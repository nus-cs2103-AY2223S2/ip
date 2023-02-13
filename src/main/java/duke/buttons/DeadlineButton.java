package duke.buttons;

import duke.functions.CreateDeadline;
import duke.functions.Functions;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DeadlineButton extends DukeButton {
    /**
     * Constructor to create an instance of a DeadLine button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for a DeadLine button
     */
    public DeadlineButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    /**
     * Returns a pane holding the form that appears after the deadline button is pressed
     * @return a VBox
     */
    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Task Description:");
        TextField desTextField = new TextField();

        Label endLabel = new Label("Deadline:");
        DatePicker deadlineMenu = new DatePicker();
        deadlineMenu.getEditor().setDisable(true);
        ComboBox hourMenu = hourPicker();
        ComboBox minMenu = minPicker();

        HBox inputPanes = new HBox();
        inputPanes.getChildren().addAll(deadlineMenu, hourMenu, minMenu);

        Button addTaskButton = new Button("Add Task");
        vbox.getChildren().addAll(desLabel, desTextField, endLabel, inputPanes, addTaskButton);

        addTaskButton.setOnMouseClicked((event) -> {
            String des = desTextField.getText();
            LocalDate endDate = deadlineMenu.getValue();
            String endHour = (String) hourMenu.getValue();
            String endMin = (String) minMenu.getValue();
            LocalDateTime end = LocalDateTime.of(endDate, LocalTime.parse(endHour + ":" + endMin));
            CreateDeadline.deadline(super.fn, des, end);
        });
        return vbox;
    }



}
