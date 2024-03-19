package com.hemal.accounts.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDto {

    private String name;
    private String email;
    private String mobileNumber;

    private AccountsDto accountsDto;
}
