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
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class BranchListController implements Initializable{

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

    private ObservableList<String> branchList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listviewBranch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listviewBranch.getItems().addAll(branchList);
        listviewBranch.setCellFactory(tc -> {
            ListCell<String> cell = new ListCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(listviewBranch.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

    }

    public void loadBranchesAll(Admin admin) {

        branchList = admin.getAllBranch(getDb());
        listviewBranch.getItems().addAll(branchList);
        
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