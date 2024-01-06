package com.example.designpattern.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;
import com.example.designpattern.DatabaseConnection;
import com.example.designpattern.SharedVariableHolder;
import com.example.designpattern.Default.Authentication;
import com.example.designpattern.Default.IAuthentication;
import com.example.designpattern.authenticationScreenInterface.SignInControllerInterface;
import com.example.designpattern.notification.Notification;
import com.example.designpattern.notification.WarningNotification;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SetupTableColumnNameController implements Initializable{

	@FXML
    private ComboBox<String>CBPrimaryColumnName;


    @FXML
    private CheckBox CBoxSupportResetPassword;

    @FXML
    private ComboBox<String> CBpasswordColumn;

    @FXML
    private ComboBox<String> CBusernameColumn;

    @FXML
    private TextField txtResetPasswordTableName;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;
    
    @FXML
    private HBox NamedResetPasswordPane;
    
    private SignInControllerInterface signInController;
	DatabaseConnection connection = DatabaseConnection.getInstance();

    private String userTableName = "";
    private boolean changeResetPasswordTableName = false;
    private IAuthentication auth;
    public void setAuthentication(IAuthentication auth) {
    	this.auth = auth;
    }
	public SetupTableColumnNameController(String selectedTable) {
		this.userTableName = selectedTable;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<List<Object>> list =connection.getColumnNamesAndTypes(SharedVariableHolder.database, userTableName);
		String priamryKeyname = connection.getPrimaryKeyColumnNamesByTable(userTableName).get(0);
		Notification noti = new Notification();
//		noti.setMessage("Username is not Existed");
//		noti.setNotiType(new WarningNotification());
//		noti.display();
		changeResetPasswordTableName = connection.checkifTableNameExisted("users"); 
		if(!changeResetPasswordTableName) {
			NamedResetPasswordPane.setVisible(false);
		}
		
		CBPrimaryColumnName.setValue(priamryKeyname);
		CBusernameColumn.getItems().addAll(getListColumnNameByDatatype(list,"VARCHAR"));
		CBpasswordColumn.getItems().addAll(getListColumnNameByDatatype(list,"VARCHAR"));

		btnConfirm.setOnAction(e->{
			if(CBusernameColumn.getValue().equals("") ||CBpasswordColumn.getValue().equals("")) {
				noti.setMessage("Username Column and Password Column must be not be null");
				noti.setNotiType(new WarningNotification());
				noti.display();
			}
			else if(CBusernameColumn.getValue().equals(CBpasswordColumn.getValue())) {
				noti.setMessage("Username Column and Password Column must be segregated");
				noti.setNotiType(new WarningNotification());
				noti.display();
			}
			else if(changeResetPasswordTableName&& connection.checkifTableNameExisted(this.txtResetPasswordTableName.getText())) {
				noti.setMessage("This reset password table's name already existed in database, please choose another one ");
				noti.setNotiType(new WarningNotification());
				noti.display();
			}
			else {
				auth.setTableName(userTableName);
				auth.setPrimaryKeyName(priamryKeyname);
				auth.setPrimaryKeyDatatype(getTypeofColumnNameFromColumnList(list,priamryKeyname));
				auth.setPasswordColumnName(CBpasswordColumn.getValue());
				auth.setUsernameColumnName(CBusernameColumn.getValue());
				
				if(changeResetPasswordTableName) {
					auth.setResetPasswordTable(this.txtResetPasswordTableName.getText());
					}
				auth.createResetPasswordTable();
				Parent root = null;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
				loader.setController(signInController);
				try {
					root = loader.load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
				Scene scene = new Scene(root);
				String css = this.getClass().getResource("/css/style.css").toExternalForm();
				scene.getStylesheets().add(css);
				stage.setScene(scene);
				
				//gen code
			}
		});

		btnBack.setOnAction(e->{
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ChooseUserTableInDatabase.fxml"));
				ChooseUserTableController controller = new ChooseUserTableController();
				controller.setAuthentication(auth);
				loader.setController(controller);
				root = loader.load();
//				root = FXMLLoader.load(getClass().getResource("/screen/SignIn.fxml"));
				Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
				Scene scene = new Scene(root);
				String css = this.getClass().getResource("/css/style.css").toExternalForm();
				scene.getStylesheets().add(css);
				stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	public String getTypeofColumnNameFromColumnList(List<List<Object>> list, String columnName) {
		for (List<Object> pair : list) {
            if (pair.size() == 2 && pair.get(0) instanceof String && pair.get(0).equals(columnName)) {
                // Match found, return the datatype
                try {
					try {
						return auth.SqlTypetoJavaType((String) pair.get(1));
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
	
	public void setSignInController(SignInControllerInterface signInController) {
		// TODO Auto-generated method stub
		this.signInController = signInController;
	}
	

}
