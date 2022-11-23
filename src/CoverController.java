import Classes.*;
import java.io.IOException;
import java.sql.Connection;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CoverController {

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
    void btnUserLogin(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        UserLoginController userLoginController = loader.getController();
        userLoginController.setDb(db);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnAdminLogin(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        AdminLoginController adminLoginController = loader.getController();
        adminLoginController.setDb(db);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void hyperlinkLoadDatabasesClicked(ActionEvent event) {
        Admin.UploadAllJosaaRoundCutoff(getDb());
        System.out.println("Loaded all databases.");
    }

}