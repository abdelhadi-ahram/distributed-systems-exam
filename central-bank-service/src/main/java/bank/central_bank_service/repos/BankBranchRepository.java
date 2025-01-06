package bank.central_bank_service.repos;

import bank.central_bank_service.domain.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {
}
