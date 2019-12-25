package com.bank.test;

import com.bank.test.model.AppMessage;
import com.bank.test.model.User;
import com.bank.test.utility.BankingAppUtility;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Long askAccountNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Account Number");
        Long accountNumber = scanner.nextLong();
        return accountNumber;
    }

    public static double askAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Amount ");
        double amount = scanner.nextDouble();
        return amount;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        User user = null;
        Random random = new Random();
        BankingAppUtility bankingAppUtility = new BankingAppUtility();

        System.out.println("Welcome to ERupiya Banking Application");

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Deposit Fund");
            System.out.println("3. Withdraw Fund");
            System.out.println("4. Account Details");
            System.out.println("5. close account");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    user = new User();
                    System.out.println(" CREATING ACCOUNT");
                    System.out.println("Enter your name:  ");
                    String name = scanner.next();
                    System.out.println("enter your address:");
                    String address = scanner.next();

                    user.setAccountNumber(random.nextLong());
                    user.setName(name);
                    user.setAddress(address);
                    user.setAmount(0);
                    AppMessage appMessage = bankingAppUtility.createAccount(user);
                    if (appMessage.isSuccess()) {
                        user = appMessage.getData();
                    } else {
                        System.out.println("Please try again !!!");
                    }


                    break;
                case 2:

                    bankingAppUtility.depositAmount(askAccountNumber(), askAmount());



                    break;
                case 3:

                    bankingAppUtility.withdrawAmount(askAccountNumber(), askAmount());


                    break;
                case 4:
                    System.out.println("Enter your account number");
                    Long accountNumber1 = scanner.nextLong();
                    user = bankingAppUtility.getAccountDetails(accountNumber1);

                    if (user == null) {
                        System.out.println("this account number does not exit");
                    } else {
                        System.out.println("amount=" + user.getAmount());
                        System.out.println("Account Number=" + user.getAccountNumber());
                        System.out.println("Name=" + user.getName());
                        System.out.println("Address=" + user.getAddress());
                    }


                    break;
                case 5:

                    System.out.println("Enter id you wants to delete");
                    bankingAppUtility.closeAccount(askAccountNumber());


                    break;

                default:
                    System.out.println("invalid Choice");
                    System.exit(0);
                    break;


            }

        }
    }
}
