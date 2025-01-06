package bank.bank_branch_service.model;

import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountDTO {

    private Long id;

    @Size(max = 255)
    private String number;

    private Double balance;

    private AccountType type;

    @Size(max = 255)
    private String customerReference;

    private List<@Size(max = 255) String> transacritons;

}
