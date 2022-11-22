import Classes.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class UserRegisterController implements Initializable{

    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private TextField registerUsername;

    @FXML
    private TextField registerEmail;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private ComboBox<String> comboboxCategory;
    private final ObservableList<String> categoryOptions = FXCollections.observableArrayList("OPEN", "EWS", "OBC-NCL", "SC", "ST", "OPEN(PWD)", "EWS(PWD)", "OBC-NCL(PWD)", "SC(PWD)", "ST");
    
    @FXML
    private ComboBox<String> comboboxGender;
    private final ObservableList<String> genderOptions = FXCollections.observableArrayList("Male", "Female (including supernumerary)");

    @FXML
    private TextField registerGeneralRank;
    
    @FXML
    private TextField registerCategoryRank;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboboxCategory.getItems().addAll(categoryOptions);
        comboboxGender.getItems().addAll(genderOptions);
    }

    @FXML
    void btnUserRegisterEnterClicked(ActionEvent event) {

        Connection userDb = null;
        try {
            userDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor","root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        User newUserRegister = new User(
            registerUsername.getText(),
            registerEmail.getText(),
            registerPassword.getText(),
            comboboxCategory.getValue(),
            comboboxGender.getValue(),
            Integer.parseInt(registerCategoryRank.getText()),
            Integer.parseInt(registerGeneralRank.getText())
        );

        if(newUserRegister.Register(userDb)) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                System.err.print("Error in " + this.getClass().getName() + " : ");
                System.err.println(e);
            }

            UserMainPageController userMainPageController = loader.getController();
            userMainPageController.setUser(newUserRegister);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }

    @FXML
    void btnUserRegisterBackClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}