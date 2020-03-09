package library.ui.listmember;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import library.model.LibraryMember;
import library.ui.addmember.MemberAddController;
import library.ui.main.MainController;
import library.util.LibraryAssistantUtil;

public class MemberListController extends AdminController implements Initializable {

    ObservableList<LibraryMember> list = FXCollections.observableArrayList();

    @FXML
    private TableView<LibraryMember> tableView;
    @FXML
    private TableColumn<LibraryMember, String> firstNameCol;
    @FXML
    private TableColumn<LibraryMember, String> lastNameCol;
    @FXML
    private TableColumn<LibraryMember, String> idCol;
    @FXML
    private TableColumn<LibraryMember, String> telephoneCol;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void loadData() {
        list.clear();

        List<LibraryMember> members = getAllLibraryMembers();
        System.out.println(members);
        list.addAll(members);
        tableView.setItems(list);
    }

    @FXML
    private void handleMemberDelete(ActionEvent event) {
        //Fetch the selected row
        LibraryMember selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("No member selected", "Please select a member for deletion.");
            return;
        }
//        if (DatabaseHandler.getInstance().isMemberHasAnyBooks(selectedForDeletion)) {
//            AlertMaker.showErrorMessage("Cant be deleted", "This member has some books.");
//            return;
//        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getFirstName() + " " + selectedForDeletion.getLastName() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            deleteMember(selectedForDeletion.getMemberId());
            AlertMaker.showSimpleAlert("Book deleted", selectedForDeletion.getFirstName() + " " + selectedForDeletion.getLastName() + " was deleted successfully.");
            list.remove(selectedForDeletion);
        } else {
            AlertMaker.showSimpleAlert("Deletion cancelled", "Deletion process cancelled");
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void handleMemberEdit(ActionEvent event) {
        //Fetch the selected row
        LibraryMember selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("No member selected", "Please select a member for edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/ui/addmember/member_add.fxml"));
            Parent parent = loader.load();

            MemberAddController controller = loader.getController();
            controller.infalteUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Member");
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
    private void exportAsPDF(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"   Name    ", "ID", "Mobile", "    Email   "};
        printData.add(Arrays.asList(headers));
        for (LibraryMember member : list) {
            List<String> row = new ArrayList<>();
            row.add(member.getFirstName());
            row.add(member.getLastName());
            row.add(member.getMemberId());
            row.add(member.getTelephone());
            printData.add(row);
        }
        LibraryAssistantUtil.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }
}
