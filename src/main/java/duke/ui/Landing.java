package duke.ui;

import duke.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for Landing. Provides the layout for the other controls.
 */
//@@author Zxun2-reused
//Inspired by Zxun2
public class Landing extends AnchorPane {
    @FXML
    private Button startButton;

    @FXML
    public void initialize() {
    }

    public void goToMainPage(Main main) {
        startButton.setOnAction(event -> main.showMainPage());
    }
}
//@@author
