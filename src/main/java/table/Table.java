package table;

import java.util.ArrayList;
import java.util.List;
import column.Column;
import column.ColumnBuilder;
import database.DatabaseConnector;

public class Table {
    private String tableName;
    private List<Column> columnDetails;
    private List<Object[]> observableList;
    private DatabaseConnector dbConnector;

    public Table(String tableName, DatabaseConnector dbConnector) {
        this.tableName = tableName;
        this.columnDetails = new ArrayList<>();
        this.observableList = new ArrayList<>();
        this.dbConnector = dbConnector;
    }

    public void fetchData() {
        List<List<Object>> columnsData = dbConnector.getColumnDetails(dbConnector.getCurrentDatabase(), tableName);
        System.out.println(tableName);
        if (columnsData != null) {
            for (List<Object> columnData : columnsData) {
                String columnName = (String) columnData.get(0);
                String dataType = (String) columnData.get(1);
                boolean isNullable = ((String) columnData.get(2)).equals("Allows Null");
                boolean isPrimaryKey = ((String) columnData.get(3)).equals("Primary Key");
                
                System.out.println("Column Name: " + columnName +
                        ", DataType: " + dataType +
                        ", Nullable: " + isNullable +
                        ", Primary Key: " + isPrimaryKey);

                Column column = new ColumnBuilder()
                        .setColumnName(columnName)
                        .setClassName(dataType)
                        .setNullable(isNullable)
                        .setPrimaryKey(isPrimaryKey)
                        .build();

                columnDetails.add(column);
            }
        }

        List<List<Object>> tableData = dbConnector.getColumnData(dbConnector.getCurrentDatabase(), tableName);
        if (tableData != null) {
            for (List<Object> rowData : tableData) {
                observableList.add(rowData.toArray());
                System.out.println(rowData);
            }
        }
    }

    public void addRow(Object[] rowData) {
        // Add row logic using JDBC INSERT query
    }

    public void updateRow(int rowIndex, Object[] updatedRowData) {
        // Update row logic using JDBC UPDATE query
    }

    public void removeRow(int rowIndex) {
        // Remove row logic using JDBC DELETE query
    }

    // Other methods to manipulate table data
    // ...
}
