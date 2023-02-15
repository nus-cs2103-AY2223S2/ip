package colette.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** Class that handles the separate help window of the GUI */
public class HelpWindow extends AnchorPane {

    private static boolean isOpen = false;
    private static Stage stage;

    private static String userGuideUrl = "https://zrei.github.io/ip/";

    @FXML
    private Text helpText;
    @FXML
    private Hyperlink userGuideHyperlink;

    /**
     * Initialize the help window.
     */
    @FXML
    public void initialize() {
        this.helpText.setText(GuiText.generateHelpWindowText());
        userGuideHyperlink.setOnAction((ActionEvent e) -> Gui.getGuiHostServices().showDocument(userGuideUrl));
        HelpWindow.isOpen = true;
    }

    /**
     * Launches the help window if it is not already open.
     */
    public static void launchHelpWindow() {
        if (HelpWindow.isOpen) {
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class
                    .getClassLoader().getResource("view/HelpWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Stage stage = new Stage();
            HelpWindow.stage = stage;
            stage.setTitle("Help");
            stage.setScene(scene);
            stage.setOnCloseRequest(event ->
                HelpWindow.isOpen = false
            );
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the help window if it is open.
     */
    public static void closeHelpWindow() {
        if (HelpWindow.isOpen) {
            HelpWindow.stage.close();
        }
    }

    public static boolean isOpen() {
        return HelpWindow.isOpen;
    }

}
