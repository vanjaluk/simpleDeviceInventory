package inventory;

/**
 * @project STP_projekt
 * @author vanja
 * @time 18-01-2019 and 4:01 PM
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        primaryStage.setTitle("Device Inventory");
        primaryStage.setScene(new Scene(root, 1440, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
