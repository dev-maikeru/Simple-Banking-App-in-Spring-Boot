package com.bankingapp.Banking.App.in.Spring.Boot.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegularAccount.class, name = "regular"),
        @JsonSubTypes.Type(value = InterestAccount.class, name = "interest"),
        @JsonSubTypes.Type(value = CheckingAccount.class, name = "checking")
})
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String acctNumber;

    @Column
    private double balance;

    @Column
    private double minimumBalance;

    @Column
    private double penalty;

    @Column
    private double transactionCharge;

    @Column
    private double interestCharge;
}
