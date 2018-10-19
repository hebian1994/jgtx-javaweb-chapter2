package org.smart4j.chapter2.service;

import org.smart4j.chapter2.dao.CustomerDao;
import org.smart4j.chapter2.model.Customer;

import java.util.List;
import java.util.Map;

public class CustomerService {
    CustomerDao customerDao=new CustomerDao();
    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return customerDao.queryEntityList(Customer.class, sql);
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return customerDao.queryEntity(Customer.class, sql, id);
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return customerDao.insertEntity(Customer.class, fieldMap);
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return customerDao.updateEntity(Customer.class, id, fieldMap);
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id) {
        return customerDao.deleteEntity(Customer.class, id);
    }
}
