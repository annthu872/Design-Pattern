package com.example.designpattern.Controller;

import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.DesignpatternApplication;
import com.example.designpattern.table.Table;
import com.example.tablehandler.TableGenFromDB;
import com.example.testbasicform.BaseForm;

import javafx.scene.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button; 
public class FormPopupController implements Initializable {

    @FXML
    private VBox addingPane;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;

    @FXML
    private Label titleLabel;
    private Stage stage;
    private List<TextField> textfieldList;
    private List<String> fieldnameList;
    private List<String> data;
    
    public FormPopupController(Stage stage, BaseForm bf, List<String> data) {
        this.fieldnameList = bf.getColumnNames();
        this.stage = stage;
        this.textfieldList = new ArrayList<TextField>();
        this.data = data;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addingPane.getChildren().clear();
		for ( int i =0; i< fieldnameList.size(); i++) {
			Parent root;
			try {
				root = FXMLLoader.load(DesignpatternApplication.class.getResource("/screen/FieldnameLine.fxml"));
				String css = DesignpatternApplication.class.getResource("/css/style.css").toExternalForm();
				root.getStylesheets().add(css);
				Label label = (Label)root.lookup("Label");
				textfieldList.add((TextField)root.lookup("TextField"));
				label.setText(fieldnameList.get(i)+" :");
				addingPane.getChildren().add(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(data != null && data.size() == fieldnameList.size()) {
			for ( int i =0; i< fieldnameList.size(); i++) {
				textfieldList.get(i).setText(data.get(i));
			}
		}
		btnCancel.setOnAction(e ->{
	        addingPane.getChildren().clear();

			this.stage.close();
		});
		
		btnAdd.setOnAction(e -> {
			List<String> fieldValues = new ArrayList<>();
		    for (TextField textField : textfieldList) {
		        fieldValues.add(textField.getText());
		    }

		    DatabaseConnection con = DatabaseConnection.getInstance();
		    List<Table> tables = con.getTablesWithColumns();

		    boolean isValid = false;
		    for (Table table : tables) {
		        isValid = table.validateUpdate(fieldValues);
		        if (isValid) {
		        	con.addRowToTable(TableGenFromDB.getInstance().getTableName(), fieldValues);
		            break;
		        }
		    }

		    con.close();

		    if (isValid) {
		        stage.close();
		    } else {
		    	//bao loi gi do
		    }
		});

	}
}
