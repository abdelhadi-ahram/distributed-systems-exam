package bank.bank_branch_service.service;

import bank.bank_branch_service.domain.Account;
import bank.bank_branch_service.domain.Transaction;
import bank.bank_branch_service.model.AccountDTO;
import bank.bank_branch_service.repos.AccountRepository;
import bank.bank_branch_service.repos.TransactionRepository;
import bank.bank_branch_service.util.NotFoundException;
import bank.bank_branch_service.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(final AccountRepository accountRepository,
            final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<AccountDTO> findAll() {
        final List<Account> accounts = accountRepository.findAll(Sort.by("id"));
        return accounts.stream()
                .map(account -> mapToDTO(account, new AccountDTO()))
                .toList();
    }

    public AccountDTO get(final Long id) {
        return accountRepository.findById(id)
                .map(account -> mapToDTO(account, new AccountDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AccountDTO accountDTO) {
        final Account account = new Account();
        mapToEntity(accountDTO, account);
        return accountRepository.save(account).getId();
    }

    public void update(final Long id, final AccountDTO accountDTO) {
        final Account account = accountRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(accountDTO, account);
        accountRepository.save(account);
    }

    public void delete(final Long id) {
        accountRepository.deleteById(id);
    }

    private AccountDTO mapToDTO(final Account account, final AccountDTO accountDTO) {
        accountDTO.setId(account.getId());
        accountDTO.setNumber(account.getNumber());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setType(account.getType());
        accountDTO.setCustomerReference(account.getCustomerReference());
        accountDTO.setTransacritons(account.getTransacritons());
        return accountDTO;
    }

    private Account mapToEntity(final AccountDTO accountDTO, final Account account) {
        account.setNumber(accountDTO.getNumber());
        account.setBalance(accountDTO.getBalance());
        account.setType(accountDTO.getType());
        account.setCustomerReference(accountDTO.getCustomerReference());
        account.setTransacritons(accountDTO.getTransacritons());
        return account;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Account account = accountRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Transaction accountTransaction = transactionRepository.findFirstByAccount(account);
        if (accountTransaction != null) {
            referencedWarning.setKey("account.transaction.account.referenced");
            referencedWarning.addParam(accountTransaction.getId());
            return referencedWarning;
        }
        return null;
    }

}
