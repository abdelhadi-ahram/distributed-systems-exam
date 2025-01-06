package bank.merchant_service.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BankCheckDTO {

    private Long id;

    @Size(max = 255)
    private String checkNumber;

    @Size(max = 255)
    private String bankCode;

    @Size(max = 255)
    private String accountNumber;

    @Size(max = 255)
    private String customerNumber;

    private Double amount;

    @Size(max = 255)
    private String isCertified;

}
