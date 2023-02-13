package duke.ui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class UI {
    public static ButtonType showRetryDialog(AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message, new ButtonType[] { ButtonType.YES, ButtonType.NO });
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType buttonRes = result.orElseThrow(() -> {
            throw new RuntimeException("Alert dialog should not have returned Optional.empty");
        });
        
        return buttonRes;
    }
}
