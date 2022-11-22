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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class AdminUserListController implements Initializable{

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

    private ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<User, String> columnCategory;

    @FXML
    private TableColumn<User, Integer> columnCategoryRank;

    @FXML
    private TableColumn<User, String> columnEmail;

    @FXML
    private TableColumn<User, String> columnGender;

    @FXML
    private TableColumn<User, Integer> columnGeneralRank;

    @FXML
    private TableColumn<User, String> columnUsername;

    @FXML
    private TableView<User> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<User, String>("category"));
        columnGender.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        columnGeneralRank.setCellValueFactory(new PropertyValueFactory<User, Integer>("GeneralRank"));
        columnCategoryRank.setCellValueFactory(new PropertyValueFactory<User, Integer>("CategoryRank"));

        tableView.getItems().addAll(userList);

    }

    public void loadUsers(Admin admin) {

        Connection userDb = null;
        try {
            userDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor","root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        userList = admin.getUserList(userDb);
        tableView.getItems().addAll(userList);

    }

    @FXML
    void btnRemoveUserClicked(MouseEvent event) {

        int selectedId = tableView.getSelectionModel().getSelectedIndex();

        Connection userDb = null;
        try {
            userDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor","root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        User user = tableView.getSelectionModel().getSelectedItem();
        boolean deleted = getAdmin().removeUser(userDb, user.getUsername(), user.getEmail());

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