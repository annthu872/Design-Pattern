package com.example.designpattern.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.Default.Authentication;
import com.example.designpattern.Default.User;
import com.example.designpattern.notification.Notification;
import com.example.designpattern.notification.WarningNotification;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ChooseUserTableController {

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnDefault;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private void initialize() {
    	Authentication a = Authentication.getInstance();
        DatabaseConnection con = DatabaseConnection.getInstance();
        List<String> tableList = con.getTableList(SharedVariableHolder.database);
        comboBox.getItems().addAll(tableList);
        System.out.println(tableList);
		Notification noti = new Notification();

        btnConfirm.setOnAction(e -> {
        	String selectedTable = comboBox.getValue();
            System.out.println("Table: "+selectedTable);
            if(		DatabaseConnection.getInstance().getPrimaryKeyColumnNamesByTable(selectedTable).size() != 1) {
            	noti.setMessage("Table for reset authentication name must be have only 1 primary key column, Please choose another table ");
				noti.setNotiType(new WarningNotification());
				noti.display();
            }
            else {
                Parent root;
            	try {
    				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SetupTableColumnName.fxml"));
    				SetupTableColumnNameController controller = new SetupTableColumnNameController(selectedTable);
    				loader.setController(controller);
    				root = loader.load();
    				Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
    				Scene scene = new Scene(root);
    				String css = this.getClass().getResource("/css/style.css").toExternalForm();
    				scene.getStylesheets().add(css);
    				stage.setScene(scene);
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
            }
			
        });
        btnDefault.setOnAction(e->{
        	
        	if(a.checkifUserTableNameExisted()||a.checkifResetPassTableNameExisted()) {
        		new PopupWindow().displaySetupTableForm();
        	}
        	else {
            	try {
					a.createDefaultUserTableToDatabase(User.class);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
//gen code
        });
    }

   
}
