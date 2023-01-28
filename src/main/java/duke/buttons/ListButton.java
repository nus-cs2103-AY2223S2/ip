package duke.buttons;

import duke.Functions;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ListButton extends DukeButton {
    public ListButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.fn.list();
        });
    }

    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        return vbox;
    }
}
