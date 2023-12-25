package com.example.designpattern.table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;

import com.example.designpattern.column.Column;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	
	private String generateImports() {
        Set<String> imports = new HashSet<>();

        for (Column column : columnList) {
            if (column.isPrimaryKey()) {
                imports.add("import jakarta.persistence.Id;");
                imports.add("import jakarta.persistence.GeneratedValue;");
                imports.add("import jakarta.persistence.GenerationType;");
            }

            if (!column.isNullable()) {
                imports.add("import org.jetbrains.annotations.NotNull;");
            }
        }

        StringBuilder importBuilder = new StringBuilder();
        for (String importStmt : imports) {
            importBuilder.append(importStmt).append("\n");
        }
        importBuilder.append("\n");
        return importBuilder.toString();
    }
	
	public String generateSettersAndGetters() {
        StringBuilder methodBuilder = new StringBuilder();
        for (Column column : columnList) {
            methodBuilder.append(column.generateSetter());
            methodBuilder.append(column.generateGetter());
        }
        return methodBuilder.toString();
    }

	public String generateEntityClass() {
		StringBuilder classBuilder = new StringBuilder("package entity;\n\n");
		classBuilder.append(generateImports());

        classBuilder.append("public class " + tableName + " {\n\n");

        // Generate variables
        for (Column column : columnList) {
        	//classBuilder.append(column.generateAnnotations());
            classBuilder.append("    private ").append(column.getClassName()).append(" ").append(column.getFieldName()).append(";\n");
        }

	    // Generate no-argument constructor
	    classBuilder.append("\n    public ").append(tableName).append("() {\n    }\n\n");

	    // Generate constructor with parameters
	    classBuilder.append("    public ").append(tableName).append("(");
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
	    classBuilder.append("	}\n\n");

	    classBuilder.append(generateSettersAndGetters());

	    classBuilder.append("}");
	    return classBuilder.toString();
	}
	
	public String generateFormClass() {
        StringBuilder formClass = new StringBuilder("package form;\n\n");

        formClass.append("import entity." + tableName + ";\n\n");
        
        formClass.append("public class ")
                .append(tableName)
                .append("Form extends BaseForm<")
                .append(tableName)
                .append("> {\n");
        formClass.append("\tpublic ")
        		.append(tableName)
                .append("Form() {\n");
        formClass.append("\t\tsuper(")
        		.append(tableName)
                .append(".class);\n");
        formClass.append("\t}\n");

        formClass.append("}\n");

        return formClass.toString();
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
