package duke.buttons;

import duke.Functions;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UnmarkButton extends DukeButton {
    public UnmarkButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Which task to unmark?:");
        TextField unmarkTextField = new TextField();
        Button unmarkTaskButton = new Button("UnMark Task");
        vbox.getChildren().addAll(desLabel, unmarkTextField, unmarkTaskButton);

        unmarkTaskButton.setOnMouseClicked((event) -> {
            String markIndex = unmarkTextField.getText();
            this.fn.unmark(markIndex);
        });

        return vbox;
    }
}
