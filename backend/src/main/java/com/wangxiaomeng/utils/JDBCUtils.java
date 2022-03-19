package com.wangxiaomeng.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static Logger logger = (Logger) LogManager.getLogger(JDBCUtils.class.getName());
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
            logger.error("SQLState: "+((SQLException)e).getSQLState());
            logger.error("Error Code: "+((SQLException)e).getErrorCode());
            logger.error("Message: "+e.getMessage());
            Throwable t=ex.getCause();
            while (t != null){
                logger.info("Cause: "+t);
                t=t.getCause();
            }
        }
    }
}
