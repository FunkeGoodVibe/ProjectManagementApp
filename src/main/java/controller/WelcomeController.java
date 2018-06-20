package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.java.driver.Main;

public class WelcomeController implements Initializable {
	@FXML
	private AnchorPane root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (!Main.isSpalshScreenLoaded) {
			loadSplashScreen();
		}
	}

	public void loadSplashScreen() {
		try {
			Main.isSpalshScreenLoaded = true;

			StackPane pane = FXMLLoader.load(getClass().getClassLoader().getResource(("view/SplashScreen.fxml")));
			root.getChildren().setAll(pane);

			FadeTransition fadeIn = new FadeTransition(Duration.seconds(3.3), pane);
			fadeIn.setCycleCount(1);

			FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), pane);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);

			fadeIn.play();

			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});

			fadeOut.setOnFinished((e) -> {
				try {
					AnchorPane parentContent = FXMLLoader
							.load(getClass().getClassLoader().getResource(("view/WelcomeScreen.fxml")));
					root.getChildren().setAll(parentContent);

				} catch (IOException ex) {
					Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
				}
			});
		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}
}
