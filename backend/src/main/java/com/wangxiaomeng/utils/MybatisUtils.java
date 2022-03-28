package com.wangxiaomeng.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory factory = null;
    static {
        System.out.println("oppo");
        String resource = "mybatis-config.xml";
        InputStream in = null;
        // 加载配置文件
        try {
            in = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        factory = new SqlSessionFactoryBuilder().build(in);
    }

    public static SqlSessionFactory getFactory() {
        return factory;
    }
}
