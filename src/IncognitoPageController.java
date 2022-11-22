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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class IncognitoPageController implements Initializable{

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
    private ComboBox<String> comboboxBranch;
    private ObservableList<String> branchOptions = FXCollections.observableArrayList("ALL");
    
    @FXML
    private ComboBox<String> comboboxCategory;
    private final ObservableList<String> categoryOptions = FXCollections.observableArrayList("OPEN", "EWS", "OBC-NCL", "SC", "ST", "OPEN(PWD)", "EWS(PWD)", "OBC-NCL(PWD)", "SC(PWD)", "ST");
    
    @FXML
    private ComboBox<String> comboboxGender;
    private final ObservableList<String> genderOptions = FXCollections.observableArrayList("Male", "Female (including supernumerary)");
    
    @FXML
    private ComboBox<String> comboboxInstitute;
    private ObservableList<String> instituteOptions = FXCollections.observableArrayList("ALL");
    
    @FXML
    private ComboBox<Integer> comboboxRound;
    private final ObservableList<Integer> roundOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);

    @FXML
    private TextField tfCategoryRank;

    @FXML
    void btnGoClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("IncognitoInstituteTable.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        SearchInstitute searchInstitute = new SearchInstitute(comboboxRound.getValue(), comboboxGender.getValue(), comboboxInstitute.getValue(), comboboxBranch.getValue(), comboboxCategory.getValue(), 0);

        if(tfCategoryRank.getText() != "") {
            searchInstitute.setCategoryRank(Integer.parseInt(tfCategoryRank.getText()));
        }
        if(comboboxBranch.getValue() == "ALL") {
            searchInstitute.setBranch("");
        }
        if(comboboxInstitute.getValue() == "ALL") {
            searchInstitute.setInstituteName("");
        }

        IncognitoInstituteTableController instituteTableController = loader.getController();
        instituteTableController.setDb(db);
        instituteTableController.setUser(user);
        instituteTableController.loadInstitutesIncognito(searchInstitute);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboboxBranch.getItems().addAll(branchOptions);
        comboboxCategory.getItems().addAll(categoryOptions);
        comboboxGender.getItems().addAll(genderOptions);
        comboboxInstitute.getItems().addAll(instituteOptions);
        comboboxRound.getItems().addAll(roundOptions);

        loadBranches();
        loadInstitutes();

    }

    public void loadBranches() {

        branchOptions = new Institute().getAllBranch(getDb());
        comboboxBranch.getItems().addAll(branchOptions);

    }

    public void loadInstitutes() {

        instituteOptions = new Institute().getAllInstitute(getDb());
        comboboxInstitute.getItems().addAll(instituteOptions);

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

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

}