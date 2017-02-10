package com.weibo.ad.example.anno;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class POJOColPropHolder {
    private static Map<String, Map<String, String>> _propholder = null;
    private static Map<String, Map<String, PropertyDescriptor>> _typeHolder = null;
    private static POJOColPropHolder _instance = new POJOColPropHolder();

    public static POJOColPropHolder getInstance() {
        return _instance;
    }

    private POJOColPropHolder() {

    }

    public synchronized Map<String, String> getColumnPropMap(Class clazz) {
        String className = clazz.toString();
        if(_propholder.get(className) == null) {
            initial(clazz, className);
        }
        return _propholder.get(className);
    }

    public synchronized Map<String, PropertyDescriptor> getColumnType(Class clazz) {
        String className = clazz.toString();
        if(_typeHolder.get(className) == null) {
            initial(clazz, className);
        }
        return _typeHolder.get(className);
    }

    private synchronized void initial(Class clazz, String className) {
        Map<String, String> pojoMap = new HashMap<String, String>();
        Map<String, PropertyDescriptor> typeMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields) {
            AdColumn c = f.getAnnotation(AdColumn.class);
            if(c != null ) {
                String colName = (c.columnName().equals("")) ? f.getName() : c.columnName();
                pojoMap.put(colName/* column name*/, f.getName()/*prop name*/);
                Class<?> type = f.getType();
                try {
                    typeMap.put(colName, new PropertyDescriptor(f.getName(), clazz));
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
            }
        }
        _propholder.put(className, pojoMap);
        _typeHolder.put(className, typeMap);
    }
}
