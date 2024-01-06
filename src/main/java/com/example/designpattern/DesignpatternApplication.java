package com.example.designpattern;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.designpattern.Controller.*;
import com.example.designpattern.decorator.*;
import com.example.designpattern.decorator.TableDetailButton;
import com.example.designpattern.filegenerator.FileGenerator;
import com.example.designpattern.table.Table;
import com.example.tablehandler.TableGenFromDB;
import com.example.designpattern.Default.*;
import com.example.designpattern.authenticationScreenInterface.IoCContainer;
import com.example.designpattern.authenticationScreenInterface.ResetPassword1ControllerInterface;
import com.example.designpattern.authenticationScreenInterface.ResetPassword2ControllerInterface;
import com.example.designpattern.authenticationScreenInterface.SignInControllerInterface;
import com.example.designpattern.authenticationScreenInterface.SignUpControllerInterface;




public class DesignpatternApplication extends Application {
	private final IoCContainer container = new IoCContainer();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			container.register(SignInControllerInterface.class, SignInController.class);
			container.register(SignUpControllerInterface.class, SignUpController.class);
			container.register(ResetPassword1ControllerInterface.class, ResetPasswordController.class);
			container.register(ResetPassword2ControllerInterface.class, ResetPasswordController2.class);
			container.register(IAuthentication.class, Authentication.class);

	        SignInControllerInterface signInController = IoCContainer.resolve(SignInControllerInterface.class);
	        SignUpControllerInterface signUpController = IoCContainer.resolve(SignUpControllerInterface.class);
	        ResetPassword1ControllerInterface resetPassword1Controller = IoCContainer.resolve(ResetPassword1ControllerInterface.class);
	        ResetPassword2ControllerInterface resetPassword2Controller = IoCContainer.resolve(ResetPassword2ControllerInterface.class);
			
			
			/*FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/ChooseUserTableInDatabase.fxml"));
			ChooseUserTableController controller = new ChooseUserTableController();
			controller.setAuthentication(auth);
			controller.setSignInController(signInController);
			loader.setController(controller);
            Parent root = loader.load();
			String css = this.getClass().getResource("/css/style.css").toExternalForm();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(css);*/

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/SignIn.fxml"));
            loader.setController(signInController);
            Parent root = loader.load();
            String css = this.getClass().getResource("/css/style.css").toExternalForm();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		SpringApplication.run(DesignpatternApplication.class, args);
		launch(args);
		
//		System.out.println(DatabaseConnection.getInstance().checkTableIntandAutoIncrementPrimaryKey("aaaaaaaaaa"));
		/*DatabaseConnection con = DatabaseConnection.getInstance();
        List<Table> tables = con.getTablesWithColumns();
        FileGenerator gen = new FileGenerator("C:\\Users\\duong\\OneDrive\\Máy tính\\New folder");
        gen.generateAll(System.getProperty("user.dir"), tables);
        con.close();*/

	}
	
}
