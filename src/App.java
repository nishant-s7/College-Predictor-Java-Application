import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Database connection
        Connection Db = null;
        try {
            Db = DriverManager.getConnection("jdbc:mysql://localhost:3307/java_proj_college_predictor", "root", "D@ta8aSe");
        } catch (SQLException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cover.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.print("Error in " + this.getClass().getName() + " : ");
            System.err.println(e);
        }
        
        CoverController coverController = loader.getController();
        coverController.setDb(Db);

        Image image = new Image("file:/C:/Users/nisha/OneDrive/Documents/IIITS/OOP/SLIDES/Project/OOP%20Project/icon%20cp.png");

        primaryStage.getIcons().add(image);
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("College Predictor");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}