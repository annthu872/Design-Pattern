package com.example.designpattern.table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;

import com.example.designpattern.column.Column;
import com.example.designpattern.notification.*;

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
            
            if (!column.isTimestamp()) {
            	imports.add("import java.sql.Timestamp;");
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
		StringBuilder classBuilder = new StringBuilder("package com.example.testbasicform;\n\n");
		classBuilder.append(generateImports());

        classBuilder.append("public class " + tableName + " {\n\n");

        // Generate variables
        for (Column column : columnList) {
        	classBuilder.append(column.generateAnnotations());
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
	
	public String validateTypeAndNotNull(List<String> values) {
		String message = "";
		for (int i = 0; i < columnList.size(); i++) {
            Column column = columnList.get(i);
            String value = values.get(i);

            if (!column.validateUpdate(value).equals("")) {
            	message += column.validateUpdate(value);
            }
        }
        
        return message;
    }
	
	public String validatePrimaryKeyUpdate(Class object, List<String> oldValues, List<String> newValues) {
	    for (int i = 0; i < columnList.size(); i++) {
	        Column column = columnList.get(i);
	        if (column.isPrimaryKey()) {
	            String oldValue = oldValues.get(i);
	            String newValue = newValues.get(i);
	            
	            // Check if the primary key value has changed
	            if (!oldValue.equals(newValue)) {
	                // Get the field name using reflection and check the annotation to verify if it's a primary key
	                try {
	                    java.lang.reflect.Field field = object.getDeclaredField(column.getFieldName());
	                    field.setAccessible(true);
	                    jakarta.persistence.Id annotation = field.getAnnotation(jakarta.persistence.Id.class);
	                    if (annotation != null) {
	                        return "Can not change value of " + column.getClassName(); 
	                    }
	                } catch (NoSuchFieldException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	    return "";
	}

	public boolean validateAdd(List<String> values) {
		String message = validateTypeAndNotNull(values);
		if(!message.equals("")) {
			Notification noti = new Notification();
	        noti.setMessage("Invalid input!\n" + message);
	        noti.setNotiType(new ErrorNotification());
	        noti.display();
	        return false;
		}
		
		Notification noti = new Notification();
        noti.setMessage("Add success!");
        noti.setNotiType(new InformationNotification());
        noti.display();

		return true;
	}
	
	public boolean validateUpdate(Class object, List<String> oldValues, List<String> newValues) {
		String messageValidateTypeAndNotNull = validateTypeAndNotNull(newValues);
		if(!messageValidateTypeAndNotNull.equals("")) {
			Notification noti = new Notification();
	        noti.setMessage("Invalid input!\n" + messageValidateTypeAndNotNull);
	        noti.setNotiType(new ErrorNotification());
	        noti.display();
	        return false;
		}
		
		String messageValidatePrimaryKeyUpdate = validatePrimaryKeyUpdate(object, oldValues, newValues);
		if(!messageValidatePrimaryKeyUpdate.equals("")) {
			Notification noti = new Notification();
	        noti.setMessage("Can not change primary key value!\n" + messageValidatePrimaryKeyUpdate);
	        noti.setNotiType(new ErrorNotification());
	        noti.display();
	        return false;
		}
		
		Notification noti = new Notification();
        noti.setMessage("Add success!");
        noti.setNotiType(new InformationNotification());
        noti.display();

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
