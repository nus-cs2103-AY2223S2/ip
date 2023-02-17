package kude.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kude.processor.Processor;

/**
 * Controller for the Main View
 */
public class MainView extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Processor processor;
    private Gui gui;


    private final Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput(ActionEvent actionEvent) {
        var input = userInput.getText();
        processor.runCommand(input);
        var response = gui.getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    public void setData(Processor processor, Gui gui) {
        this.processor = processor;
        this.gui = gui;
    }
}
