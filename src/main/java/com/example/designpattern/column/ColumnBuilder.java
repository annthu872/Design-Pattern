package com.example.designpattern.column;

import java.util.HashMap;
import java.util.Map;

public class ColumnBuilder {
    private Column column = new Column();

    public ColumnBuilder setClassName(String className) {
    	column.className =SQLTypeMapper.mapSQLTypeToJavaType(className);
        return this;
    }

    public ColumnBuilder setColumnName(String columnName) {
        column.columnName = columnName;
        return this;
    }

    public ColumnBuilder setAutoIncrement(boolean isAutoIncrement) {
        column.isAutoIncrement = isAutoIncrement;
        return this;
    }

    public ColumnBuilder setNullable(boolean isNullable) {
    	column.isNullable = isNullable;
        return this;
    }

    public ColumnBuilder setIsPrimaryKey(boolean isPrimaryKey) {
    	column.isPrimaryKey = isPrimaryKey;
        return this;
    }

    public ColumnBuilder setFieldName(String fieldName) {
    	column.fieldName = fieldName;
        return this;
    }

    public Column build() {
        return column;
    }
    
    public class SQLTypeMapper {
        private static final Map<String, String> typeMap = new HashMap<>();

        static {
            typeMap.put("BIT", "Boolean");
            typeMap.put("TINYINT", "Integer");
            typeMap.put("TINYINT UNSIGNED", "Integer");
            typeMap.put("SMALLINT", "Integer");
            typeMap.put("SMALLINT UNSIGNED", "Integer");
            typeMap.put("INTEGER", "Integer");
            typeMap.put("INTEGER UNSIGNED", "Integer");
            typeMap.put("BIGINT", "Integer");
            typeMap.put("BIGINT UNSIGNED", "Integer");
            typeMap.put("REAL", "Float");
            typeMap.put("FLOAT", "Double");
            typeMap.put("DOUBLE", "Double");
//            typeMap.put("DATE", "java.sql.Date");
//            typeMap.put("TIME", "java.sql.Time");
            typeMap.put("TIMESTAMP", "Timestamp");
        }

        public static String mapSQLTypeToJavaType(String sqlType) {
            return typeMap.getOrDefault(sqlType.toUpperCase(), "String");
        }
    }
}