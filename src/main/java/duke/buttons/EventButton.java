package duke.buttons;

import duke.dukeexceptions.DukeException;
import duke.functions.CreateEvent;
import duke.functions.Functions;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventButton extends DukeButton {
    /**
     * Constructor to create an instance of an Event button
     * @param s Text label of the button
     * @param i Pane that button belongs to
     * @param o Pane that will output results of this button
     * @param f Functions defined for an Event button
     */
    public EventButton(String s, Pane i, Pane o, Functions f) {
        super(s, i, o, f);
        this.button.setOnMouseClicked((event) -> {
            this.inputLayout.getChildren().clear();
            this.inputLayout.getChildren().add(inputForm());
        });
    }

    /**
     * Returns a pane holding the form that appears after the event button is pressed
     * @return a VBox
     */
    @Override
    public VBox inputForm() {
        VBox vbox = new VBox();
        Label desLabel = new Label("Task Description:");
        TextField desTextField = new TextField();

        Label startLabel = new Label("Start:");
        DatePicker startDateMenu = new DatePicker();
        startDateMenu.getEditor().setDisable(true);
        ComboBox startHourMenu = hourPicker();
        ComboBox startMinMenu = minPicker();
        HBox startPane = new HBox();
        startPane.getChildren().addAll(startDateMenu, startHourMenu, startMinMenu);

        Label endLabel = new Label("End:");
        DatePicker endDateMenu = new DatePicker();
        endDateMenu.getEditor().setDisable(true);
        ComboBox endHourMenu = hourPicker();
        ComboBox endMinMenu = minPicker();
        HBox endPane = new HBox();
        endPane.getChildren().addAll(endDateMenu, endHourMenu, endMinMenu);

        Button addTaskButton = new Button("Add Task");
        vbox.getChildren().addAll(desLabel, desTextField, startLabel, startPane, endLabel, endPane, addTaskButton);

        addTaskButton.setOnMouseClicked((event) -> {
            String des = desTextField.getText();

            LocalDate startDate = startDateMenu.getValue();
            String startHour = (String) startHourMenu.getValue();
            String startMin = (String) startMinMenu.getValue();


            LocalDate endDate = endDateMenu.getValue();
            String endHour = (String) endHourMenu.getValue();
            String endMin = (String) endMinMenu.getValue();

            try {
                LocalDateTime start = LocalDateTime.of(startDate, LocalTime.parse(startHour + ":" + startMin));
                LocalDateTime end = LocalDateTime.of(endDate, LocalTime.parse(endHour + ":" + endMin));
                CreateEvent.events(super.fn, des, start, end);
            } catch (DateTimeParseException e) {
                try {
                    throw new DukeException(outputLayout, "Inputs cannot be empty");
                } catch (DukeException ex) {}
            }
        });

        return vbox;
    }
}
