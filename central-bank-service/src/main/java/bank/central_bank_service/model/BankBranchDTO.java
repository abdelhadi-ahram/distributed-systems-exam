package bank.central_bank_service.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BankBranchDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String webServiceUrl;

}
