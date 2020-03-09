package library.ui.main.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.business.AuthenticationController;
import library.dataaccess.Auth;
import library.ui.main.Main;
import library.util.LibraryAssistantUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends ToolbarController implements Initializable {
    @FXML
    private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void handleMenuClose(ActionEvent event) {
        getStage().close();
    }

    @FXML
    private void handleAboutMenu(ActionEvent event) {
        LibraryAssistantUtil.loadWindow(getClass().getResource("/library/ui/about/about.fxml"), "About Me", null);
    }

    @FXML
    private void handleMenuFullScreen(ActionEvent event) {
        Stage stage = getStage();
        stage.setFullScreen(!stage.isFullScreen());
    }

    public void logout(ActionEvent actionEvent) throws Exception {
        AuthenticationController.currentAuth = null;
        getStage().close();
        Main main = new Main();
        main.start(new Stage(StageStyle.DECORATED));
    }
}