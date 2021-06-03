package org.bank.transactions;
import org.bank.connection.JDBCConnection;

import java.sql.ResultSet;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {
    public static void main(String[] args) throws Exception {

        int option;
        String query;
        String holder_name;
        int account_no;

        while(true){
            System.out.println("-------------WELCOME TO BANK--------------");
            System.out.println("1. CREATE ACCOUNT");
            System.out.println("2. DEPOSIT MONEY");
            System.out.println("3. WITHDRAW MONEY");
            System.out.println("4. DEACTIVATE ACCOUNT");
            System.out.println("5. CHECK BALANCE");
            System.out.println("6. EXIT");
            System.out.print("Choose Option : ");
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();


            JDBCConnection jdbc = new JDBCConnection();
            switch(option){
                case 1:
                    System.out.print("Enter Your Name : ");
                    holder_name = sc.next();

                    System.out.print("Enter Initial Amount : ");
                    double total_amount =  sc.nextFloat();

                    query = "INSERT INTO account (holder_name, total_amount) values( '" + holder_name + "', "+ total_amount +")";
                    System.out.println(query);
                    int result = jdbc.executeUpdate(query);
                    System.out.println(result);
                    break;

                case 2:
                    System.out.print("Enter Account Number : ");
                    account_no = sc.nextInt();

                    System.out.println("Enter total Money to Deposit : ");
                    total_amount = sc.nextFloat();

                    query = "update account set total_amount = (total_amount + "+ total_amount +") where account_no = " + account_no +";";
                    result = jdbc.executeUpdate(query);
                    System.out.println(query);
                    break;

                case 3:
                    System.out.print("Enter Account Number : ");
                    account_no = sc.nextInt();

                    System.out.println("Enter total Money to Withdraw : ");
                    total_amount = sc.nextFloat();

                    query = "select total_amount from account where account_no = "+ account_no;
                    ResultSet current_amount = jdbc.executeQuery(query);
                    current_amount.next();

                    double amount = current_amount.getDouble("total_amount");

                    if(total_amount > amount){
                        System.out.println("Low Amount");
                        break;
                    }

                    query = "update account set total_amount = (total_amount - "+ total_amount +") where account_no = " + account_no +";";
                    result = jdbc.executeUpdate(query);
                    System.out.println(query);
                    break;

                case 4:
                    System.out.println("Enter Account Number : ");
                    account_no = sc.nextInt();

                    query = "DELETE FROM student where account_no = '" + account_no+ "'";
                    System.out.println(query);
                    break;

                case 5:
                    System.out.print("Enter the Account Number : ");
                    account_no = sc.nextInt();

                    query = "SELECT total_amount from account where account_no = " + account_no;
                    current_amount = jdbc.executeQuery(query);

                    current_amount.next();

                    amount = current_amount.getDouble("total_amount");
                    System.out.println("TOTAL AMOUNT : " + amount);
                    break;

                case 6:
                    System.out.println("Thanks!!!!!!!!");
                    System.exit(0);
            }
        }
    }
}
