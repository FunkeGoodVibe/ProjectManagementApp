package main.java.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.model.POJOTask;
import main.java.model.TasksManagementModel;

public class TasksManagementController extends ListView<String> implements Initializable {
	@FXML
	private ListView<String> listToDo;
	@FXML
	private ListView<String> listInProgress;
	@FXML
	private ListView<String> listDone;
	
	TasksManagementModel model = new TasksManagementModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateListView(listToDo, 1);
		populateListView(listInProgress, 2);
		populateListView(listDone, 3);
	}

	@FXML
	private void openAddPopUp() throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/AddTaskPopUp.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Add new task");
			stage.setScene(new Scene(root, 450, 450));
			stage.show();

		} catch (Exception e) {
			System.out.println("Can't load new window");
			e.printStackTrace();
		}
	}
	
	private void openEditPopUp(POJOTask task) throws IOException {                 
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/EditTaskPopUp.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Edit task");
			stage.setScene(new Scene(fxmlLoader.load(), 600, 450));
			EditTaskController controller = fxmlLoader.<EditTaskController>getController();
			controller.initData(task);
			
			stage.show();

		} catch (Exception e) {
			System.out.println("Can't load new window");
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onClickeItemToDo(MouseEvent arg0) throws SQLException {
		try {
			openEditPopUp(model.getTaskDetails(listToDo.getSelectionModel().getSelectedItem()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onClickeItemInProgress(MouseEvent arg0) throws SQLException {
		try {
			openEditPopUp(model.getTaskDetails(listInProgress.getSelectionModel().getSelectedItem()));	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onClickeItemDone(MouseEvent arg0) throws SQLException {
		try {
			openEditPopUp(model.getTaskDetails(listDone.getSelectionModel().getSelectedItem()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void populateListView(ListView<String> listView, int stage) {
		try {	
			listView.getItems().addAll(model.populateListView(stage));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void refreshListViews() {

			listToDo.getItems().clear();
			populateListView(listToDo,1);

			listInProgress.getItems().clear();
			populateListView(listInProgress,2);

			listDone.getItems().clear();
			populateListView(listDone,3);

	}


}
