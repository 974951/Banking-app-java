package com.bank.test.utility;

import com.bank.test.model.AppMessage;
import com.bank.test.model.User;

public class BankingAppUtility {

    private UserCurdUtility userCurdUtility;

    public BankingAppUtility() {
        userCurdUtility = new UserCrudDatabaseUtility();
    }

    public AppMessage createAccount(User user) {
        return  userCurdUtility.saveUserDetails(user);
    }

    public void withdrawAmount(Long accountNumber, Double withdrawAmount) {
        User fetchedUser = userCurdUtility.getUserDetails(accountNumber);

        if (fetchedUser == null) {
            System.out.println("Sorry this account number doesn't exist in our system.");
        } else {

            if (fetchedUser.getAmount() < withdrawAmount) {
                System.out.println("You don't have enough balance in your account");
            } else {

                fetchedUser.setAmount(fetchedUser.getAmount() - withdrawAmount);

                AppMessage message = userCurdUtility.saveUserDetails(fetchedUser);

                if (message.isSuccess()) {
                    System.out.println("Your Details is successfully saved!");
                } else {
                    System.out.println(message.getErrorMessage());
                    System.out.println("Error, Please try again!");
                }
            }

        }
    }

    public void depositAmount(Long accountNumber, Double depositAmount) {
        User fetchedUser = userCurdUtility.getUserDetails(accountNumber);

        if (fetchedUser == null) {
            System.out.println("Sorry this account number doesn't exist in our system.");
        } else {

            fetchedUser.setAmount(fetchedUser.getAmount() + depositAmount);

            AppMessage message = userCurdUtility.saveUserDetails(fetchedUser);

            if (message.isSuccess()) {
                System.out.println("Your Details is successfully saved!");
            } else {
                System.out.println(message.getErrorMessage());
                System.out.println("Error, Please try again!");
            }
        }
    }
public User getAccountDetails(Long accountNumber){
        return userCurdUtility.getUserDetails(accountNumber);
}
    public void closeAccount(Long accountNumber) {
        User user=getAccountDetails(accountNumber);

        if (user==null){
            System.out.println("Your account does not exit");

        }
        else {
            userCurdUtility.deleteduser(user);
        }
        }

    }

