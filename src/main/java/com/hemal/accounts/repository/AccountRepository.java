package com.hemal.accounts.repository;

import com.hemal.accounts.entity.Accounts;
import com.hemal.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long>{

    Optional<Accounts> findByCustomerId(Long customerId);



}
