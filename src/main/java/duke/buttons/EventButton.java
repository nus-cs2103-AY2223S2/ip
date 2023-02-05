package duke.buttons;

import duke.functions.CreateEvent;
import duke.functions.Functions;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EventButton extends DukeButton {
    public EventButton(String s, Pane i, Pane o, Functions f) {
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
        Label startLabel = new Label("Start (yyyy-mm-dd hh:mm):");
        TextField startTextField = new TextField();
        Label endLabel = new Label("End (yyyy-mm-dd hh:mm):");
        TextField endTextField = new TextField();
        Button addTaskButton = new Button("Add Task");
        vbox.getChildren().addAll(desLabel, desTextField, startLabel, startTextField, endLabel, endTextField
                , addTaskButton);

        addTaskButton.setOnMouseClicked((event) -> {
            String des = desTextField.getText();
            String start = startTextField.getText();
            String end = endTextField.getText();
            CreateEvent.events(super.fn, des, start, end);
        });

        return vbox;
    }
}
