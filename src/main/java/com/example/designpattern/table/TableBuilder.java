package com.example.designpattern.table;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.column.Column;

public class TableBuilder {
    private Table table;
    
    public TableBuilder() {
        this.table = new Table();
    }

    public TableBuilder setTableName(String tableName) {
        table.tableName = tableName;
        return this;
    }

    public TableBuilder setClassName(String className) {
        table.className = className;
        return this;
    }

    public TableBuilder addColumn(Column column) {
        table.columnList.add(column);
        return this;
    }

    public Table build() {
        return table;
    }
}