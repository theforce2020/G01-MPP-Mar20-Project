package library.ui.addmember;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.alert.AlertMaker;
import library.business.AdminController;
import library.model.LibraryMember;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MemberAddController extends AdminController implements Initializable {
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    private Boolean isInEditMode = false;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) firstName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMember(ActionEvent event) {
        String fName = StringUtils.trimToEmpty(firstName.getText());
        String lName = StringUtils.trimToEmpty(lastName.getText());
        String mID = StringUtils.trimToEmpty(id.getText());
        String mMobile = StringUtils.trimToEmpty(mobile.getText());

        boolean flag = fName.isEmpty() || lName.isEmpty() || mID.isEmpty() || mMobile.isEmpty();
        if (flag) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter data in all fields.");
            return;
        }

        if (isInEditMode) {
            updateLibraryMember(mID, fName, lName, mMobile, "", "", "", "");
            handleUpdateMember();
            return;
        }

        if (doesMemberExist(mID)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Duplicate member id", "Member with same id exists.\nPlease use new ID");
            return;
        }

        saveLibraryMember(mID, fName, lName, mMobile, "", "", "", "");
        AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "New member added", fName + " " + lName + " has been added");
        clearEntries();
        close();
    }

    public void infalteUI(LibraryMember member) {
        firstName.setText(member.getFirstName());
        lastName.setText(member.getLastName());
        id.setText(member.getMemberId());
        id.setEditable(false);
        mobile.setText(member.getTelephone());

        isInEditMode = Boolean.TRUE;
    }

    private void clearEntries() {
        firstName.clear();
        lastName.clear();
        id.clear();
        mobile.clear();
    }

    private void handleUpdateMember() {
        close();
        AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Success", "Member data updated.");
    }

    private void close() {
        Stage stage = (Stage) firstName.getScene().getWindow();
        stage.close();
    }

}
