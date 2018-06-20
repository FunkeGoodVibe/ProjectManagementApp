package main.java.driver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static Boolean isSpalshScreenLoaded = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/WelcomeScreen.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Splash Screen");
			stage.show();
		} catch (Exception e) {
			System.out.println("Can't load welcome");
			e.printStackTrace();
		}
	}

}