package org.bank.connection;

import java.sql.*;
public class JDBCConnection {

    static Connection connection = null;
    static Statement statement = null;

    public JDBCConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","Qwer@#123");
        statement = connection.createStatement();

    }

    public static int executeUpdate(String query) throws SQLException {
            int result = statement.executeUpdate(query);
            return result;
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }
//    public static void main(String[] args) throws Exception{
//
//        JDBCConnection jdbcConnection = new JDBCConnection();
//        String query = "Insert into student (stu_name)values('Anand')";
//
//        int count = ExecuteUpdate(query);
//        System.out.println(count);
//
//        query = "select * from student";
//        ResultSet resultSet = ExecuteQuery(query);
//
//        while(resultSet.next()){
//            System.out.println(resultSet.getString("roll_no"));
//            System.out.println(resultSet.getString("stu_name"));
//
//        }
//    }

}
