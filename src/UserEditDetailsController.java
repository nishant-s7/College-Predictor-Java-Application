import Classes.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

public class UserEditDetailsController {

    private Scene scene;
    private Stage stage;
    private Parent root;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @FXML
    private Label labelMessage;

    @FXML
    private TextField tfCatRank;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfGenRank;

    @FXML
    private TextField tfGender;

    @FXML
    void btnChangeCatRankClicked(ActionEvent event) {

    }

    @FXML
    void btnChangeEmailClicked(ActionEvent event) {

    }

    @FXML
    void btnChangeGenRankClicked(ActionEvent event) {

    }

    @FXML
    void btnChangeGenderClicked(ActionEvent event) {

        Connection userDb = null;
        try {
            userDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor","root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        boolean load = getUser().UpdateUserDetails(userDb, 1, tfGender.getText());
        if(load) {
            labelMessage.setText("Gender updated successfully!");
        }

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPageProfile.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        UserProfileController userProfileController = loader.getController();
        userProfileController.setUser(user);
        userProfileController.setDetails(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}