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

//    public Account() {}
//
//    public Account(Long id, String name, String acctNumber, double balance, double minimumBalance, double penalty, double transactionCharge, double interestCharge) {
//        this.id = id;
//        this.name = name;
//        this.acctNumber = acctNumber;
//        this.balance = balance;
//        this.minimumBalance = minimumBalance;
//        this.penalty = penalty;
//        this.transactionCharge = transactionCharge;
//        this.interestCharge = interestCharge;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAcctNumber() {
//        return acctNumber;
//    }
//
//    public void setAcctNumber(String acctNumber) {
//        this.acctNumber = acctNumber;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }
//
//    public double getMinimumBalance() {
//        return minimumBalance;
//    }
//
//    public void setMinimumBalance(double minimumBalance) {
//        this.minimumBalance = minimumBalance;
//    }
//
//    public double getPenalty() {
//        return penalty;
//    }
//
//    public void setPenalty(double penalty) {
//        this.penalty = penalty;
//    }
//
//    public double getTransactionCharge() {
//        return transactionCharge;
//    }
//
//    public void setTransactionCharge(double transactionCharge) {
//        this.transactionCharge = transactionCharge;
//    }
//
//    public double getInterestCharge() {
//        return interestCharge;
//    }
//
//    public void setInterestCharge(double interestCharge) {
//        this.interestCharge = interestCharge;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Account account = (Account) o;
//
//        if (Double.compare(account.balance, balance) != 0) return false;
//        if (Double.compare(account.minimumBalance, minimumBalance) != 0) return false;
//        if (Double.compare(account.penalty, penalty) != 0) return false;
//        if (Double.compare(account.transactionCharge, transactionCharge) != 0) return false;
//        if (Double.compare(account.interestCharge, interestCharge) != 0) return false;
//        if (!Objects.equals(id, account.id)) return false;
//        if (!Objects.equals(name, account.name)) return false;
//        return Objects.equals(acctNumber, account.acctNumber);
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (acctNumber != null ? acctNumber.hashCode() : 0);
//        temp = Double.doubleToLongBits(balance);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(minimumBalance);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(penalty);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(transactionCharge);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(interestCharge);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        return result;
//    }
}
