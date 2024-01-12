package com.example.designpattern.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.Default.Authentication;
import com.example.designpattern.Default.IAuthentication;
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

public class ChooseUserTableController{

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnDefault;

    @FXML
    private ComboBox<String> comboBox;
    DatabaseConnection connection = DatabaseConnection.getInstance();
    IAuthentication auth;
    public ChooseUserTableController(IAuthentication auth){
    	this.auth = auth;
    }
    @FXML
    private void initialize() {
        List<String> tableList = connection.getTableList(SharedVariableHolder.database);
        comboBox.getItems().addAll(tableList);
        System.out.println(tableList);
		Notification noti = new Notification();

        btnConfirm.setOnAction(e -> {
        	String selectedTable = comboBox.getValue();
            System.out.println("Table: "+selectedTable);
            if(		connection.getPrimaryKeyColumnNamesByTable(selectedTable).size() != 1) {
            	noti.setMessage("Table for reset authentication name must be have only 1 primary key column, Please choose another table ");
				noti.setNotiType(new WarningNotification());
				noti.display();
            }
            else if (!connection.checkTableIntandAutoIncrementPrimaryKey(selectedTable)) {
            	noti.setMessage("Table for reset authentication primary key must be int value and auto_incresement");
				noti.setNotiType(new WarningNotification());
				noti.display();
            }
            else {
                Parent root;
            	try {
    				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SetupTableColumnName.fxml"));
    				SetupTableColumnNameController controller = new SetupTableColumnNameController(selectedTable, auth);
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
        	
        	if(connection.checkifTableNameExisted("users")||connection.checkifTableNameExisted("resetpassword")) {
        		new PopupWindow().displaySetupTableForm(auth,(Stage)(((Node)e.getSource()).getScene().getWindow()));
        	}
        	else {
            	try {
					auth.createDefaultUserTableToDatabase(User.class);
					//gen code
		        	try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ChooseGeneratedProjectLocation.fxml"));
						ChooseGeneratedProjectLocationController controller = new ChooseGeneratedProjectLocationController();
		     			loader.setController(controller);
		     			Parent root = loader.load();
		     			Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
		     			Scene scene = new Scene(root);
		     			String css = this.getClass().getResource("/css/style.css").toExternalForm();
		     			scene.getStylesheets().add(css);
		     			stage.setScene(scene);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        });
    }

   
}
