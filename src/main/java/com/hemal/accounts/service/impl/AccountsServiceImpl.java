package com.hemal.accounts.service.impl;

import com.hemal.accounts.constants.AccountConstants;
import com.hemal.accounts.dto.AccountsDto;
import com.hemal.accounts.dto.CustomerDto;
import com.hemal.accounts.entity.Accounts;
import com.hemal.accounts.entity.Customer;
import com.hemal.accounts.exception.CustomerAlreadyExistsException;
import com.hemal.accounts.exception.ResourceNotFoundException;
import com.hemal.accounts.mapper.AccountsMapper;
import com.hemal.accounts.mapper.CustomerMapper;
import com.hemal.accounts.repository.AccountRepository;
import com.hemal.accounts.repository.CustomerRepository;
import com.hemal.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService {


    private AccountRepository accountsRepository;
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());

        System.out.println("errrr"+customerRepository);

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

         if(optionalCustomer.isPresent()) {
             throw new CustomerAlreadyExistsException(AccountConstants.CUSTOMER_ALREADY_EXISTS + customerDto.getMobileNumber());
         }





        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccounts(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        // get the data from DB layer
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        // get the data from DB layer
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        // map Customer entity to DTO
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());

        // map Account entity to Account DTO
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    private Accounts createNewAccounts(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000 + (long)(Math.random() * 999999999);

        newAccount.setAccountNumber(randomAccNumber);

        newAccount.setAccountType(AccountConstants.SAVINGS);

        newAccount.setBranchAddress(AccountConstants.ADDRESS);



        return  newAccount;
    }

    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            // All the data from the end user are in accountsDto, so we need to map it to the accounts entity
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );

            // All the updated data, so we need to map it to the customer entity
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        // since customerId is not the primary key in accounts table we need to write custom deleteByCustomerId method in accountsRepository
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}
