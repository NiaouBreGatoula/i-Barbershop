package controller;

import model.Account;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountController {

    // Method to fetch accounts from the database and add them to the ApplicationController's accounts list
    public void importAccounts() {
        try {
            ArrayList<Account> accountsList = DatabaseConnection.fetchAccounts();
            ApplicationController.accounts.addAll(accountsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
