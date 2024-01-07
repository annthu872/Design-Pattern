package com.example.designpattern.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.DesignpatternApplication;
import com.example.designpattern.Default.Authentication;
import com.example.designpattern.Default.IAuthentication;
import com.example.designpattern.Default.User;
import com.example.designpattern.notification.Notification;
import com.example.designpattern.notification.WarningNotification;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeAuthenticationTableController implements Initializable {

    @FXML
    private VBox addingPane;


    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdate;

    private Stage stage;
    private  TextField userTableTextField;
    private TextField forgotPasswordTextField;
    private boolean tfusertable = false;
    private boolean tfresetpasswordtable = false;
	DatabaseConnection connection = DatabaseConnection.getInstance();
    IAuthentication auth;
    public ChangeAuthenticationTableController(Stage stage, IAuthentication auth) {
        this.stage = stage;
        this.auth = auth;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addingPane.getChildren().clear();
		
		tfusertable = connection.checkifTableNameExisted("users");
		tfresetpasswordtable = connection.checkifTableNameExisted("ResetPassword");
		
		System.out.println("tfusertable"+ tfusertable);
		System.out.println("tfresetpasswordtable"+ tfresetpasswordtable);

		if(tfusertable) {
			Parent root;
			try {
				root = FXMLLoader.load(DesignpatternApplication.class.getResource("/screen/FieldnameLine2.fxml"));
				String css = DesignpatternApplication.class.getResource("/css/style.css").toExternalForm();
				root.getStylesheets().add(css);
				Label label = (Label)root.lookup("Label");
				userTableTextField = (TextField)root.lookup("TextField");
				label.setText("Table for user authentication named:");
				addingPane.getChildren().add(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		if(tfresetpasswordtable) {
			Parent root;
			try {
				root = FXMLLoader.load(DesignpatternApplication.class.getResource("/screen/FieldnameLine2.fxml"));
				String css = DesignpatternApplication.class.getResource("/css/style.css").toExternalForm();
				root.getStylesheets().add(css);
				Label label = (Label)root.lookup("Label");
				forgotPasswordTextField = (TextField)root.lookup("TextField");
				label.setText("Table for reset authentication named:");
				addingPane.getChildren().add(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		btnCancel.setOnAction(e ->{
	        addingPane.getChildren().clear();
			this.stage.close();
		});
		
		if(btnUpdate != null) {
			btnUpdate.setOnAction(e->{
				Notification noti = new Notification();
//				noti.setMessage("Username already exist, please choose another username");
//				noti.setNotiType(new WarningNotification());
//				noti.display();
				if(this.tfresetpasswordtable && this.tfusertable) {
					if(this.userTableTextField.getText().equals("")||this.userTableTextField.getText() == null) {
						noti.setMessage("Table for user authentication name must be filled ");
						noti.setNotiType(new WarningNotification());
						noti.display();
					}
					else if(connection.checkifTableNameExisted(this.userTableTextField.getText())) {
						noti.setMessage("This authentication name already existed, please change another name ");
						noti.setNotiType(new WarningNotification());
						noti.display();
					}
					else if(this.forgotPasswordTextField.getText().equals("")||this.forgotPasswordTextField.getText() == null) {
						noti.setMessage("Table for reset authentication name must be filled ");
						noti.setNotiType(new WarningNotification());
						noti.display();
					}
					else if(connection.checkifTableNameExisted(this.forgotPasswordTextField.getText())) {
						noti.setMessage("This resetpassword name already existed, please change another name ");
						noti.setNotiType(new WarningNotification());
						noti.display();
					}
					else {
						auth.setTableName(this.userTableTextField.getText());
						auth.setResetPasswordTable(this.forgotPasswordTextField.getText());
						try {
							auth.createDefaultUserTableToDatabase(User.class);
							//gen code
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ChooseGeneratedProjectLocation.fxml"));
							ChooseGeneratedProjectLocationController controller = new ChooseGeneratedProjectLocationController();
			     			loader.setController(controller);
			     			Parent root = loader.load();
			     			Stage stage = new Stage();
			     			Scene scene = new Scene(root);
			     			String css = this.getClass().getResource("/css/style.css").toExternalForm();
			     			scene.getStylesheets().add(css);
			     			stage.setScene(scene);
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						stage.close();
					}
				}
				else if(this.tfusertable) {
					if(this.userTableTextField.getText().equals("")||this.userTableTextField.getText() == null) {
						noti.setMessage("Table for user authentication name must be filled ");
						noti.setNotiType(new WarningNotification());
						noti.display();
					}
					else if(connection.checkifTableNameExisted(this.userTableTextField.getText())) {
						noti.setMessage("This authentication name already existed, please change another name ");
						noti.setNotiType(new WarningNotification());
						noti.display();
					}
					else {
						auth.setTableName(this.userTableTextField.getText());
						try {
							auth.createDefaultUserTableToDatabase(User.class);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						stage.close();
					}
				}
				else  {
					if(this.forgotPasswordTextField.getText().equals("")||this.forgotPasswordTextField.getText() == null) {
						noti.setMessage("Table for reset authentication name must be filled ");
						noti.setNotiType(new WarningNotification());
						noti.display();
					}
					else if(connection.checkifTableNameExisted(this.forgotPasswordTextField.getText())) {
						noti.setMessage("This resetpassword name already existed, please change another name ");
						noti.setNotiType(new WarningNotification());
						noti.display();
					}
					else {
						auth.setResetPasswordTable(this.forgotPasswordTextField.getText());
						try {
							auth.createDefaultUserTableToDatabase(User.class);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						stage.close();	
					}
				}
			});
		}

		
	}
}
