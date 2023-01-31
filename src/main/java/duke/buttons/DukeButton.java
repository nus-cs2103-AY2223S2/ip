package duke.buttons;

import duke.functions.Functions;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class DukeButton {
    Button button;
    Pane inputLayout;
    Pane outputLayout;
    Functions fn;

    public DukeButton(String s, Pane i, Pane o, Functions f) {
        this.button = new Button(s);
        this.button.wrapTextProperty().setValue(true);
        this.inputLayout = i;
        this.outputLayout = o;
        this.fn = f;
    }

    public Button getButton() {
        return this.button;
    }

    abstract public VBox inputForm();

}
