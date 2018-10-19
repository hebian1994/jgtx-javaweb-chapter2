package org.smart4j.chapter2.dbutil;


import org.apache.commons.dbcp2.BasicDataSource;
import org.smart4j.chapter2.util.PropsUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionUtil {

    private static final BasicDataSource DATA_SOURCE;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        String driver = conf.getProperty("jdbc.driver");
        String url = conf.getProperty("jdbc.url");
        String username = conf.getProperty("jdbc.username");
        String password = conf.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }

    /**
     * 获取数据库连接
     */
    public static DataSource getDataSource(){
        return DATA_SOURCE;
    }
}
