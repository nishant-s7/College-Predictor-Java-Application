import Classes.*;
import java.io.IOException;
import java.sql.Connection;
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
    private Connection db;

    public void setDb(Connection db) {
        this.db = db;
    }

    public Connection getDb() {
        return db;
    }

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
    private TextField tfCategory;

    @FXML
    private TextField tfGenRank;

    @FXML
    private TextField tfGender;

    @FXML
    void btnChangeCatRankClicked(ActionEvent event) {

        boolean load = getUser().UpdateUserDetails(getDb(), 4, tfCatRank.getText());
        if(load) {
            labelMessage.setText("Category rank updated successfully!");
        }

    }

    @FXML
    void btnChangeCategoryClicked(ActionEvent event) {

        boolean load = getUser().UpdateUserDetails(getDb(), 2, tfCategory.getText());
        if(load) {
            labelMessage.setText("Category updated successfully!");
        }

    }

    @FXML
    void btnChangeGenRankClicked(ActionEvent event) {

        boolean load = getUser().UpdateUserDetails(getDb(), 3, tfGenRank.getText());
        if(load) {
            labelMessage.setText("General rank updated successfully!");
        }

    }

    @FXML
    void btnChangeGenderClicked(ActionEvent event) {

        boolean load = getUser().UpdateUserDetails(getDb(), 1, tfGender.getText());
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
        userProfileController.setDb(db);
        userProfileController.setDetails();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}