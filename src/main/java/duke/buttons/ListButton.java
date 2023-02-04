package duke.buttons;

import duke.functions.Functions;
import duke.functions.ListTask;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ListButton extends DukeButton {
    public ListButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            ListTask.list(f);
            this.inputLayout.getChildren().add(inputForm());
        });
    }

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
