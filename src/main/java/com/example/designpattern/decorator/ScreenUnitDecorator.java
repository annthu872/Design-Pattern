package com.example.designpattern.decorator;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane ;

public abstract class  ScreenUnitDecorator implements IScreenUnit{
	protected IScreenUnit mScreenUnit;
	

	public ScreenUnitDecorator(IScreenUnit screen) {
		this.mScreenUnit = screen;
	}
	public abstract Parent getownUI() throws IOException;
	
	public Parent getUI() throws IOException {
		System.out.println("1");
		
//		anchorPane.getChildren().add(this.getownUI());
		Parent root2 = mScreenUnit.getUI();
		Parent root1 = this.getownUI();
		Node childNode1 = root1.lookup("#aaa");
		mScreen.getChildren().add(childNode1);
		
//		Node childNode2 = root2.lookup("#aaa");
//		mScreen.getChildren().add(childNode2);
		
	    return mScreen;
	}
}



