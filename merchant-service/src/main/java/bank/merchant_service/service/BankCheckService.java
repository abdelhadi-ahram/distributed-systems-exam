package bank.merchant_service.service;

import bank.merchant_service.domain.BankCheck;
import bank.merchant_service.model.BankCheckDTO;
import bank.merchant_service.repos.BankCheckRepository;
import bank.merchant_service.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BankCheckService {

    private final BankCheckRepository bankCheckRepository;

    public BankCheckService(final BankCheckRepository bankCheckRepository) {
        this.bankCheckRepository = bankCheckRepository;
    }

    public List<BankCheckDTO> findAll() {
        final List<BankCheck> bankChecks = bankCheckRepository.findAll(Sort.by("id"));
        return bankChecks.stream()
                .map(bankCheck -> mapToDTO(bankCheck, new BankCheckDTO()))
                .toList();
    }

    public BankCheckDTO get(final Long id) {
        return bankCheckRepository.findById(id)
                .map(bankCheck -> mapToDTO(bankCheck, new BankCheckDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BankCheckDTO bankCheckDTO) {
        final BankCheck bankCheck = new BankCheck();
        mapToEntity(bankCheckDTO, bankCheck);
        return bankCheckRepository.save(bankCheck).getId();
    }

    public void update(final Long id, final BankCheckDTO bankCheckDTO) {
        final BankCheck bankCheck = bankCheckRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bankCheckDTO, bankCheck);
        bankCheckRepository.save(bankCheck);
    }

    public void delete(final Long id) {
        bankCheckRepository.deleteById(id);
    }

    private BankCheckDTO mapToDTO(final BankCheck bankCheck, final BankCheckDTO bankCheckDTO) {
        bankCheckDTO.setId(bankCheck.getId());
        bankCheckDTO.setCheckNumber(bankCheck.getCheckNumber());
        bankCheckDTO.setBankCode(bankCheck.getBankCode());
        bankCheckDTO.setAccountNumber(bankCheck.getAccountNumber());
        bankCheckDTO.setCustomerNumber(bankCheck.getCustomerNumber());
        bankCheckDTO.setAmount(bankCheck.getAmount());
        bankCheckDTO.setIsCertified(bankCheck.getIsCertified());
        return bankCheckDTO;
    }

    private BankCheck mapToEntity(final BankCheckDTO bankCheckDTO, final BankCheck bankCheck) {
        bankCheck.setCheckNumber(bankCheckDTO.getCheckNumber());
        bankCheck.setBankCode(bankCheckDTO.getBankCode());
        bankCheck.setAccountNumber(bankCheckDTO.getAccountNumber());
        bankCheck.setCustomerNumber(bankCheckDTO.getCustomerNumber());
        bankCheck.setAmount(bankCheckDTO.getAmount());
        bankCheck.setIsCertified(bankCheckDTO.getIsCertified());
        return bankCheck;
    }

}
