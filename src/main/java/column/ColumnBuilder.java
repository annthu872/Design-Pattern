package column;

public class ColumnBuilder {
    private Column result;
    
    public ColumnBuilder() {
    	result = new Column();
    }

    public ColumnBuilder reset() {
    	result.columnName = null;
    	result.className = null;
    	result.isAutoIncrement = false;
        result.isNullable = false;
        result.isPrimaryKey = false;
        return this;
    }

    public ColumnBuilder setColumnName(String columnName) {
    	result.columnName = columnName;
        return this;
    }

    public ColumnBuilder setClassName(String className) {
    	result.className = className;
        return this;
    }

    public ColumnBuilder setAutoIncrement(boolean autoIncrement) {
    	result.isAutoIncrement = autoIncrement;
        return this;
    }

    public ColumnBuilder setNullable(boolean nullable) {
    	result.isNullable = nullable;
        return this;
    }

    public ColumnBuilder setPrimaryKey(boolean primaryKey) {
    	result.isPrimaryKey = primaryKey;
        return this;
    }

    public Column build() {
        return result;
    }
}
