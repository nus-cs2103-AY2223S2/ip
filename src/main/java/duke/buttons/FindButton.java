package duke.buttons;

import duke.functions.FindTask;
import duke.functions.Functions;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FindButton extends DukeButton {
    /**
     * Constructor to create an instance of a Find button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a Find button
     */
    public FindButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    /**
     * Returns a pane holding the form that appears after the find button is pressed
     * @return a VBox
     */
    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label keywordLabel = new Label("Keyword:");
        TextField keywordTextField = new TextField();
        Button findTaskButton = new Button("Find Task");
        vbox.getChildren().addAll(keywordLabel, keywordTextField, findTaskButton);

        findTaskButton.setOnMouseClicked((event) -> {
            String keyword = keywordTextField.getText();
            FindTask.find(super.fn, keyword);
        });

        return vbox;
    }
}

