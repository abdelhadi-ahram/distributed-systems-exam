package bank.bank_branch_service.repos;

import bank.bank_branch_service.domain.Account;
import bank.bank_branch_service.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findFirstByAccount(Account account);

}
