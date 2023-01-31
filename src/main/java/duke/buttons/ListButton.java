package duke.buttons;

import duke.functions.Functions;
import duke.functions.ListTask;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ListButton extends DukeButton {
    public ListButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            new ListTask(this.fn).list();
        });
    }

    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        return vbox;
    }
}
