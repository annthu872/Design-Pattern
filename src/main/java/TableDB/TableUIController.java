package TableDB;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableUIController implements Initializable {
	@FXML
	private TableView<ObservableList<String>> myTable;
	
	private class Column {
		String name;
		List<String> data = new ArrayList<String>();
		
		Column(String a){
			name = a;
			data.add(a);
			data.add("abc");
			data.add("def");
		}

		public String getName() {
			return name;
		}
		
		public List<String> getData() {
            return data;
        }
		public ObservableList<String> getdata(){
			return FXCollections.observableList(data);
		}
	}
	
	private List<Column> colList = new ArrayList<Column>();
	
	public void initialize(URL url, ResourceBundle rb) {
		
		colList.add(new Column("dfggfyugusghuhfuhau"));
		colList.add(new Column("def"));
		int colIndex = 1;
		
		for(Column column : colList) {
			final int columnIndex = colIndex;
			TableColumn<Column, String> columnTable = new TableColumn<>(column.getName());
			//myTable.getColumns().add(columnTable);
			colIndex +=1;
		}
		autoResizeColumns(myTable);
		
	}
	
	
	
	
	public static void autoResizeColumns( TableView<?> table )
	{
	    //Set the right policy
	    table.setColumnResizePolicy( TableView.UNCONSTRAINED_RESIZE_POLICY);
	    table.getColumns().stream().forEach( (column) ->
	    {
	        //Minimal width = columnheader
	        Text t = new Text( column.getText() );
	        double max = t.getLayoutBounds().getWidth();
	        for ( int i = 0; i < table.getItems().size(); i++ )
	        {
	            //cell must not be empty
	            if ( column.getCellData( i ) != null )
	            {
	                t = new Text( column.getCellData( i ).toString() );
	                double calcwidth = t.getLayoutBounds().getWidth();
	                //remember new max-width
	                if ( calcwidth > max )
	                {
	                    max = calcwidth;
	                }
	            }
	        }
	        //set the new max-widht with some extra space
	        column.setPrefWidth( max + 10.0d );
	    } );
	}
}
