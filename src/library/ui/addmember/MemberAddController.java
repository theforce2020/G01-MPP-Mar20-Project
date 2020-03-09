package library.ui.addmember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.alert.AlertMaker;
import library.business.AdminController;
import library.database.DataHelper;
import library.database.DatabaseHandler;
import library.ui.listmember.MemberListController;
import org.apache.commons.lang3.StringUtils;

public class MemberAddController extends AdminController implements Initializable {

    DatabaseHandler handler;

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;

    private Boolean isInEditMode = false;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DatabaseHandler.getInstance();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMember(ActionEvent event) {
        String mName = StringUtils.trimToEmpty(name.getText());
        String mID = StringUtils.trimToEmpty(id.getText());
        String mMobile = StringUtils.trimToEmpty(mobile.getText());
        String mEmail = StringUtils.trimToEmpty(email.getText());

        String[] names  = mName.split(" ");
        String fName = names[0];
        String lName = "";
        if (names.length > 1) {
            lName = names[1];
        }

        boolean flag = mName.isEmpty() || mID.isEmpty() || mMobile.isEmpty() || mEmail.isEmpty();
        if (flag) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter data in all fields.");
            return;
        }

        if (isInEditMode) {
            handleUpdateMember();
            return;
        }

        if (doesMemberExist(mID)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Duplicate member id", "Member with same id exists.\nPlease use new ID");
            return;
        }

        MemberListController.Member member = new MemberListController.Member(mName, mID, mMobile, mEmail);
        saveLibraryMember(mID, fName, lName, mMobile, "", "", "", "");
        AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "New member added", mName + " has been added");
        clearEntries();
        close();
    }

    public void infalteUI(MemberListController.Member member) {
        name.setText(member.getName());
        id.setText(member.getId());
        id.setEditable(false);
        mobile.setText(member.getMobile());
        email.setText(member.getEmail());

        isInEditMode = Boolean.TRUE;
    }

    private void clearEntries() {
        name.clear();
        id.clear();
        mobile.clear();
        email.clear();
    }

    private void handleUpdateMember() {
        MemberListController.Member member = new MemberListController.Member(name.getText(), id.getText(), mobile.getText(), email.getText());
        if (DatabaseHandler.getInstance().updateMember(member)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Success", "Member data updated.");
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Failed", "Cant update member.");
        }
    }

    private  void close(){
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }

}
