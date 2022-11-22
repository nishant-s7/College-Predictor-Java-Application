import Classes.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserLoginController {

    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Hyperlink hyperlinkRegister;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameEmailinput;

    @FXML
    private Label incorrectDetails;

    @FXML
    void btnLoginSignInClicked(ActionEvent event) {

        Connection userDb = null;
        try {
            userDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor","root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        User userLoggingIn = new User(usernameEmailinput.getText(), passwordInput.getText());

        if (userLoggingIn.Login(userDb)) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                System.err.print("Error in " + this.getClass().getName() + " : ");
                System.err.println(e);
            }

            UserMainPageController userMainPageController = loader.getController();
            userMainPageController.setUser(userLoggingIn);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        else {

            incorrectDetails.setText("Incorrect username or password");

        }

    }
    
    @FXML
    void registerClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserRegistration.fxml"));
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

    @FXML
    void btnLoginBackClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cover.fxml"));
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
