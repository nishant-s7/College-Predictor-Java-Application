import java.io.IOException;
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
        incognitoPageController.setUser(user);

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
        userProfileController.setDetails(user);

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

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}