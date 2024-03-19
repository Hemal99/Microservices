package com.hemal.accounts.mapper;

import com.hemal.accounts.dto.AccountsDto;
import com.hemal.accounts.entity.Accounts;

public class AccountsMapper {

    // entity -> DTO
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setCreateDt(accounts.getCreateDt());
        return accountsDto;
    }

    // DTO -> entity
    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setCreateDt(accountsDto.getCreateDt());
        return accounts;
    }

}
