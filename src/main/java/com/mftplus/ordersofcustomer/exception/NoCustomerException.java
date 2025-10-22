package com.mftplus.ordersofcustomer.exception;

public class NoCustomerException extends Exception {
    public NoCustomerException() {
        super("No customer found");
    }
}
