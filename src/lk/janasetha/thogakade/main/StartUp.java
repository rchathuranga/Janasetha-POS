package lk.janasetha.thogakade.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class StartUp extends Application {

    private static Logger LOGGER = LogManager.getLogger(StartUp.class.getName());

    public static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/janasetha/thogakade/view/ViewContainer.fxml"));
        primaryStage.setTitle("Janasetha Stores");

        scene = new Scene(root);


        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(false);//todo true
        primaryStage.show();
    }
}
