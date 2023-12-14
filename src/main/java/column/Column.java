package column;

public class Column {
    protected String columnName;
    protected String className;
    protected boolean isAutoIncrement;
    protected boolean isNullable;
    protected boolean isPrimaryKey;

    public boolean processUpdate() {
        
        return true;
    }
}
