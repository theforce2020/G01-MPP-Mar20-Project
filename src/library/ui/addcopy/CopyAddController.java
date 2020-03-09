package library.ui.addcopy;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.alert.AlertMaker;
import library.business.AdminController;
import library.ui.listbook.BookListController;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CopyAddController extends AdminController implements Initializable {

    @FXML
    private JFXTextField isbn;
    @FXML
    private JFXTextField copies;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;

    private Boolean isInEditMode = Boolean.FALSE;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void addCopy(ActionEvent event) {
        String isbn = StringUtils.trimToEmpty(this.isbn.getText());
        int checkout = Integer.parseInt(StringUtils.trimToEmpty(copies.getText()));

        if (isbn.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter data in all fields.");
            return;
        }

        if (isInEditMode) {
            addBookCopies(isbn, checkout);
            handleEditOperation();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void close() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public void inflateUI(BookListController.Bookz book) {
        isbn.setText(book.getIsbn());
        isbn.setEditable(false);
        isInEditMode = Boolean.TRUE;
    }

    private void clearEntries() {
        isbn.clear();
    }

    private void handleEditOperation() {
        close();
        AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Success", "Update complete");
    }
}
