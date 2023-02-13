package duke.buttons;

import duke.functions.Functions;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class DukeButton {
    Button button;
    Pane inputLayout;
    Pane outputLayout;
    Functions fn;

    /**
     * Constructor to create an instance of a button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a DeadLine button
     */
    public DukeButton(String s, Pane i, Pane o, Functions f) {
        this.button = new Button(s);
        this.button.wrapTextProperty().setValue(true);
        this.inputLayout = i;
        this.outputLayout = o;
        this.fn = f;
    }

    /**
     * Abstract method that all button must have. Dictates that all button in this pane will create an input form
     * @return a VBox
     */
    abstract public VBox inputForm();

    public Node getButton() {
        return this.button;
    }
}
