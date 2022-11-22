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
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class UserInstituteListController implements Initializable {

    private Scene scene;
    private Stage stage;
    private Parent root;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private ObservableList<String> collegeList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listviewInstitute;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listviewInstitute.getItems().addAll(collegeList);
        listviewInstitute.setCellFactory(tc -> {
            ListCell<String> cell = new ListCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(listviewInstitute.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

    }

    public void loadInstitutesAll() {

        Connection adminDb = null;
        try {
            adminDb = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor", "root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        collegeList = getUser().getAllInstitute(adminDb);
        listviewInstitute.getItems().addAll(collegeList);

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
        userMainPageController.setUser(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}