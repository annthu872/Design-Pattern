package com.example.designpattern.Controller;

import java.io.IOException;
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
public class AddingFormPopupController implements Initializable {

    @FXML
    private VBox addingPane;
    @FXML
    private Button btnCancel;
    private Stage stage;
    private List<String> fieldnameList;
    public AddingFormPopupController(Stage stage, List<String> fieldnameList) {
        this.fieldnameList = new ArrayList<>(fieldnameList);
        this.stage = stage;
    }
    private List<Button> tableListButton = new ArrayList<Button>();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for ( int i =0; i< fieldnameList.size(); i++) {
			Parent root;
			try {
				root = FXMLLoader.load(DesignpatternApplication.class.getResource("/screen/FieldnameLine.fxml"));
				String css = DesignpatternApplication.class.getResource("/css/style.css").toExternalForm();
				root.getStylesheets().add(css);
				Label label = (Label)root.lookup("Label");
				label.setText(fieldnameList.get(i)+" :");
				addingPane.getChildren().add(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		btnCancel.setOnAction(e ->{
			this.stage.close();
		});
	}
	
//	public List<String> getTables(){
//		List<String> tableList = new ArrayList<>();
//		tableList.add("Cao Nguyên");
//		tableList.add("Nguyễn Duy");
//		tableList.add("TRương toàn thịnh");
//		tableList.add("Thư");
//		tableList.add("Thảo");
//		tableList.add("Cao Nguyên 1");
//		tableList.add("Nguyễn Duy 2");
//		tableList.add("TRương toàn thịnh 3");
//		tableList.add("Thư 4");
//		tableList.add("Thảo 5");
//		tableList.add("Cao Nguyên 2");
//		tableList.add("Nguyễn Duy 12");
//		tableList.add("TRương toàn thịnh TRương toàn thịnh");
//		tableList.add("Thư 1231");
//		tableList.add("Thảo 532");
//
//		return tableList;
//	}

}
