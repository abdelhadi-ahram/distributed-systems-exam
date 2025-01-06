package bank.merchant_service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("bank.merchant_service.domain")
@EnableJpaRepositories("bank.merchant_service.repos")
@EnableTransactionManagement
public class DomainConfig {
}
