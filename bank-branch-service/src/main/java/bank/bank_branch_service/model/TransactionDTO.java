package bank.bank_branch_service.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private Double amount;

    private TransactionType type;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String checkNumber;

    private LocalDate date;

    private Long account;

}
