import Classes.*;
import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginController {

    private Scene scene;
    private Stage stage;
    private Parent root;
    private Connection db;

    public void setDb(Connection db) {
        this.db = db;
    }

    public Connection getDb() {
        return db;
    }

    @FXML
    private Label incorrectDetails;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameEmailinput;

    @FXML
    void btnLoginBackClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cover.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        CoverController coverController = loader.getController();
        coverController.setDb(db);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnLoginSignInClicked(ActionEvent event) {

        Admin adminLoggingIn = new Admin(usernameEmailinput.getText(), passwordInput.getText());

        if (adminLoggingIn.Login(getDb())) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                System.err.print("Error in " + this.getClass().getName() + " : ");
                System.err.println(e);
            }

            AdminMainPageController adminMainPageController = loader.getController();
            adminMainPageController.setDb(db);;
            adminMainPageController.setAdmin(adminLoggingIn);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        else {
            incorrectDetails.setText("Incorrect username or password");
        }

    }

}
