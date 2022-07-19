package com.wangxiaomeng.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static Logger logger = (Logger) LogManager.getLogger("initlog");
    private static String jdbcDriver;
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;

    // 加载MySql配置文件
    static {
        String projectRootDirectory = System.getProperty("user.dir");
        String catalinaHomePath = System.getProperty("catalina.home");
        // 对项目进行部署后，会将properties类型的配置文件复制到项目部署文件夹下相应的位置--->management/WEB-INF/classes/mysql.properties
        URL mysqlConfigUrl = JDBCUtils.class.getClassLoader().getResource("mysql.properties");
        logger.info("projectRootDirectory=" + projectRootDirectory);
        logger.info("catalinaHomePath=" + catalinaHomePath);
        logger.info("mysqlConfigUrl=" + mysqlConfigUrl);
        Properties properties = new Properties();
        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("mysql.properties");
            properties.load(in);
            jdbcDriver = properties.getProperty("driver");
            jdbcURL = properties.getProperty("url");
            jdbcUsername = properties.getProperty("username");
            jdbcPassword = properties.getProperty("password");
            logger.info("load mysql config success!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal("occur error when loading mysql.properties");
        }
    }

    static {
        try {
            Class.forName(jdbcDriver);
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
