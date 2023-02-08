package ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * A subclass of HBox that represents the input UI.
 * @author Nicholas Lee
 */
public class FXInputBox extends HBox {

    public FXInputBox(TextField userInput, Button sendButton) {
        this.setSpacing(10);
        this.getChildren().addAll(userInput, sendButton);
    }
}
