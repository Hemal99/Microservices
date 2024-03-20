package com.hemal.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(
        name = "Customer",
        description = "Customer details"

)
public class CustomerDto {

    @Schema(
            description = "Name of the customer" , example = "Hemal Perera"
    )
    @NotEmpty(message = "Name is required")
    @Size(min=5,max = 30, message = "Name should be between 5 to 30 characters")
    private String name;

    @Schema(
            description = "Email address of the customer" , example = "hemal@gmail.com")
    @NotEmpty(message = "Email is required")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
