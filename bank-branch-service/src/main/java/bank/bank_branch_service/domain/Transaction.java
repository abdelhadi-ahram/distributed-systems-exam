package bank.bank_branch_service.domain;

import bank.bank_branch_service.model.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Transactions")
@Getter
@Setter
public class Transaction {

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
    private Double amount;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column
    private String description;

    @Column
    private String checkNumber;

    @Column
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

}
