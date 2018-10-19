package org.smart4j.chapter2.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.smart4j.chapter2.dbutil.DbConnectionUtil;
import org.smart4j.chapter2.util.CollectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CustomerDao {
    //取得数据库连接
    private QueryRunner QUERY_RUNNER = new QueryRunner(DbConnectionUtil.getDataSource());

    /**
     * 执行更新语句（包括：update、insert、delete）增查删
     */
    public int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            rows = QUERY_RUNNER.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    /**
     * 查询实体列表
     */
    public <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        try {
            entityList = QUERY_RUNNER.query(sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entityList;
    }
    /**
     * 查询实体单个
     */
    public <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        try {
            entity = QUERY_RUNNER.query(sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }
    /**
     * 插入实体
     * 服务层提供Customer.class和map
     */
    public <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            return false;
        }

        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " VALUES " + values;

        Object[] params = fieldMap.values().toArray();

        return executeUpdate(sql, params) == 1;
    }
    /**
     * 删除实体
     */
    public <T> boolean deleteEntity(Class<T> entityClass, long id) {
        String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id = ?";
        return executeUpdate(sql, id) == 1;
    }
    /**
     * 更新实体
     */
    public <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            return false;
        }

        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(" = ?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id = ?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;
    }

    private String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }





}
