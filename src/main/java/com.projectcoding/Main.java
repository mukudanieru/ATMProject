package com.projectcoding;

import com.projectcoding.accounts.Account;
import com.projectcoding.bank.Bank;

import java.util.Scanner;

public class Main {
    private static Account user;
    private final static Bank bank = new Bank();
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            menuOne();

            int prompt = prompt("Choose: ");

            if (prompt == 1) {
                simulator();
            } else if (prompt == 2) {
                bank.registerAccount();
                bank.displayAccounts();
            } else if (prompt == 3) {
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        input.close();
    }

    private static String checkPin() {
        int tries = 0;
        while (tries <= 2) {
            System.out.print("Enter PIN: ");
            String pin = input.nextLine();

            if (pin.length() >= 4) {
                return pin;
            } else {
                System.out.println("Invalid PIN. Please enter a 4-digit PIN.");
            }

            tries++;
        }
        return null;
    }

    private static void simulator() {
        String pin = checkPin();

        if (pin == null) {
            System.out.println("Too many invalid attempts. Exiting.");
            return;
        }

        user = bank.loginAccount(pin);

        while (user != null) {
            menuTwo();

            int prompt = prompt("Choose: ");

            if (prompt == 1) {
                checkBalance();
            } else if (prompt == 2) {
                cashWithdrawal();
            } else if (prompt == 3) {
                cashDeposit();
            } else if (prompt == 4) {
                checkAccount();
            } else if (prompt == 5) {
                break;
            }

            while (true) {
                int promptTwo = prompt("\nDo you want another transaction? [1] - YES [2] - NO: ");

                if (promptTwo == 1) {
                    break;
                } else if (promptTwo == 2) {
                    return;
                } else {
                    System.out.println("Invalid input!");
                }
            }
        }
    }

    private static void checkBalance() {
        System.out.println("-- CHECKING BALANCE --");
        System.out.printf("Your current balance is ₱%.2f%n", user.getBalance());
    }

    private static void cashWithdrawal() {
        System.out.println("-- CASH WITHDRAWAL --");

        user.withdrawal(getDouble("Amount: "));
    }

    private static void cashDeposit() {
        System.out.println("-- CASH DEPOSIT --");

        user.deposit(getDouble("Amount: "));
    }

    private static void checkAccount() {
        System.out.println("-- YOUR PERSONAL BANK ACCOUNT INFO --");
        System.out.println(user);
    }

    private static void menuOne() {
        clearScreen();
        System.out.println("-- Automated Teller Machine --");
        System.out.println("[1] - Insert CARD | [2] - Create an ATM ACCOUNT | [3] - Exit");
    }

    private static void menuTwo() {
        clearScreen();
        System.out.println("-- Please select your transaction --");
        System.out.println(
                "[1] - Balance Inquiry | [2] - Cash withdrawal | [3] - Cash deposit | [4] - Check account info | [5] - Remove your card");
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static int prompt(String x) {
        System.out.print(x);
        int prompt = input.nextInt();
        input.nextLine();

        return prompt;
    }

    private static double getDouble(String x) {
        System.out.print(x);
        double amount = input.nextDouble();
        input.nextLine();

        return amount;
    }
}
