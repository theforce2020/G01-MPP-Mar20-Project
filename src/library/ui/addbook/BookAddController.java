package library.ui.addbook;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.alert.AlertMaker;
import library.business.AdminController;
import library.model.Book;
import library.ui.listbook.BookListController;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookAddController extends AdminController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField isbn;
    @FXML
    private JFXTextField maxCheckout;
    @FXML
    private JFXTextField author;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;

    private Boolean isInEditMode = Boolean.FALSE;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void addBook(ActionEvent event) {
        String isbn = StringUtils.trimToEmpty(this.isbn.getText());
        String bookAuthor = StringUtils.trimToEmpty(author.getText());
        String bookName = StringUtils.trimToEmpty(title.getText());
        int checkout = Integer.parseInt(StringUtils.trimToEmpty(maxCheckout.getText()));

        if (isbn.isEmpty() || bookAuthor.isEmpty() || bookName.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter data in all fields.");
            return;
        }

        if (isInEditMode) {
            Book book = getBook(isbn);
            book.setTitle(bookName);
            updateBook(book);

            //updateBook(isbn, bookName, checkout, bookAuthor, "");
            handleEditOperation();
            return;
        }

        if (isBookAvailable(isbn)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Duplicate book isbn", "Book with same Book ID exists.\nPlease use new ID");
            return;
        }

        saveBook(isbn, bookName, checkout, bookAuthor, "");
        AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "New book added", bookName + " has been added");
        clearEntries();
        close();
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

//    private void checkData() {
//        String qu = "SELECT title FROM BOOK";
//        ResultSet rs = databaseHandler.execQuery(qu);
//        try {
//            while (rs.next()) {
//                String titlex = rs.getString("title");
//                System.out.println(titlex);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BookAddController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void inflateUI(BookListController.Bookz book) {
        title.setText(book.getTitle());
        isbn.setText(book.getIsbn());
        author.setText(book.getAuthor());
        isbn.setEditable(false);
        isInEditMode = Boolean.TRUE;
    }

    private void clearEntries() {
        title.clear();
        isbn.clear();
        author.clear();
    }

    private void handleEditOperation() {
        close();
        AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Success", "Update complete");
    }
}
