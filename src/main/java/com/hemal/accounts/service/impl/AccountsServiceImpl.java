package com.hemal.accounts.service.impl;

import com.hemal.accounts.constants.AccountConstants;
import com.hemal.accounts.dto.CustomerDto;
import com.hemal.accounts.entity.Accounts;
import com.hemal.accounts.entity.Customer;
import com.hemal.accounts.exception.CustomerAlreadyExistsException;
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

         customer.setCreatedAt(LocalDateTime.now());
         customer.setCreatedBy("Anonymouse");



        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccounts(savedCustomer));

    }

    private Accounts createNewAccounts(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000 + (long)(Math.random() * 999999999);

        newAccount.setAccountNumber(randomAccNumber);

        newAccount.setAccountType(AccountConstants.SAVINGS);

        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymouse");

        return  newAccount;
    }


}
