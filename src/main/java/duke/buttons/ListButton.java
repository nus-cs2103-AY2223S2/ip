package duke.buttons;

import duke.functions.Functions;
import duke.functions.ListTask;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ListButton extends DukeButton {
    /**
     * Constructor to create an instance of a List button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a List button
     */
    public ListButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            ListTask.list(f);
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    /**
     * Returns a pane holding the form that appears after the list button is pressed
     * @return a VBox
     */
    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Sort task by:");
        Button deadlineSortBtn = new Button("Deadline/End time");
        Button defaultSortBtn = new Button("Default order");
        vbox.getChildren().addAll(desLabel, deadlineSortBtn, defaultSortBtn);

        deadlineSortBtn.setOnMouseClicked((event) -> {
            ListTask.deadlineSort(super.fn);
            ListTask.list(super.fn);
        });

        defaultSortBtn.setOnMouseClicked((event) -> {
            ListTask.defaultSort(super.fn);
            ListTask.list(super.fn);
        });
        return vbox;
    }
}
