package com.example.designpattern.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.tablehandler.TableGenFromDB;
import com.example.tablehandler.TableUIHandle;
import com.example.testbasicform.ActorForm;
import com.example.testbasicform.BaseForm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.*;
public class TableDetailButtonController implements Initializable{

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;
    
    private BaseForm bf;
    public void setForm(BaseForm bf){
    	this.bf = bf;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnAdd.setOnAction(e -> PopupWindow.display(this.bf));
//		btnEdit.setOnAction(e -> PopupWindow.display(TableUIHandle.getInstance().getColumnNames(),TableGenFromDB.getInstance().getFieldname()));
	}
}
