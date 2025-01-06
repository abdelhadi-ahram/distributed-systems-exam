package bank.central_bank_service.service;

import bank.central_bank_service.domain.BankBranch;
import bank.central_bank_service.model.BankBranchDTO;
import bank.central_bank_service.repos.BankBranchRepository;
import bank.central_bank_service.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BankBranchService {

    private final BankBranchRepository bankBranchRepository;

    public BankBranchService(final BankBranchRepository bankBranchRepository) {
        this.bankBranchRepository = bankBranchRepository;
    }

    public List<BankBranchDTO> findAll() {
        final List<BankBranch> bankBranches = bankBranchRepository.findAll(Sort.by("id"));
        return bankBranches.stream()
                .map(bankBranch -> mapToDTO(bankBranch, new BankBranchDTO()))
                .toList();
    }

    public BankBranchDTO get(final Long id) {
        return bankBranchRepository.findById(id)
                .map(bankBranch -> mapToDTO(bankBranch, new BankBranchDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BankBranchDTO bankBranchDTO) {
        final BankBranch bankBranch = new BankBranch();
        mapToEntity(bankBranchDTO, bankBranch);
        return bankBranchRepository.save(bankBranch).getId();
    }

    public void update(final Long id, final BankBranchDTO bankBranchDTO) {
        final BankBranch bankBranch = bankBranchRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bankBranchDTO, bankBranch);
        bankBranchRepository.save(bankBranch);
    }

    public void delete(final Long id) {
        bankBranchRepository.deleteById(id);
    }

    private BankBranchDTO mapToDTO(final BankBranch bankBranch, final BankBranchDTO bankBranchDTO) {
        bankBranchDTO.setId(bankBranch.getId());
        bankBranchDTO.setName(bankBranch.getName());
        bankBranchDTO.setCity(bankBranch.getCity());
        bankBranchDTO.setWebServiceUrl(bankBranch.getWebServiceUrl());
        return bankBranchDTO;
    }

    private BankBranch mapToEntity(final BankBranchDTO bankBranchDTO, final BankBranch bankBranch) {
        bankBranch.setName(bankBranchDTO.getName());
        bankBranch.setCity(bankBranchDTO.getCity());
        bankBranch.setWebServiceUrl(bankBranchDTO.getWebServiceUrl());
        return bankBranch;
    }

}
