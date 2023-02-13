package duke.buttons;

import duke.dukeexceptions.DukeException;
import duke.functions.CreateTodo;
import duke.functions.Functions;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TodoButton extends DukeButton {
    /**
     * Constructor to create an instance of a ToDo button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a ToDo button
     */
    public TodoButton(String s, Pane i, Pane o, Functions f) {
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
        Label desLabel = new Label("Task Description:");
        TextField desTextField = new TextField();
        Button addTaskButton = new Button("Add Task");
        vbox.getChildren().addAll(desLabel, desTextField, addTaskButton);

        addTaskButton.setOnMouseClicked((event) -> {
            String des = desTextField.getText();
            try {
                CreateTodo.todo(super.fn, des);
            } catch (DukeException e) {}
        });
        return vbox;
    }
}
