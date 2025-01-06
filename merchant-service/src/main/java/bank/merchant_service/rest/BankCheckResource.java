package bank.merchant_service.rest;

import bank.merchant_service.model.BankCheckDTO;
import bank.merchant_service.service.BankCheckService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/bankChecks", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankCheckResource {

    private final BankCheckService bankCheckService;

    public BankCheckResource(final BankCheckService bankCheckService) {
        this.bankCheckService = bankCheckService;
    }

    @GetMapping
    public ResponseEntity<List<BankCheckDTO>> getAllBankChecks() {
        return ResponseEntity.ok(bankCheckService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankCheckDTO> getBankCheck(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(bankCheckService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBankCheck(
            @RequestBody @Valid final BankCheckDTO bankCheckDTO) {
        final Long createdId = bankCheckService.create(bankCheckDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateBankCheck(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final BankCheckDTO bankCheckDTO) {
        bankCheckService.update(id, bankCheckDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBankCheck(@PathVariable(name = "id") final Long id) {
        bankCheckService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
