package bank.central_bank_service.rest;

import bank.central_bank_service.model.BankBranchDTO;
import bank.central_bank_service.service.BankBranchService;
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
@RequestMapping(value = "/api/bankBranches", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankBranchResource {

    private final BankBranchService bankBranchService;

    public BankBranchResource(final BankBranchService bankBranchService) {
        this.bankBranchService = bankBranchService;
    }

    @GetMapping
    public ResponseEntity<List<BankBranchDTO>> getAllBankBranches() {
        return ResponseEntity.ok(bankBranchService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankBranchDTO> getBankBranch(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(bankBranchService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBankBranch(
            @RequestBody @Valid final BankBranchDTO bankBranchDTO) {
        final Long createdId = bankBranchService.create(bankBranchDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateBankBranch(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final BankBranchDTO bankBranchDTO) {
        bankBranchService.update(id, bankBranchDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBankBranch(@PathVariable(name = "id") final Long id) {
        bankBranchService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
