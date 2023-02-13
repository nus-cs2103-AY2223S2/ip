package duke.buttons;

import duke.functions.Functions;
import duke.functions.MarkTask;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MarkButton extends DukeButton {
    /**
     * Constructor to create an instance of a Mark button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a Mark button
     */
    public MarkButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    /**
     * Returns a pane holding the form that appears after the mark button is pressed
     * @return a VBox
     */
    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Which task to mark?:");
        TextField markTextField = new TextField();
        Button markTaskButton = new Button("Mark Task");
        vbox.getChildren().addAll(desLabel, markTextField, markTaskButton);

        markTaskButton.setOnMouseClicked((event) -> {
            String markIndex = markTextField.getText();
            MarkTask.mark(super.fn, markIndex, true);
        });

        return vbox;
    }
}
