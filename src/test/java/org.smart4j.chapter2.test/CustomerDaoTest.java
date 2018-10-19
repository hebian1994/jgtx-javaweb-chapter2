package org.smart4j.chapter2.test;

import org.junit.Test;
import org.smart4j.chapter2.dao.CustomerDao;
import org.smart4j.chapter2.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerDaoTest {
    @Test
    public void t1(){
        CustomerDao customerDao=new CustomerDao();
        //查询所有
        String sql = "SELECT * FROM customer";
        System.out.println(customerDao.queryEntityList(Customer.class,sql));
        //查询单个
        String sql2 = "SELECT * FROM customer WHERE id = ?";
        long id=1;
        System.out.println(customerDao.queryEntity(Customer.class,sql2,id));
    }
    @Test
    public void t2(){
        CustomerDao customerDao=new CustomerDao();
        //增加
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "John");
        fieldMap.put("telephone", "13512345678");
        customerDao.insertEntity(Customer.class,fieldMap);
        //删除
        customerDao.deleteEntity(Customer.class,1);
        //更改
        customerDao.updateEntity(Customer.class,2,fieldMap);
    }
    @Test
    public void t3(){
        CustomerDao customerDao=new CustomerDao();
        //更改,根据id改写名字等
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "李大建");
        customerDao.updateEntity(Customer.class,2,fieldMap);
    }
}
