package com.example.designpattern.decorator;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public abstract class  ScreenUnitDecorator implements IScreenUnit{
	protected IScreenUnit mScreenUnit;
	

	public ScreenUnitDecorator(IScreenUnit screen) {
		this.mScreenUnit = screen;
	}
	public abstract Parent getownUI() throws IOException;
	
	public Parent getUI() throws IOException {
	  StackPane stackPane = new StackPane();
	  stackPane.getChildren().add(this.getownUI());
      stackPane.getChildren().add(mScreenUnit.getUI());
      return stackPane;
	}
}



