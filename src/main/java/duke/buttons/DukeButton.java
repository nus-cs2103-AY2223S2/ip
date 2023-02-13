package duke.buttons;

import duke.functions.Functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class DukeButton {
    Button button;
    Pane inputLayout;
    Pane outputLayout;
    Functions fn;

    /**
     * Constructor to create an instance of a button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a DeadLine button
     */
    public DukeButton(String s, Pane i, Pane o, Functions f) {
        this.button = new Button(s);
        this.button.wrapTextProperty().setValue(true);
        this.inputLayout = i;
        this.outputLayout = o;
        this.fn = f;
    }

    /**
     * Abstract method that all button must have. Dictates that all button in this pane will create an input form
     * @return a VBox
     */
    abstract public VBox inputForm();

    public Node getButton() {
        return this.button;
    }

    public ComboBox hourPicker() {
        ComboBox hourMenu = new ComboBox();
        hourMenu.setPromptText("HH");
        ObservableList<String> hourOptions = FXCollections.observableArrayList();
        for (int i = 0; i < 24; i++) {
            hourOptions.add(String.format("%02d",i));
        }
        hourMenu.setItems(hourOptions);
        return hourMenu;
    }

    public ComboBox minPicker() {
        ComboBox minMenu = new ComboBox();
        minMenu.setPromptText("mm");
        ObservableList<String> minOptions = FXCollections.observableArrayList();
        for (int i = 0; i < 60; i++) {
            minOptions.add(String.format("%02d",i));
        }
        minMenu.setItems(minOptions);
        return minMenu;
    }

    public ComboBox indexPicker() {
        ComboBox markMenu = new ComboBox();
        markMenu.setPromptText("Choose task index");
        ObservableList<String> taskOptions = FXCollections.observableArrayList();
        for (int i = 1; i <= fn.getTl().count(); i++) {
            taskOptions.add(String.format("%d",i));
        }
        markMenu.setItems(taskOptions);
        return markMenu;
    }
}
