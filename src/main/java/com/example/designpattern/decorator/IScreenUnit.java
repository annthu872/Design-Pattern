package com.example.designpattern.decorator;
import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
public interface IScreenUnit {
	public static final AnchorPane mScreen = new AnchorPane();
	public Parent getUI() throws IOException;
//	public AnchorPane getUI() throws IOException;

}

