import Classes.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class UserProfileController implements Initializable{

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
    private ListView<String> userProfileListView;
    private ObservableList<String> userDetails = FXCollections.observableArrayList();

    @FXML
    void btnUserProfileBackClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        UserMainPageController userMainPageController = loader.getController();
        userMainPageController.setDb(db);
        userMainPageController.setUser(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userProfileListView.getItems().addAll(userDetails);

    }

    public void setDetails() {
        userProfileListView.getItems().add("Username : " + user.getUsername());
        userProfileListView.getItems().add("Email : " + user.getEmail());
        userProfileListView.getItems().add("Gender : " + user.getGender());
        userProfileListView.getItems().add("Category : " + user.getCategory());
        userProfileListView.getItems().add("General Rank : " + Integer.toString(user.getGeneralRank()));
        userProfileListView.getItems().add("Category Rank : " + Integer.toString(user.getCategoryRank()));
    }

    @FXML
    void btnEditClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEditDetails.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        UserEditDetailsController userEditDetailsController = loader.getController();
        userEditDetailsController.setDb(db);
        userEditDetailsController.setUser(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void hyperlinkDeleteClicked(ActionEvent event) {

        boolean done = getUser().deleteAccount(getDb(), getUser().getPassword());

        if(done) {
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

    }

}