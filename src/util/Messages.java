package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Messages {

	public static void showAlertDialog(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.show();
    }
}
