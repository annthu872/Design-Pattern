package com.example.designpattern.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;
import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.Default.Authentication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class SetupTableColumnNameController implements Initializable{

	@FXML
    private ComboBox<String>CBPrimaryColumnName;
    @FXML
    private ComboBox<String> CBactiveColumn;

    @FXML
    private CheckBox CBoxSupportResetPassword;

    @FXML
    private ComboBox<String> CBpasswordColumn;

    @FXML
    private ComboBox<String> CBusernameColumn;

    @FXML
    private CheckBox CheckboxAddActiveColumn;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;
    
    private String userTableName = "";
    
    private Authentication auth = Authentication.getInstance();
	public SetupTableColumnNameController(String selectedTable) {
		this.userTableName = selectedTable;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<List<Object>> list = DatabaseConnection.getInstance().getColumnNamesAndTypes(SharedVariableHolder.database, userTableName);
		String priamryKeyname = DatabaseConnection.getInstance().getPrimaryKeyColumnNamesByTable(userTableName).get(0);
		
		CBPrimaryColumnName.setValue(priamryKeyname);
		CBusernameColumn.getItems().addAll(getListColumnNameByDatatype(list,"VARCHAR"));
		CBpasswordColumn.getItems().addAll(getListColumnNameByDatatype(list,"VARCHAR"));
		CBactiveColumn.getItems().addAll(getListColumnNameByDatatype(list,"BIT"));
		
		btnConfirm.setOnAction(e->{
			auth.setPrimaryKeyDatatype(priamryKeyname);
			auth.setPrimaryKeyDatatype(getTypeofColumnNameFromColumnList(list,priamryKeyname));
		});
		// TODO Auto-generated method stub
//		for (List<Object> pair : list) {
//            if (pair.size() == 2 && pair.get(0) instanceof String && pair.get(0).equals("")) {
//                // Match found, return the datatype
//                auth.setPrimaryKeyDatatype(auth.SqlTypetoJavaType((String) pair.get(1)));
//            }
//        }
		
	}
	public String getTypeofColumnNameFromColumnList(List<List<Object>> list, String columnName) {
		for (List<Object> pair : list) {
            if (pair.size() == 2 && pair.get(0) instanceof String && pair.get(0).equals(columnName)) {
                // Match found, return the datatype
                try {
					try {
						System.out.println(auth.SqlTypetoJavaType((String) pair.get(1)));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }		
		return null;
	}
	public List<String> getListColumnNameByDatatype(List<List<Object>> list, String datatype) {
		List<String> namesWithDatatype = new ArrayList<String>() ;

        for (List<Object> pair : list) {
            if (pair.size() == 2 && pair.get(1) instanceof String && pair.get(1).equals(datatype)) {
                // Datatype matches, add the corresponding name to the list
                namesWithDatatype.add((String) pair.get(0));
            }
        }

        return namesWithDatatype;
    }
	

}
