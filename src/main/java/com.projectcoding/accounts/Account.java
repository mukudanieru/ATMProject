package com.projectcoding.accounts;

public class Account {
    private double balance;
    private final String accountNumber;
    private final String id;
    private String pin;
    private final boolean isPinChangeable;

    public Account(String accountNumber, boolean isPinChangeable, String pin) {
        this.accountNumber = accountNumber;
        this.id = "SA" + accountNumber;
        this.isPinChangeable = isPinChangeable;
        this.pin = pin;
    }

    @Override
    public  String toString() {
        return String.format("Your account number: %s%nYour ID number: %s", this.accountNumber, this.id);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        if (amount >= 20000) {
            this.balance = amount;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) throws IllegalAccessException {
        if (!isPinChangeable) {
            throw new IllegalAccessException("Sorry but this PIN cannot be changed.");
        }

        this.pin = pin;
    }

    public boolean isPinChangeable() {
        return isPinChangeable;
    }

    public void withdrawal(double amount) throws IllegalArgumentException {
        if (this.balance - amount == 0) {
            throw new IllegalArgumentException("Invalid withdrawal: Insufficient funds.");
        }

        this.balance -= amount;
        System.out.printf("Successful withdrawal! Remaining balance: ₱%.2f%n", this.balance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid deposit: Amount must be greater than zero.");
        }

        this.balance += amount;
        System.out.printf("Successful deposit! New balance: ₱%.2f%n", this.balance);
    }
}
