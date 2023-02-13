package duke.ui;

import duke.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for gui.components.Landing. Provides the layout for the other controls.
 */
public class Landing extends AnchorPane {
    @FXML
    private Button startButton;

    /**
     * This function is called when the GUI window starts.
     * It is used to set icons, title, and on start-up commands.
     */
    @FXML
    public void initialize() {
    }

    public void goToMainPage(Main main) {
        startButton.setOnAction(event -> main.showMainPage());
    }
}