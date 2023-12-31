package com.example.designpattern.Controller;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.example.designpattern.DesignpatternApplication;
import com.example.testbasicform.BaseForm;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
public class PopupWindow {
    public static void display(BaseForm bf){
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Parent root;
		try {
			FormPopupController controller = new FormPopupController(popupStage, bf, null);
			FXMLLoader loader = new FXMLLoader(DesignpatternApplication.class.getResource("/screen/AddForm.fxml"));
			loader.setController(controller);
			root = loader.load();
			String css = DesignpatternApplication.class.getResource("/css/style.css").toExternalForm();
	        Scene popupScene = new Scene(root);
	        popupScene.getStylesheets().add(css);
	        popupStage.setScene(popupScene);
	        popupStage.setTitle("Adding Form");
	        popupStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    public static void display(BaseForm bf,List<String> data){
    	Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Parent root;
		try {
			FormPopupController controller = new FormPopupController(popupStage, bf, data);
			FXMLLoader loader = new FXMLLoader(DesignpatternApplication.class.getResource("/screen/UpdateForm.fxml"));
			loader.setController(controller);
			root = loader.load();
			String css = DesignpatternApplication.class.getResource("/css/style.css").toExternalForm();
	        Scene popupScene = new Scene(root);
	        popupScene.getStylesheets().add(css);
	        popupStage.setScene(popupScene);
	        popupStage.setTitle("Updating Form");
	        popupStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void displaySetupTableForm() {
		Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Parent root;
		try {
			ChangeAuthenticationTableController controller = new ChangeAuthenticationTableController(popupStage);
			FXMLLoader loader = new FXMLLoader(DesignpatternApplication.class.getResource("/screen/UpdateForm.fxml"));
			loader.setController(controller);
			root = loader.load();
			String css = DesignpatternApplication.class.getResource("/css/style.css").toExternalForm();
	        Scene popupScene = new Scene(root);
	        popupScene.getStylesheets().add(css);
	        popupStage.setScene(popupScene);
	        popupStage.setTitle("Updating Form");
	        popupStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}