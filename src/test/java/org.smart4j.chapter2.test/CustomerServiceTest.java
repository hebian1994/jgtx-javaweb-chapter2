package org.smart4j.chapter2.test;

import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter2.dao.CustomerDao;
import org.smart4j.chapter2.service.CustomerService;

import java.util.HashMap;
import java.util.Map;

public class CustomerServiceTest {
    private CustomerService customerService=new CustomerService();
    @Test
    public void testservice(){
       System.out.println(customerService.getCustomerList());
       System.out.println(customerService.getCustomer(2));
    }
    @Test
    public void t2(){
        //增加
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "李成梅");
        fieldMap.put("contact", "John");
        fieldMap.put("telephone", "13512345678");
        customerService.createCustomer(fieldMap);
        //删除
        customerService.deleteCustomer(2);
    }
    @Test
    public void t3(){
        //更改
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "李成梅");
        fieldMap.put("contact", "John");
        customerService.updateCustomer(3,fieldMap);
    }

}
