package library.ui.main.admin;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.alert.AlertMaker;
import library.business.LibrarianController;
import library.exceptions.CheckException;
import library.model.Book;
import library.model.LibraryMember;
import library.ui.callback.BookReturnCallback;
import library.ui.issuedlist.IssuedListController;
import library.ui.main.toolbar.ToolbarController;
import library.util.LibraryAssistantUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController implements Initializable {

    library.business.AdminController adminController = new library.business.AdminController();
    ObservableList<CountStat> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<String, String> fieldCol;
    @FXML
    private TableColumn<String, Long> countCol;
    @FXML
    private StackPane rootPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private TableView<CountStat> tableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fieldCol.setCellValueFactory(new PropertyValueFactory<>("field"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        initDrawer();
        initStats();
    }

    private void initStats() {
        list.clear();

        long memCount = adminController.getAllLibraryMembers().size();
        long bookCount = adminController.getAllBooks().size();

        list.add(new CountStat("Members", memCount));
        list.add(new CountStat("Books", bookCount));

        tableView.setItems(list);
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

    private void initDrawer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/ui/main/toolbar/toolbar.fxml"));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);

            drawer.open();
            drawer.toFront();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}