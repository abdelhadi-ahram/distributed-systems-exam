package bank.bank_branch_service.repos;

import bank.bank_branch_service.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
