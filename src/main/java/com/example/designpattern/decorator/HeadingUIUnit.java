package com.example.designpattern.decorator;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane ;
public class HeadingUIUnit extends ScreenUnitDecorator {

	public HeadingUIUnit(IScreenUnit screen) {
		super(screen);
	}

	@Override
	public Parent getownUI() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/Tab.fxml"));
		AnchorPane a = new AnchorPane();
		a.getChildren().add(loader.load());
		return a;
	}

}