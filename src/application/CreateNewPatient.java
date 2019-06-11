package application;

import java.net.URL;
import java.util.Date;

import Data.Patient;
import Data.Practitioner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.text.ParseException;
import java.text.SimpleDateFormat;  


public class CreateNewPatient {
	private double sizeX;
	private double sizeY;
	private double positionX;
	private double positionY;	
	private Scene scene;
	private Stage stage;
	private Practitioner user;
//	private Patient patient;
//	private String username = null;
//	private String password = null;
//	private String name = null;
//	private String address = null;
//	private String email = null;
//	private String phone = null;
//	private Date dob = null;
//	private String medicare = null;
	ListView<Control> listView;
	String checkUser;
    Date date;



	public CreateNewPatient(double sizeX, double sizeY, double positionX, double positionY, Practitioner user) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.positionX = positionX;
		this.positionY = positionY;

		buildUpStage();
	}

	private void buildUpStage() {
		// TODO Auto-generated method stub
		stage = new Stage();
		stage.setX(positionX);
		stage.setY(positionY);
		stage.setHeight(sizeY);
		stage.setMinHeight(sizeY);
		stage.setMaxHeight(sizeY);
		stage.setWidth(sizeX);
		stage.setMaxWidth(sizeX);
		stage.setMinWidth(sizeX);
		stage.setTitle("Practice Management - New Patient");
		
		// setup fonts for text
		Font overall = Font.font("Aral", 20);
		
		// initialising components
		BorderPane content = new BorderPane();
		VBox patients = new VBox();
		Text usersName = new Text(user.getName());
		BorderPane title = new BorderPane();
		Text titleTXT = new Text("Create Patient");
		BorderPane nameNbuttons = new BorderPane();
		BorderPane backBtnContainer = new BorderPane();
		Button back = new Button();
		listView = new ListView();
		BorderPane addViewButtons = new BorderPane();
		Button create = new Button();
		title.setCenter(titleTXT);
		addViewButtons.setCenter(create);
		nameNbuttons.setLeft(usersName);
		content.setTop(nameNbuttons);
		content.setCenter(patients);
		
		//Setup styles
		title.setStyle("-fx-font-size: 20px; -fx-background-color: rgb(200, 238, 242); -fx-text-align: center;");
		nameNbuttons.setStyle("-fx-background-color: rgb(250, 250, 250)");
		patients.setStyle("-fx-background-color: rgb(230, 238, 242)");
		usersName.setFont(overall);
		
        //Adding GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,50));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
		
		// add patient components
        Label lblUserName = new Label("UserName");
        TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        PasswordField pf = new PasswordField();
        Label lblConPassword = new Label("ConfirmPassword");
        PasswordField cpf = new PasswordField();
        Label lblName = new Label("YourName");
        TextField txtName = new TextField();
        Label lblAddress = new Label("Address");
        TextField txtAddress = new TextField();
        Label lblEmail = new Label("Email");
        TextField txtEmail = new TextField();
        Label lblPhone = new Label("PhoneNumber");
        TextField txtPhone = new TextField();
        Label lblDob = new Label("DateOfBrith");
        TextField txtDob = new TextField();
        Label lblMed = new Label("Medicare");
        TextField txtMed = new TextField();
        
        listView.getItems().add(lblUserName);
        listView.getItems().add(txtUserName);
        listView.getItems().add(lblPassword);
        listView.getItems().add(pf);
        listView.getItems().add(lblConPassword);
        listView.getItems().add(cpf);
        listView.getItems().add(lblName);
        listView.getItems().add(txtName);
        listView.getItems().add(lblAddress);
        listView.getItems().add(txtAddress);
        listView.getItems().add(lblEmail);
        listView.getItems().add(txtEmail);
        listView.getItems().add(lblPhone);
        listView.getItems().add(txtPhone);
        listView.getItems().add(lblDob);
        listView.getItems().add(txtDob);
        listView.getItems().add(lblMed);
        listView.getItems().add(txtMed);
        
        //Action for btnCreate
    	EventHandler<ActionEvent> CreateClick = new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
				try {
    				date = new SimpleDateFormat("yyyy,mm,dd").parse(txtDob.getText().toString());
    			} catch (ParseException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			try {
    				if(!pf.getText().equals(cpf.getText())) {
    					System.out.println(pf.getText().toString());
    					System.out.println(cpf.getText().toString());
    	                showAlert(Alert.AlertType.CONFIRMATION, "Password mismatched!", "Password mismatched!");

    				}
    				else if(txtUserName.getText().toString().equals("")) {
    	                showAlert(Alert.AlertType.CONFIRMATION, "Please enter user name!", "Please enter user name!");
    				}
    				else {
      				  Patient p = new Patient(txtUserName.getText().toString(), pf.getText().toString(), txtName.getText().toString(), 
    						  txtAddress.getText().toString(), txtEmail.getText().toString(), txtPhone.getText().toString(),date, txtMed.getText().toString());  	
    				  user.AddPatients(p);
    	                showAlert(Alert.AlertType.CONFIRMATION, "Registration Successful!", "Registration Successful!");
    				}

    				
    			} catch (Exception e2) {
    				e2.printStackTrace();
    			}
    		}
    		
    	};

		
        //Adding Nodes to GridPane layout
//        gridPane.add(lblUserName, 0, 0);
//        gridPane.add(txtUserName, 0, 1);
//    	gridPane.add(lblPassword, 0, 2);
//        gridPane.add(pf, 0, 3);
//        gridPane.add(lblConPassword, 0, 4);
//        gridPane.add(cpf, 0, 5);
//        gridPane.add(lblName, 0, 6);
//        gridPane.add(txtName, 0, 7);
//        gridPane.add(lblAddress, 0, 8);
//        gridPane.add(txtAddress, 0, 9);
//        gridPane.add(lblEmail, 0, 10);
//        gridPane.add(txtEmail, 0, 11);
//        gridPane.add(lblPhone, 0, 12);
//        gridPane.add(txtPhone, 0, 13);
//        gridPane.add(lblDob, 0, 14);
//        gridPane.add(txtDob, 0, 15);
//        gridPane.add(lblMed, 0, 16);
//        gridPane.add(txtMed, 0, 17);  
        
	
		// setup buttons
		URL imgURL = getClass().getResource("back.png");
		backBtnContainer.setLeft(back);
		back.setMinSize(70, 30);
		back.setStyle("-fx-background-image: url('" + imgURL.toString() + "'); -fx-background-repeat: no-repeat;"
				+ "  -fx-background-position: center; -fx-background-size: 68px 24px; ");
		back.setOnAction(backClick);
		
		create.setText("Submit");
		create.setOnAction(CreateClick);



		// setup and show scene
		patients.getChildren().addAll(backBtnContainer, title, listView, addViewButtons);
		scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();
	}
	
	//show stage
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
	
	//show alert
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
		alert.setContentText(message);
        alert.show();
    }
}
