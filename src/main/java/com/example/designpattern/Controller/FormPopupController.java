package com.example.designpattern.Controller;

import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.designpattern.DesignpatternApplication;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
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
    private  List<String> data;
    public FormPopupController(Stage stage, List<String> fieldnameList, List<String> data) {
        this.fieldnameList = new ArrayList<>(fieldnameList);
        this.stage = stage;
        this.textfieldList = new ArrayList<TextField>();
        this.data = data;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
			this.stage.close();
		});
	}
}
