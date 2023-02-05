package duke.buttons;

import duke.functions.CreateTodo;
import duke.functions.Functions;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TodoButton extends DukeButton {

    public TodoButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Task Description:");
        TextField desTextField = new TextField();
        Button addTaskButton = new Button("Add Task");
        vbox.getChildren().addAll(desLabel, desTextField, addTaskButton);

        addTaskButton.setOnMouseClicked((event) -> {
            String des = desTextField.getText();
            CreateTodo.todo(super.fn, des);
        });

        return vbox;
    }
}
