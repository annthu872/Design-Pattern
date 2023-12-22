package com.example.designpattern.column;

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

    private String getPackageName() {
        String[] tokens = className.split("\\.");
        StringBuilder packageName = new StringBuilder();
        for (int i = 0; i < tokens.length - 1; i++) {
            if (i != 0) {
                packageName.append(".");
            }
            packageName.append(tokens[i]);
        }
        return packageName.toString();
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

    public String generateAnnotations() {
        StringBuilder annotationBuilder = new StringBuilder("@NotNull\n");
        if (isPrimaryKey) {
            annotationBuilder.append("@Id\n");
            annotationBuilder.append("@GeneratedValue(strategy = GenerationType.IDENTITY)\n");
        }

        return annotationBuilder.toString();
    }

    public String toSql() {
		return null;
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
