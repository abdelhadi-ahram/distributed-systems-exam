package bank.bank_branch_service.service;

import bank.bank_branch_service.domain.Account;
import bank.bank_branch_service.domain.Transaction;
import bank.bank_branch_service.model.TransactionDTO;
import bank.bank_branch_service.repos.AccountRepository;
import bank.bank_branch_service.repos.TransactionRepository;
import bank.bank_branch_service.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(final TransactionRepository transactionRepository,
            final AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<TransactionDTO> findAll() {
        final List<Transaction> transactions = transactionRepository.findAll(Sort.by("id"));
        return transactions.stream()
                .map(transaction -> mapToDTO(transaction, new TransactionDTO()))
                .toList();
    }

    public TransactionDTO get(final Long id) {
        return transactionRepository.findById(id)
                .map(transaction -> mapToDTO(transaction, new TransactionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TransactionDTO transactionDTO) {
        final Transaction transaction = new Transaction();
        mapToEntity(transactionDTO, transaction);
        return transactionRepository.save(transaction).getId();
    }

    public void update(final Long id, final TransactionDTO transactionDTO) {
        final Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(transactionDTO, transaction);
        transactionRepository.save(transaction);
    }

    public void delete(final Long id) {
        transactionRepository.deleteById(id);
    }

    private TransactionDTO mapToDTO(final Transaction transaction,
            final TransactionDTO transactionDTO) {
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setCheckNumber(transaction.getCheckNumber());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setAccount(transaction.getAccount() == null ? null : transaction.getAccount().getId());
        return transactionDTO;
    }

    private Transaction mapToEntity(final TransactionDTO transactionDTO,
            final Transaction transaction) {
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setCheckNumber(transactionDTO.getCheckNumber());
        transaction.setDate(transactionDTO.getDate());
        final Account account = transactionDTO.getAccount() == null ? null : accountRepository.findById(transactionDTO.getAccount())
                .orElseThrow(() -> new NotFoundException("account not found"));
        transaction.setAccount(account);
        return transaction;
    }

}
