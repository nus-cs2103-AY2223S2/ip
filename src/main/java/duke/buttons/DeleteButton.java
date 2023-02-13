package duke.buttons;

import duke.dukeexceptions.DukeException;
import duke.functions.DeleteTask;
import duke.functions.Functions;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DeleteButton extends DukeButton {
    /**
     * Constructor to create an instance of a Delete button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a Delete button
     */
    public DeleteButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    /**
     * Returns a pane holding the form that appears after the delete button is pressed
     * @return a VBox
     */
    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Which Task to delete?:");
        ComboBox deleteMenu = indexPicker();
        Button delTaskButton = new Button("Delete Task");
        vbox.getChildren().addAll(desLabel, deleteMenu, delTaskButton);

        delTaskButton.setOnMouseClicked((event) -> {
            String delIndex = (String) deleteMenu.getValue();
            try {
                DeleteTask.delete(super.fn, delIndex);
            } catch (DukeException e) {}
        });
        return vbox;
    }
}
