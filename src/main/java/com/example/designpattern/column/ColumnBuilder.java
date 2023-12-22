package com.example.designpattern.column;

public class ColumnBuilder {
    private Column column = new Column();

    public ColumnBuilder setClassName(String className) {
    	if (className.equalsIgnoreCase("INT") || className.equalsIgnoreCase("INTEGER")) {
            column.className = "Integer";
        } else if (className.equalsIgnoreCase("VARCHAR")) {
            column.className = "String";
        } else if (className.equalsIgnoreCase("BIGINT")) {
            column.className = "Long";
        } else if (className.equalsIgnoreCase("BOOLEAN")) {
            column.className = "Boolean";
        } else if (className.equalsIgnoreCase("BIT")) {
            column.className = "Boolean"; 
        } else if (className.equalsIgnoreCase("FLOAT")) {
            column.className = "Float"; 
        } else {
            column.className = "String";
        }
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
}