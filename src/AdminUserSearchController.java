import java.sql.Connection;
import java.util.ResourceBundle;
import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;

public class AdminUserSearchController implements Initializable{

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

    @FXML
    private Label labelMessage;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfUsername;

    @FXML
    void btnGoClicked(MouseEvent event) {

        userList = getAdmin().getUser(getDb(), tfUsername.getText(), tfEmail.getText());

        if(userList != null) {
            tableView.getItems().addAll(userList);
            labelMessage.setText("User found");
            labelMessage.setTextFill(Color.GREEN);
        }
        else {
            labelMessage.setText("User not found");
            labelMessage.setTextFill(Color.RED);
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
        adminMainPageController.setDb(db);
        adminMainPageController.setAdmin(admin);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}