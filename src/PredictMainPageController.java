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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

public class PredictMainPageController implements Initializable{

    private Scene scene;
    private Stage stage;
    private Parent root;
    private User user;
    private SearchInstitute searchInstitute;
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

    public void createSearchInstitute(User user) {
        setUser(user);
        SearchInstitute si = new SearchInstitute(user.getUsername(), user.getEmail(),user.getPassword(), user.getCategory(), user.getGender(), user.getCategoryRank(), user.getGeneralRank(), 0);
        searchInstitute = si;
    }

    @FXML
    private ComboBox<Integer> comboboxRound;
    private final ObservableList<Integer> roundOptions = FXCollections.observableArrayList(1,2,3,4,5,6);

    @FXML
    private TextField tfPredbyIns_Institute;

    @FXML
    private TextField tfPredbyBrnch_Branch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboboxRound.getItems().addAll(roundOptions);
    }

    @FXML
    void btnUserPagePredictSimplePredictClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstituteTable.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        searchInstitute.setRound(comboboxRound.getValue());
        searchInstitute.setSelect(1);

        InstituteTableController simplePredictTableController = loader.getController();
        simplePredictTableController.setDb(db);
        simplePredictTableController.loadInstitutesSimplePredict(searchInstitute);
        simplePredictTableController.setUser(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnUserPagePredictInstitutePredictClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstituteTable.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        searchInstitute.setRound(comboboxRound.getValue());
        searchInstitute.setSelect(2);
        searchInstitute.setInstituteName(tfPredbyIns_Institute.getText());

        InstituteTableController simplePredictTableController = loader.getController();
        simplePredictTableController.setDb(db);
        simplePredictTableController.loadInstitutesSimplePredict(searchInstitute);
        simplePredictTableController.setUser(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnUserPagePredictBranchPredictClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstituteTable.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        searchInstitute.setRound(comboboxRound.getValue());
        searchInstitute.setSelect(3);
        searchInstitute.setBranch(tfPredbyBrnch_Branch.getText());

        InstituteTableController simplePredictTableController = loader.getController();
        simplePredictTableController.setDb(db);
        simplePredictTableController.loadInstitutesSimplePredict(searchInstitute);
        simplePredictTableController.setUser(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnUserPagePredictBrnchInstPredictClicked(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstituteTable.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        searchInstitute.setRound(comboboxRound.getValue());
        searchInstitute.setSelect(4);
        searchInstitute.setInstituteName(tfPredbyIns_Institute.getText());
        searchInstitute.setBranch(tfPredbyBrnch_Branch.getText());

        InstituteTableController simplePredictTableController = loader.getController();
        simplePredictTableController.setDb(db);
        simplePredictTableController.loadInstitutesSimplePredict(searchInstitute);
        simplePredictTableController.setUser(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnUserPredictBackClicked(ActionEvent event) {

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