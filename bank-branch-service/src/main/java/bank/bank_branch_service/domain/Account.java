package bank.bank_branch_service.domain;

import bank.bank_branch_service.model.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Entity
@Table(name = "Accounts")
@Getter
@Setter
public class Account {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String number;

    @Column
    private Double balance;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column
    private String customerReference;

    @Column(columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> transacritons;

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions;

}
