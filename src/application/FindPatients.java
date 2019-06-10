package application;

import java.net.URL;

import Data.Equipment;
import Data.Patient;
import Data.Practitioner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FindPatients {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;
	private Scene scene;
	private Stage stage;
	private Practitioner user;
	private TextField search;
	private ListView<Patient> patientsList;
	private PatientOptionsMenu prevMenu;
	private ListView<Equipment> toReturn;
	private MyPatients self;


	public FindPatients(double _sizeX, double _sizeY, double _positionX, double _positionY, Practitioner _user) {

		user = _user;
		sizeX = _sizeX;
		sizeY = _sizeY;
		positionX = _positionX;
		positionY = _positionY;

		buildUpStage();

	}

	// creates the scene and all elements, adds to stage and shows it
	private void buildUpStage() {
		stage = new Stage();
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setHeight(sizeY);
		stage.setMinHeight(sizeY);
		stage.setMaxHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setMaxWidth(sizeX);
		stage.setMinWidth(sizeX);
		stage.setTitle("Practice Management - Find patients");

		// setup fonts for text
		Font overall = Font.font("Aral", 20);

		// initialising components
		BorderPane content = new BorderPane();
		VBox patients = new VBox();
		Text usersName = new Text(user.getName());
		BorderPane title = new BorderPane();
		Text titleTXT = new Text("Fined Patients");
		BorderPane nameNbuttons = new BorderPane();
		nameNbuttons.setLeft(usersName);
		usersName.setFont(overall);
		BorderPane backBtnContainer = new BorderPane();

		Button back = new Button();
		search = new TextField();

		patientsList = new ListView<Patient>();
		patientsList.setPrefHeight(350);
		toReturn = new ListView<>();
		BorderPane returnBtn = new BorderPane();
		BorderPane ViewBtn = new BorderPane();
		Button view = new Button("View");
		Button returned = new Button("Return");
//		lend.setOnAction(lendClicked);
//		returned.setOnAction(returnedClicked);
		title.setCenter(titleTXT);
		ViewBtn.setCenter(view);
		returnBtn.setCenter(returned);
//		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		content.setCenter(patients);

		// setup buttons and search
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
		back.setOnAction(backClick);
		view.setOnAction(viewClick);
//		search.setOnKeyReleased(searching);
		
		//search
		search = new TextField();
		search.setOnKeyReleased(searching);
		search.setPromptText("Search");


		// Setup styles
		title.setStyle("-fx-font-size: 20px; -fx-background-color: rgb(200, 238, 242); -fx-text-align: center;");
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		patients.setStyle("-fx-background-color: rgb(230, 238, 242)");
//		usersName.setFont(overall);
		content.setStyle("-fx-font-size: 15px;");
		patientsList.setPrefHeight(350);
		search.setPromptText("Search");
		toReturn.setMaxHeight(50);
		/**
		// check if any items are borrowed create returns list
		if (user.getBorrowed().size() > 0) {
			// Populating and setting display name for ListView of patients borrowed equipment
			toReturn.setCellFactory(new Callback<ListView<Equipment>, ListCell<Equipment>>() {
				@Override
				public ListCell<Equipment> call(ListView<Equipment> param) {
					ListCell<Equipment> cell = new ListCell<Equipment>() {
						@Override
						protected void updateItem(Equipment item, boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item.getDescription());
							} else {
								setText(null);
							}
						}
					};
					return cell;
				}
			});
			for (Equipment eq : user.getBorrowed()) {
				toReturn.getItems().add(eq);

			}
			equipment.getChildren().addAll(toReturn, returnBtn);

		} // END IF BORROWED NOT EMPTY
		**/
		
		/**
		// Populating and setting display name for ListView of available equipment
		patientsList.setCellFactory(new Callback<ListView<Equipment>, ListCell<Equipment>>() {
			@Override
			public ListCell<Equipment> call(ListView<Equipment> param) {
				ListCell<Equipment> cell = new ListCell<Equipment>() {
					@Override
					protected void updateItem(Equipment item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(item.getDescription());
						} else {
							setText(null);
						}
					}
				};
				return cell;
			}
		});
		**/
		
		//Populating and setting display name for ListView of patients
		patientsList.setCellFactory(new Callback<ListView<Patient>, ListCell<Patient>>() {
		    @Override
		    public ListCell<Patient> call(ListView<Patient> param) {
		         ListCell<Patient> cell = new ListCell<Patient>() {
		             @Override
		            protected void updateItem(Patient item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item != null) {
		                    setText(item.getName());
		                } else {
		                    setText(null);
		                }
		            }
		         };
		        return cell;
		    }
		});
		for (Patient patient : user.getPatients()) {
			patientsList.getItems().add(patient);
		}
		
		patients.getChildren().addAll(backBtnContainer, title, search, patientsList, ViewBtn);

		// setup and show scene
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();
	}
	
	// show stage
	public void showStage(double _positionX, double _positionY) {
		positionX = _positionX;
		positionY = _positionY;
		buildUpStage();
	}

	// Back button click setup
	EventHandler<ActionEvent> backClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				stage.close();
				MainMenu mainMenu = new MainMenu(sizeX, sizeY, stage.getX(), stage.getY(), user);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	};
	
	EventHandler<ActionEvent> viewClick = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				PatientOptionsMenu mainMenu = new PatientOptionsMenu(sizeX, sizeY, 
						stage.getX(), stage.getY(), patientsList.getSelectionModel().getSelectedItem(), self);
				stage.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	};
	
	/**
	EventHandler<ActionEvent> returnedClicked = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			try {
				user.getBorrowed().remove(toReturn.getSelectionModel().getSelectedItem());
				stage.close();
				showStage(stage.getX(), stage.getY());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	};
	**/
	EventHandler<KeyEvent> searching = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) {
			try {
				patientsList.getItems().clear();
				if (search.getText().isEmpty()) {
					for (Patient P : PracticeInterface.database.getPatients()) {
						patientsList.getItems().add(P);

					}
				} else {
					for (Patient P : PracticeInterface.database.getPatients()) {
						if (P.getName().toLowerCase().contains(search.getText().toLowerCase())) {
							patientsList.getItems().add(P);
						}

					}
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	};
}
