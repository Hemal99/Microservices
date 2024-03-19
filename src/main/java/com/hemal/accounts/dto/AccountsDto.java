package com.hemal.accounts.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountsDto {

    private long accountNumber;
    private String accountType;
    private String branchAddress;
    private LocalDate createDt;
}