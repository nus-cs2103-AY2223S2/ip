package duke.buttons;

import duke.functions.Functions;
import duke.functions.MarkTask;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MarkButton extends DukeButton {
    public MarkButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

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
