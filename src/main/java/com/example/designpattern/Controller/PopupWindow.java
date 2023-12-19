package com.example.designpattern.Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
public class PopupWindow {

    public static void display(String message) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);

        StackPane popupLayout = new StackPane();
        popupLayout.getChildren().add(new Label(message));
        Button button= new Button();
        popupLayout.getChildren().add(button);
//        button.setOnAction(e-> AddPopupWindow.display());
        Scene popupScene = new Scene(popupLayout, 300, 200);
        popupStage.setScene(popupScene);

        popupStage.setTitle("Popup Window");
        
        
        popupStage.showAndWait();
    }
}