package com.wangxiaomeng.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static final String jdbcURL="jdbc:mysql://localhost:3306/javaguider?useSSL=false&serverTimezone=UTC";
    private static final String jdbcUsername="root";
    private static final String jdbcPassword="root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static Connection getConnection(){
        Connection connnection=null;
        try {
            connnection= DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connnection;
    }

    public static void printSQLException(SQLException ex){
        for(Throwable e:ex){
            e.printStackTrace(System.err);
            System.err.println("SQLState: "+((SQLException)e).getSQLState());
            System.err.println("Error Code: "+((SQLException)e).getErrorCode());
            System.err.println("Message: "+e.getMessage());
            Throwable t=ex.getCause();
            while (t != null){
                System.out.println("Cause: "+t);
                t=t.getCause();
            }
        }
    }
}
