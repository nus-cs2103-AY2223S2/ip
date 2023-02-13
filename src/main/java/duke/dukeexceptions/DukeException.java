package duke.dukeexceptions;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DukeException extends Exception {
    public DukeException(Pane output, String message) {
        super(message);
        Label textToAdd = new Label(message);
        textToAdd.setWrapText(true);
        output.getChildren().clear();
        output.getChildren().add(textToAdd);
    }
}














