package com.wangxiaomeng.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtils {
    private static Logger logger = (Logger) LogManager.getLogger(DruidUtils.class.getName());
    private static Properties properties;

    // 加载数据库连接池
    static {
        properties = new Properties();
        InputStream in = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(in);
            logger.info("file druid.properties config load success!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal("occur error when loading druid.properties");
        }
    }

    public static Connection getConnection() throws Exception {
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        logger.info("the status of getting connection from database pool is successful!");
        return connection;
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
