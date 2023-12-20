package com.example.designpattern;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NotificationController {
	@FXML
	private Label notiType;
	@FXML
	private Label message;
	private String cssLink;
	@FXML
	private Button okButton;
	
    public void setLabelText(String notiTypeText, String messageText) {
        notiType.setText(notiTypeText);
        message.setText(messageText);
    }
	
	@FXML
	public void initialize() {
		notiType.setText("Error message");
		message.setText("This column can not be null");
		okButton.setOnAction(e -> {
            System.out.println("OK button clicked!");
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        });
	}
    
    public static void display() {
    	Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Parent root;
		try {
			
			FXMLLoader loader = new FXMLLoader(DesignpatternApplication.class.getResource("/screen/NotificationDialog.fxml"));
			root = loader.load();
			
			Stage ownerStage = DesignpatternApplication.getPrimaryStage();
			BoxBlur blur = new BoxBlur(5, 5, 5);
	            if (ownerStage != null) {
	                ownerStage.getScene().getRoot().setEffect(blur);
	                popupStage.setOnHidden(event -> {
	                    ownerStage.getScene().getRoot().setEffect(null);
	                });
	            } else {
	                System.err.println("Primary stage is null. Check the initialization order.");
	            }
			
	        Scene popupScene = new Scene(root);
	        popupStage.setScene(popupScene);
	        popupStage.setTitle("IMPORTANT MESSAGE");
	        popupStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
