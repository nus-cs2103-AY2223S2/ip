package duke.buttons;

import duke.functions.DeleteTask;
import duke.functions.Functions;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DeleteButton extends DukeButton {
    public DeleteButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Which Task to delete?:");
        TextField delTextField = new TextField();
        Button delTaskButton = new Button("Delete Task");
        vbox.getChildren().addAll(desLabel, delTextField, delTaskButton);

        delTaskButton.setOnMouseClicked((event) -> {
            String delIndex = delTextField.getText();
            DeleteTask.delete(super.fn, delIndex);
        });

        return vbox;
    }
}
