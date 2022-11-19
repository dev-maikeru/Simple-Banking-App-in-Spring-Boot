package com.bankingapp.Banking.App.in.Spring.Boot.utils;

public class AccountUtils {
    public static String generateAccountNumber() {
        /* return a random long of 16 length */
        long smallest = 1000_0000_0L;
        long biggest =  8999_9999_9L;

        // return a long between smallest and biggest (+1 to include biggest as well with the upper bound)
        long acctNumber = smallest + (long) (Math.random() * (biggest - smallest));
        return String.valueOf(acctNumber);
    }
}
