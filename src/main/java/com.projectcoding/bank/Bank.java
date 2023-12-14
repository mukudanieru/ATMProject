package com.projectcoding.bank;

import com.projectcoding.accounts.Account;
import com.projectcoding.configuration.CreateAccount;

import java.util.Arrays;

public class Bank {
    private Account[] users;

    public Bank() {
        Account defaultUserOne = new Account("125870", true, "1234");
        Account defaultUserTwo = new Account("789456", true, "5678");
        Account defaultUserThree = new Account("996687", true, "4321");
        Account defaultUserFour = new Account("225588", false, "8765");
        Account defaultUserFive = new Account("335574", false, "1111");
        this.users = new Account[]{defaultUserOne, defaultUserTwo, defaultUserThree, defaultUserFour, defaultUserFive};

        for (Account user : this.users) {
            user.setBalance(20000.36);
        }
    }

    public void registerAccount() {
        CreateAccount newAccount = new CreateAccount();
        this.addAccount(newAccount.getNewAccount());
    }

    public Account loginAccount(String pin) {
        if (this.users.length == 0) {
            System.out.println("The bank currently has no accounts.");
            return null;
        }

        for (Account user : this.users) {
            if (pin.equals(user.getPin())) {
                return user;
            }
        }

        System.out.println("Invalid PIN. Please check your PIN and try again.");
        return null;
    }

    public void displayAccounts() {
        for (Account account : this.users) {
            System.out.println(account);
        }
    }

    private void addAccount(Account account) {
        this.users = Arrays.copyOf(this.users, users.length + 1);
        this.users[users.length - 1] = account;
    }
}