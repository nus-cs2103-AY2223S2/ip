package duke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * GUI for additional welcome message that shows at the top of the window.
 */
public class WelcomeDialog extends VBox {
    @FXML
    private VBox welcomeDialog;

    private WelcomeDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/WelcomeDialog.fxml"));
            fxmlLoader.setController(this);
            welcomeDialog = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private VBox getVBox() {
        return welcomeDialog;
    }

    /**
     * Creates a container with welcome message upon boot.
     *
     * @return Container of Vbox containing static text.
     */
    public static VBox getWelcomeDialog() {
        var w = new WelcomeDialog();
        return w.getVBox();
    }
}
