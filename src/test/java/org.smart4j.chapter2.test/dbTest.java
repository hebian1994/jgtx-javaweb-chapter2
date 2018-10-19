package org.smart4j.chapter2.test;

import org.junit.Test;
import org.smart4j.chapter2.dbutil.DbConnectionUtil;

public class dbTest {
    @Test
    public void db1(){
        System.out.println(DbConnectionUtil.getDataSource());
    }
}
