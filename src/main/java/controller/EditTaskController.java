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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.model.EditTaskModel;
import main.java.model.POJOTask;

public class EditTaskController implements Initializable {
	
	@FXML
	private Label lblNumberOfEmployees;
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
	@FXML
	private CheckBox cBoxNextStage;
	
	POJOTask task;
	EditTaskModel model = new EditTaskModel();
	private ObservableList<String> priorityList = FXCollections.observableArrayList("High", "Normal", "Low");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cBoxPriority.setItems(priorityList);
	}
	
	@FXML
	public void cancel(ActionEvent event) {
		((Stage) btnCancel.getScene().getWindow()).close();
	}
	
	@FXML
	public void updateTask(ActionEvent event) throws SQLException {
		String taskName = txtNameOfTask.getText();
		String effortEstimate = txtEffortEstimate.getText();
		String taskPriority = cBoxPriority.getValue();
		String nameOfEmployees = txtNamesOfEmployees.getText();
		Date startingDate = java.sql.Date.valueOf(dateStart.getValue());
		Date endingDate = java.sql.Date.valueOf(dateEnd.getValue());

		try {
			model.updateTask(taskName, taskPriority, nameOfEmployees, startingDate, endingDate, effortEstimate, task.getTaskName());
		} catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		
		if(cBoxNextStage.isSelected()) {
			goToNextStage(task);
		}

		((Stage) btnCancel.getScene().getWindow()).close();
	}
	
	@FXML
	public void deleteTask(ActionEvent event) {
		try {
			model.deleteTask(task.getTaskName());
		} catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		((Stage) btnCancel.getScene().getWindow()).close();
	}
	
	private void goToNextStage(POJOTask task) throws SQLException {
		if(task.getStage() < 3) {
			int stage = task.getStage() + 1;
			try {
				model.goToNextStage(task.getTaskName(), stage);
			} catch (SQLException e) {
				System.out.println("ERROR");
				e.printStackTrace();
			}
		}
	}
	
	public void initData(POJOTask task) {
		this.task = task;
		txtNameOfTask.setText(this.task.getTaskName());
		cBoxPriority.setValue(task.getTaskPriority());
		txtNamesOfEmployees.setText(task.getNameOfEmployees());
		dateStart.setValue(new java.sql.Date(task.getStartingDate().getTime()).toLocalDate());
		dateEnd.setValue(new java.sql.Date(task.getEndingDate().getTime()).toLocalDate());
		txtEffortEstimate.setText(String.valueOf(task.getEffortEstimate()));
		countEmployees();
	}
	
	private void countEmployees() {
		String employees = task.getNameOfEmployees();
		String[] splitArray = employees.split(",");
		lblNumberOfEmployees.setText(String.valueOf(splitArray.length));
	}
}
