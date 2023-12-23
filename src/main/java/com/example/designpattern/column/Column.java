package com.example.designpattern.column;

import org.apache.commons.lang3.text.WordUtils;

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
    
    public boolean validateUpdate(String value) {
        if (!isNullable && value == null) {
            return false;
        }
        
        switch (className) {
        case "Integer":
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return false;
            }
            break;
        case "Long":
            try {
                Long.parseLong(value);
            } catch (NumberFormatException e) {
                return false;
            }
            break;
        case "Float":
            try {
                Float.parseFloat(value);
            } catch (NumberFormatException e) {
                return false;
            }
            break;
        default:
            break;
        }
        return true;
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
