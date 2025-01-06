package bank.merchant_service.repos;

import bank.merchant_service.domain.BankCheck;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankCheckRepository extends JpaRepository<BankCheck, Long> {
}
