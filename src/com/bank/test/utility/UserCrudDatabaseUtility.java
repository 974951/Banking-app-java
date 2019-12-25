package com.bank.test.utility;

import com.bank.test.model.AppMessage;
import com.bank.test.model.User;

import java.sql.*;

public class UserCrudDatabaseUtility implements UserCurdUtility {

    Connection connection = null;

    @Override
    public AppMessage saveUserDetails(User user) {
      if (user.getId()==0){
          return insertUser(user);
      }
       else{
           return updateUser(user);
      }
    }

    //Open Db connection to mysql
    //hold connection using connection object
    //prepare query using this account number i.e use select query
    //hold result set object
    //Iterate through ResultSet object using using its next()

    public AppMessage insertUser(User user){
        AppMessage appMessage=new AppMessage();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_banking_app";
            String userName = "root";
            String password = "admin";

            Connection connection = DriverManager.getConnection(url, userName, password);
            String insertQuery="insert into tbl_user " +
                    "(account_number, name, address, amount) " +
                    "(?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(insertQuery);
            preparedStatement.setDouble(1,user.getAmount());

            preparedStatement.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
return appMessage;

    }

    @Override
    public User getUserDetails(Long accountNumber) {
// 1. Open Db Connection to mysql
        // 2. Hold Connection using Connection Object
        // 3. Prepare query using this accountNumber i.e use Select Query
        // 4. Hold ResultSet Object
        // 5. Iterate through ResultSet object using its next() function
        // 6. Set Values to User Object
        // 7. Return User Object

        User user = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_banking_app";
            String userName = "root";
            String password = "admin";

            Connection connection = DriverManager.getConnection(url, userName, password);

            String selectQuery = "SELECT * FROM tbl_user WHERE account_number = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setLong(1, accountNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Checking  if user exists or not
                user = new User();
                while (resultSet.next()) {

                    // fetching column values and storing to local variables
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    Long accNo = resultSet.getLong("account_number");
                    Double amount = resultSet.getDouble("amount");

                    // setting local variables values to User attributes.
                    user.setId(id);
                    user.setName(name);
                    user.setAddress(address);
                    user.setAccountNumber(accNo);
                    user.setAmount(amount);
                }


            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }

    @Override
    public void deleteduser(User user) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_banking_app";
            String userName = "root";
            String password = "admin";

            Connection connection = DriverManager.getConnection(url, userName, password);
            String deleteQuery = "DELETE from tbl_user Where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.execute();
            Connection close;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        }
    public AppMessage updateUser(User user){
        AppMessage appMessage=new AppMessage();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_banking_app";
            String userName = "root";
            String password = "admin";

            connection = DriverManager.getConnection(url, userName, password);

            String updateQuery = "update tbl_user\n" +
                    "set amount=?\n" +
                    "where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, user.getAmount());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.execute();
            appMessage.setSuccess(true);
            appMessage.setData(user);


                } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
return appMessage;
    }

        }




