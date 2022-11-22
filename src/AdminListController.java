import Classes.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AdminListController implements Initializable{

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

    ObservableList<Admin> adminList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Admin, String> columnUsername;

    @FXML
    private TableColumn<Admin, String> columnEmail;

    @FXML
    private TableColumn<Admin, String> columnPosition;

    @FXML
    private TableView<Admin> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        columnUsername.setCellValueFactory(new PropertyValueFactory<Admin, String>("username"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Admin, String>("email"));
        columnPosition.setCellValueFactory(new PropertyValueFactory<Admin, String>("position"));

        tableView.getItems().addAll(adminList);

    }

    public void loadAdminList(Admin admin) {

        Connection adminDb = null;
        try {
            adminDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor","root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        adminList = admin.getAdminList(adminDb);
        tableView.getItems().addAll(adminList);

    }

    @FXML
    void btnRemoveAdminClicked(MouseEvent event) {

        int selectedId = tableView.getSelectionModel().getSelectedIndex();

        Connection adminDb = null;
        try {
            adminDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor","root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        Admin admint = tableView.getSelectionModel().getSelectedItem();
        boolean deleted = getAdmin().removeAdmin(adminDb, admint.getUsername(), admint.getEmail());

        if(deleted) {
            tableView.getItems().remove(selectedId);
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