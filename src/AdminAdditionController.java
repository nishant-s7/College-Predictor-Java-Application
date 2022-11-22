import Classes.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AdminAdditionController {

    private Scene scene;
    private Stage stage;
    private Parent root;
    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    @FXML
    private PasswordField pfPasswordInput;

    @FXML
    private TextField tfEmailInput;

    @FXML
    private TextField tfUsernameInput;

    @FXML
    void btnFinalAddAdminClicked(ActionEvent event) {

        Connection adminDb = null;
        try {
            adminDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor", "root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        if(getAdmin().addNewAdmin(adminDb, tfUsernameInput.getText(), tfEmailInput.getText(), pfPasswordInput.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                System.err.print("Error in " + this.getClass().getName() + " : ");
                System.err.println(e);
            }

            AdminMainPageController adminMainPageController = loader.getController();
            adminMainPageController.setAdmin(admin);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        AdminMainPageController adminMainPageController = loader.getController();
        adminMainPageController.setAdmin(admin);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}