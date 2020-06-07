package lk.janasetha.thogakade.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.Property;

import java.util.List;
import java.util.Map;


public class StartUp extends Application {

    private static Logger LOGGER = LogManager.getLogger(StartUp.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/janasetha/thogakade/view/ViewContainer.fxml"));
        primaryStage.setTitle("Janasetha Stores");
        primaryStage.setScene(new Scene(root));
        primaryStage.setAlwaysOnTop(false);//todo true
        primaryStage.show();
    }
}
