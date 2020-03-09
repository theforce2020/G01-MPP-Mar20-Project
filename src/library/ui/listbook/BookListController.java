package library.ui.listbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.alert.AlertMaker;
import library.business.AdminController;
import library.model.Book;
import library.ui.addbook.BookAddController;
import library.ui.addcopy.CopyAddController;
import library.ui.main.MainController;
import library.util.LibraryAssistantUtil;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookListController extends AdminController implements Initializable {

    ObservableList<Bookz> list = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private TableView<Bookz> tableView;
    @FXML
    private TableColumn<Bookz, String> titleCol;
    @FXML
    private TableColumn<Bookz, String> idCol;
    @FXML
    private TableColumn<Bookz, String> authorCol;
    @FXML
    private TableColumn<Bookz, Integer> copyCol;
    @FXML
    private TableColumn<Bookz, Integer> availCol;
    @FXML
    private AnchorPane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void initCol() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        copyCol.setCellValueFactory(new PropertyValueFactory<>("copies"));
        availCol.setCellValueFactory(new PropertyValueFactory<>("available"));
    }

    private void loadData() {
        list.clear();
        List<Book> books = getAllBooks();
        list.addAll(books.stream().map(Bookz::new).collect(Collectors.toList()));
        tableView.setItems(list);
    }

    @FXML
    private void handleBookDeleteOption(ActionEvent event) {
        //Fetch the selected row
        Bookz selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("No book selected", "Please select a book for deletion.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to delete the book " + selectedForDeletion.title + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            deleteBook(selectedForDeletion.isbn);
                AlertMaker.showSimpleAlert("Book deleted", selectedForDeletion.getTitle() + " was deleted successfully.");
                list.remove(selectedForDeletion);
        } else {
            AlertMaker.showSimpleAlert("Deletion cancelled", "Deletion process cancelled");
        }
    }

    @FXML
    private void handleBookEditOption(ActionEvent event) {
        //Fetch the selected row
        Bookz selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("No book selected", "Please select a book for edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/ui/addbook/add_book.fxml"));
            Parent parent = loader.load();

            BookAddController controller = loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Book");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryAssistantUtil.setStageIcon(stage);

            stage.setOnHiding((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"   Title   ", "ID", "  Author  ", "  Publisher ", "Avail"};
        printData.add(Arrays.asList(headers));
        for (Bookz book : list) {
            List<String> row = new ArrayList<>();
            row.add(book.title);
            row.add(book.isbn);
            row.add(book.author);
            row.add(String.valueOf(book.copies));
            printData.add(row);
        }
        LibraryAssistantUtil.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }

    @FXML
    private void exportToConsole(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"   Title   ", "ID", "  Author  ", "  Publisher ", "Avail"};
        printData.add(Arrays.asList(headers));
        for (Bookz book : list) {
            List<String> row = new ArrayList<>();
            row.add(book.title);
            row.add(book.isbn);
            row.add(book.author);
            row.add(String.valueOf(book.copies));
            printData.add(row);
        }

        System.out.println(printData);
    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }

    public void addCopy(ActionEvent actionEvent) {
        Bookz selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("No book selected", "Please select a book for edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/ui/addcopy/add_copy.fxml"));
            Parent parent = loader.load();

            CopyAddController controller = loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add Book Copy");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryAssistantUtil.setStageIcon(stage);

            stage.setOnHiding((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toConsole(ActionEvent actionEvent) {
        Bookz selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("No book selected", "Please select a book for edit.");
            return;
        }
        System.out.println(selectedForEdit);
    }

    public static class Bookz {
        String title;
        String isbn;
        String author;
        int copies;
        int available;

        public Bookz(Book book) {
            this.title = book.getTitle();
            this.isbn = book.getIsbn();
            this.author = book.getAuthors().get(0).getName();
            this.copies = book.getNumCopies();
            this.available = book.getAvailableNoOfCopies();
        }

        public Book toBook() {
            return new Book(isbn, title, copies, new ArrayList<>());
        }

        public String getTitle() {
            return title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getAuthor() {
            return author;
        }

        public int getCopies() {
            return copies;
        }

        public int getAvailable() {
            return available;
        }

        @Override
        public String toString() {
            return "Books{" +
                    "title='" + title + '\'' +
                    ", isbn='" + isbn + '\'' +
                    ", author='" + author + '\'' +
                    ", copies=" + copies +
                    ", available=" + available +
                    '}';
        }
    }

}
