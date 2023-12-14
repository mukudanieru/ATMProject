package com.projectcoding.configuration;

import com.projectcoding.accounts.Account;

import java.util.Scanner;

public class CreateAccount {
    private final Scanner input = new Scanner(System.in);
    private final Account newAccount;

    public CreateAccount() {
        message();
        String accountNumber = accountNumber();
        boolean isPinChangeable = pinChangeable();
        String pin = registerPin();
        this.newAccount = new Account(accountNumber, isPinChangeable, pin);
    }

    private void message() {
        String message = "-- Creating a bank account -- \n";
        System.out.println(message);
    }

    private String accountNumber() {
       String message = "Please enter a unique account number: ";
       System.out.print(message);
       return this.input.nextLine();
    }

    private boolean pinChangeable() {
        String message = "Would you like a changeable PIN? [1] - Yes | [2] - No: ";
        System.out.print(message);

        int prompt = input.nextInt();
        input.nextLine();

        return prompt == 1;
    }

    private String registerPin() {
        while (true) {
            System.out.print("Create a 4-digit PIN for account security: ");
            String first = input.nextLine();

            System.out.print("Enter your PIN again: ");
            String second = input.nextLine();

            if (first.equalsIgnoreCase(second)) {
                return first;
            }

            System.out.println("Two PIN that you entered are not the same. Try again.\n");
        }
    }

    public Account getNewAccount() {
        System.out.println("Congratulations! Your account has been successfully created. You may now login.");
        return this.newAccount;
    }
}
