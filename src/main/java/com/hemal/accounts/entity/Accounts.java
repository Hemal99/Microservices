package com.hemal.accounts.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter@ToString@AllArgsConstructor@NoArgsConstructor

public class Accounts extends BaseEntity{

    @Column(name = "customer_id")
    private long customerId;
    @Column(name="account_number")
    @Id
    private long accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddress;
    @Column(name = "create_dt")
    private LocalDate createDt;

}
