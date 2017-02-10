package com.weibo.ad.example.anno;

import org.springframework.jdbc.core.RowMapper;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AdRowMapper<T> implements RowMapper {

    private Class<T> _clazz;

    public AdRowMapper(Class<T> clazz) {
        _clazz = clazz;
    }

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        for(int i=1; i < cols; i++) {
            String colName = rsmd.getColumnName(i);
            PropertyDescriptor pd = POJOColPropHolder.getInstance().getColumnType(_clazz).get(colName);

        }
        return null;
    }



    protected String column2Prop(String columnName) {
        String name = POJOColPropHolder.getInstance().getColumnPropMap( _clazz ).get(columnName);
        if(name != null) {
            return name;
        } else {
            throw new IllegalArgumentException("columnName not found! ");
        }
    }
}
