import java.io.IOException;
import java.sql.Connection;
import Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class UserMainPageController {

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

    @FXML
    void btnuserPageIncognitoClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("IncognitoPage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        IncognitoPageController incognitoPageController = loader.getController();
        incognitoPageController.setDb(db);
        incognitoPageController.setUser(user);
        incognitoPageController.loadBranches();
        incognitoPageController.loadInstitutes();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnuserPagePredictClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPagePredict.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        PredictMainPageController predictMainPageController = loader.getController();
        predictMainPageController.setDb(db);
        predictMainPageController.createSearchInstitute(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnuserPageProfileClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPageProfile.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        UserProfileController userProfileController = loader.getController();
        userProfileController.setDb(db);
        userProfileController.setUser(user);
        userProfileController.setDetails();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnBranchListClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserBranchList.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        UserBranchListController branchListController = loader.getController();
        branchListController.setDb(db);
        branchListController.setUser(user);
        branchListController.loadBranchesAll();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnCollegeListClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInstituteList.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        UserInstituteListController userInstituteListController = loader.getController();
        userInstituteListController.setDb(db);
        userInstituteListController.setUser(user);
        userInstituteListController.loadInstitutesAll();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnLogOutClicked(ActionEvent event) {

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