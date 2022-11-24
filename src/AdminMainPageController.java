import Classes.*;
import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AdminMainPageController {

    private Scene scene;
    private Stage stage;
    private Parent root;
    private Admin admin;
    private Connection db;

    public void setDb(Connection db) {
        this.db = db;
    }

    public Connection getDb() {
        return db;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    @FXML
    void btnViewAdminListClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminList.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        AdminListController adminListController = loader.getController();
        adminListController.setDb(db);
        adminListController.loadAdminList(admin);
        adminListController.setAdmin(admin);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnAddAdminClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminAddition.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        AdminAdditionController adminAdditionController = loader.getController();
        adminAdditionController.setDb(db);
        adminAdditionController.setAdmin(getAdmin());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnSearchUserClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminUserSearch.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        AdminUserSearchController adminUserSearchController = loader.getController();
        adminUserSearchController.setDb(db);
        adminUserSearchController.setAdmin(getAdmin());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnViewUserListClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminUserList.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        AdminUserListController adminUserListController = loader.getController();
        adminUserListController.setDb(db);
        adminUserListController.setAdmin(admin);
        adminUserListController.loadUsers(admin);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnViewInstituteListClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstituteList.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        InstituteListController instituteListController = loader.getController();
        instituteListController.setDb(db);
        instituteListController.loadInstitutesAll(admin);
        instituteListController.setAdmin(admin);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnViewBranchListClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("BranchList.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        BranchListController branchListController = loader.getController();
        branchListController.setDb(db);
        branchListController.setAdmin(admin);
        branchListController.loadBranchesAll(admin);

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

    @FXML
    void btnUpdateInstitutesClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstituteCSVUpdation.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        InstituteCSVUpdationController instituteCSVUpdationController = loader.getController();
        instituteCSVUpdationController.setDb(db);
        instituteCSVUpdationController.setAdmin(admin);

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
