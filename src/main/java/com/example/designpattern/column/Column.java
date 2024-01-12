package com.example.designpattern.column;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;;

public class Column {
	protected String className;
    protected String columnName;
    protected boolean isAutoIncrement;
    protected boolean isNullable;
    protected boolean isPrimaryKey;
    protected String fieldName;

    public Column() {
        // Constructor
    }

    public String getSimpleClassName() {
        String[] tokens = className.split("\\.");
        return tokens[tokens.length - 1];
    }
    
	public String getColumnName() {
		return this.columnName;
	}

	public String getClassName() {
		return this.className;
	}

    public String getFieldName() {
        return columnName.replaceAll("\\s+", "");
    }
    
    public boolean isNullable() {
        return isNullable;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }
    
    public boolean isTimestamp() {
        return className.equals("Timestamp");
    }

    public String generateAnnotations() {
        StringBuilder annotationBuilder = new StringBuilder("	@NotNull\n");
        if (isPrimaryKey) {
            annotationBuilder.append("	@Id\n");
            annotationBuilder.append("	@GeneratedValue(strategy = GenerationType.IDENTITY)\n");
        }
        return annotationBuilder.toString();
    }
    
    public String generateSetter() {
        return "\t" + "public void set" + columnName + "(" + className + " " + getFieldName() + ") {\n"
                + "\t\t" + "this." + getFieldName() + " = " + getFieldName() + ";\n\t" + "}\n";
    }

    public String generateGetter() {
        return "\t" + "public " + className + " get" + columnName + "() {\n"
        		+ "\t\t" + "return this." + getFieldName() + ";\n\t" + "}\n\n";
    }
    
    public String validateUpdate(String value) {
        if (!isNullable && value == null) {
            return "Value cannot be null for column: " + columnName;
        }
        
        switch (className) {
            case "Integer":
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return "Invalid Integer value for column: " + columnName;
                }
                break;
            case "Long":
                try {
                    Long.parseLong(value);
                } catch (NumberFormatException e) {
                    return "Invalid Long value for column: " + columnName;
                }
                break;
            case "Float":
                try {
                    Float.parseFloat(value);
                } catch (NumberFormatException e) {
                    return "Invalid Float value for column: " + columnName;
                }
                break;
            case "Timestamp":
            	String timestampString = value;
            	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            	try {
            	    Date parsedDate = dateFormat.parse(timestampString);
            	    Timestamp timestamp = new Timestamp(parsedDate.getTime());
            	} catch (Exception e) {
            		return "Invalid Timestamp value for column: " + columnName;
            	}
                break;
            default:
                break;
        }
        return "";
    }
    
    public String toString() {
        StringBuilder columnString = new StringBuilder("Column Name: " + columnName + "\n");
        columnString.append("Class Name: ").append(className).append("\n");
        columnString.append("Is Auto Increment: ").append(isAutoIncrement).append("\n");
        columnString.append("Is Nullable: ").append(isNullable).append("\n");
        columnString.append("Is Primary Key: ").append(isPrimaryKey).append("\n");
        return columnString.toString();
    }
}
