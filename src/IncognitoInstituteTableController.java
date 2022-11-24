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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class IncognitoInstituteTableController implements Initializable{

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

    private ObservableList<Institute> collegeList = FXCollections.observableArrayList();

    @FXML
    private TableView<Institute> tableView;

    @FXML
    private TableColumn<Institute, String> categoryColumn;

    @FXML
    private TableColumn<Institute, Integer> closingRankColumn;

    @FXML
    private TableColumn<Institute, String> genderColumn;

    @FXML
    private TableColumn<Institute, String> instituteColumn;

    @FXML
    private TableColumn<Institute, Integer> openingRankColumn;

    @FXML
    private TableColumn<Institute, String> programColumn;

    @FXML
    private TableColumn<Institute, String> quotaColumn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        instituteColumn.setCellValueFactory(new PropertyValueFactory<Institute, String>("InstituteName"));
        programColumn.setCellValueFactory(new PropertyValueFactory<Institute, String>("program"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Institute, String>("Gender"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Institute, String>("category"));
        quotaColumn.setCellValueFactory(new PropertyValueFactory<Institute, String>("Quota"));
        openingRankColumn.setCellValueFactory(new PropertyValueFactory<Institute, Integer>("OpeningRank"));
        closingRankColumn.setCellValueFactory(new PropertyValueFactory<Institute, Integer>("ClosingRank"));

        instituteColumn.setCellFactory(tc -> {
            TableCell<Institute, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(instituteColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        genderColumn.setCellFactory(tc -> {
            TableCell<Institute, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(genderColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        programColumn.setCellFactory(tc -> {
            TableCell<Institute, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(programColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        tableView.getItems().addAll(collegeList);

    }

    public void loadInstitutesIncognito(SearchInstitute searchInstitute) {

        collegeList = searchInstitute.IncognitoSearch(getDb(), searchInstitute.getRound(), searchInstitute.getGender(), searchInstitute.getInstituteName(), searchInstitute.getBranch(), searchInstitute.getCategory(), searchInstitute.getCategoryRank());
        tableView.getItems().addAll(collegeList);

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

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
        incognitoPageController.loadInstitutes();
        incognitoPageController.loadBranches();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}