package main.java.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.model.AddTaskModel;

public class AddTaskController implements Initializable {
	@FXML
	private TextField txtEffortEstimate;
	@FXML
	private TextField txtNameOfTask;
	@FXML
	private ChoiceBox<String> cBoxPriority;
	@FXML
	private TextField txtNamesOfEmployees;
	@FXML
	private DatePicker dateStart;
	@FXML
	private DatePicker dateEnd;
	@FXML
	private Button btnCancel;

	private ObservableList<String> priorityList = FXCollections.observableArrayList("High", "Normal", "Low");
	private AddTaskModel model = new AddTaskModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cBoxPriority.setValue("Normal");
		cBoxPriority.setItems(priorityList);
	}

	@FXML
	public void cancel(ActionEvent event) {
		((Stage) btnCancel.getScene().getWindow()).close();
	}

	@FXML
	public void addTask(ActionEvent event) {
		String taskName = txtNameOfTask.getText();
		String taskPriority = cBoxPriority.getValue();
		String nameOfEmployees = txtNamesOfEmployees.getText();
		Date startingDate = java.sql.Date.valueOf(dateStart.getValue());
		Date endingDate = java.sql.Date.valueOf(dateEnd.getValue());
		int effortEstimate = Integer.valueOf(txtEffortEstimate.getText());
		if (!txtNamesOfEmployees.getText().trim().isEmpty())
		{
			try {	
				model.postTaskInDatabase(taskName, taskPriority, nameOfEmployees, startingDate, endingDate, effortEstimate);
			} catch (SQLException e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}

			((Stage) btnCancel.getScene().getWindow()).close();
		}
	}
}
