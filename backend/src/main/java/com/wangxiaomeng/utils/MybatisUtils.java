package com.wangxiaomeng.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static Logger logger = (Logger) LogManager.getLogger("initlog");
    private static SqlSessionFactory factory = null;
    static {
        logger.info("Mybatis load start!");
        String resource = "mybatis-config.xml";
        try (
           InputStream in = Resources.getResourceAsStream(resource);
        ){
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Mybatis load failed!");
        } finally {
            logger.info("Mybatis load end!");
        }
    }

    public static SqlSession getSqlSession(){
        SqlSession session = null;
        if(factory != null){
            session = factory.openSession();
        }
        return session;
    }
}