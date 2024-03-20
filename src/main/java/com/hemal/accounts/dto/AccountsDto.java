package com.hemal.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(
        name = "Account",
        description = "Account details"

)
public class AccountsDto {

    @Schema(
            description = "Account Number" , example = "1234567890"
    )
    @NotEmpty(message = "Account Number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type" , example = "Savings"
    )
    @NotEmpty(message = "Account Type")
    private String accountType;

    @Schema(
            description = "Branch Address" , example = "Colombo"
    )
    @NotEmpty(message = "Branch Address is required")
    private String branchAddress;
    private LocalDate createDt;
}
