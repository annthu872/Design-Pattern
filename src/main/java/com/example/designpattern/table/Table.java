package com.example.designpattern.table;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;

import com.example.designpattern.column.Column;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Table implements Serializable {
	protected String tableName;
	protected String className;
	protected List<Column> columnList = new ArrayList<>();

	public Table() {
    }
	
	public void setTableName(String tableName) {
		this.tableName = tableName;	
	}
	
	public String getTableName() {
		return this.tableName;	
	}

	public String generateJavaClass() {
	    StringBuilder classBuilder = new StringBuilder("public class " + WordUtils.capitalize(tableName) + " {\n\n");

	    // Generate variables
	    for (Column column : columnList) {
	        classBuilder.append("    private ").append(column.getClassName()).append(" ").append(column.getFieldName()).append(";\n");
	    }

	    // Generate no-argument constructor
	    classBuilder.append("\n    public ").append(WordUtils.capitalize(tableName)).append("() {\n    }\n\n");

	    // Generate constructor with parameters
	    classBuilder.append("    public ").append(WordUtils.capitalize(tableName)).append("(");
	    for (int i = 0; i < columnList.size(); i++) {
	        Column column = columnList.get(i);
	        classBuilder.append(column.getClassName()).append(" ").append(column.getFieldName());
	        if (i < columnList.size() - 1) {
	            classBuilder.append(", ");
	        }
	    }
	    classBuilder.append(") {\n");
	    for (Column column : columnList) {
	        classBuilder.append("        this.").append(column.getFieldName()).append(" = ").append(column.getFieldName()).append(";\n");
	    }
	    classBuilder.append("    }\n\n");

	    for (Column column : columnList) {
	        classBuilder.append("    public void set").append(WordUtils.capitalize(column.getColumnName())).append("(")
	                .append(column.getClassName()).append(" ").append(column.getFieldName()).append(") {\n")
	                .append("        this.").append(column.getFieldName()).append(" = ").append(column.getFieldName()).append(";\n")
	                .append("    }\n");

	        classBuilder.append("    public ").append(column.getClassName()).append(" get").append(WordUtils.capitalize(column.getColumnName())).append("() {\n")
	                .append("        return this.").append(column.getFieldName()).append(";\n")
	                .append("    }\n\n");
	    }

	    classBuilder.append("}");
	    return classBuilder.toString();
	}

    public boolean addToDatabase() {
		return false;
    }
    
    public String toSql() {
		return null;
    }
	
	public boolean validateUpdate(List<String> values) {
        if (values.size() != columnList.size()) {
            return false;
        }

        for (int i = 0; i < columnList.size(); i++) {
            Column column = columnList.get(i);
            String value = values.get(i);

            if (!column.validateUpdate(value)) {
                return false;
            }
        }
        return true;
    }
	
	public String toString() {
        StringBuilder tableString = new StringBuilder("Table Name: " + tableName + "\n");
        for (Column column : columnList) {
            tableString.append(column.toString()).append("\n");
        }
        return tableString.toString();
    }
}
