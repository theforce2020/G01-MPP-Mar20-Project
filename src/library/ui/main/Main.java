package library.ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.database.DatabaseHandler;
import library.exceptions.ExceptionUtil;
import library.util.LibraryAssistantUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    private final static Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Library Assistant launched on {}", LibraryAssistantUtil.formatDateTimeString(startTime));
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Long exitTime = System.currentTimeMillis();
                LOGGER.log(Level.INFO, "Library Assistant is closing on {}. Used for {} ms", LibraryAssistantUtil.formatDateTimeString(startTime), exitTime);
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/library/ui/login/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("The Force Login");

        LibraryAssistantUtil.setStageIcon(stage);
    }

}
