package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.model.LoginModel;

public class LoginController implements Initializable {
	@FXML
	private Label errorMessage;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private PasswordField txtSignUpPassword;
	@FXML
	private PasswordField txtReEnterPassword;
	@FXML
	private TextField txtSignUpUsername;
	@FXML
	private TextField txtUsername;
	
	public LoginModel model = new LoginModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void login(ActionEvent event) {
		try {
			if (model.isLogin(txtUsername.getText(), txtPassword.getText())) {
				((Node) (event.getSource())).getScene().getWindow().hide();
				Stage stage = new Stage();
				Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("view/TasksDashboard.fxml")));
				stage.setScene(scene);
				stage.setResizable(false);
				stage.setTitle("Task Board");
				stage.show();

			} else {
				errorMessage.setText("Wrong username or passwords");
			}
		} catch (SQLException e) {
			errorMessage.setText("Wrong username or passwords");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void register(ActionEvent event) {
		try {
			if (!txtReEnterPassword.getText().trim().isEmpty() || !txtUsername.getText().trim().isEmpty()  )
			{
				if (txtReEnterPassword.getText().equals(txtSignUpPassword.getText()) ) {
				model.isRegister(txtSignUpUsername.getText(), txtSignUpPassword.getText());
				errorMessage.setText("User is added successfully");
				} else {
					errorMessage.setText("Passwords don't match");
				}
			}
			else
			{
				errorMessage.setText("Please fill all fields");
			}
			
		} catch (SQLException e) {
			errorMessage.setText("Please fill all fields");
			e.printStackTrace();
		}
	}

}
