package duke.buttons;

import duke.functions.Functions;
import duke.functions.MarkTask;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UnmarkButton extends DukeButton {
    /**
     * Constructor to create an instance of a unmark button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a unmark button
     */
    public UnmarkButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    /**
     * Returns a pane holding the form that appears after the unmark button is pressed
     * @return a VBox
     */
    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Which task to unmark?:");
        TextField unmarkTextField = new TextField();
        Button unmarkTaskButton = new Button("UnMark Task");
        vbox.getChildren().addAll(desLabel, unmarkTextField, unmarkTaskButton);

        unmarkTaskButton.setOnMouseClicked((event) -> {
            String markIndex = unmarkTextField.getText();
            MarkTask.mark(super.fn, markIndex, false);
        });

        return vbox;
    }
}
