package duke.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import duke.Utils;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskCreator;
import duke.task.ToDo;

public class AddTaskWindow extends ControllerBase implements Initializable {
    private static Map<String, TaskCreator> taskCreators = Map.of(
        "ToDo", (desc, a, b) -> new ToDo(desc),
        "Deadline", (desc, due, a) -> new Deadline(desc, due),
        "Event", (desc, start, end) -> new Event(desc, start, end)
    );

    public static Dialog<Optional<Task>> getAddTaskDialog() throws IOException {
        AddTaskWindow controller = new AddTaskWindow();
        DialogPane pane = Utils.loadFxmlFile(AddTaskWindow.class.getResource("/view/AddTaskWindow.fxml"), controller);
        Dialog<Optional<Task>> diag = new Dialog<>();
        diag.setDialogPane(pane);
        diag.setResultConverter((buttonType) -> {
            if (buttonType == ButtonType.OK) {
                Task newTask = controller.createTask();
                return Optional.of(newTask);
            } else {
                return Optional.empty();
            }
        });
        pane.getButtonTypes().addAll(
            ButtonType.OK,
            ButtonType.CANCEL
        );
        pane.lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
            String error = null;

            String typeToAdd = controller.taskTypeComboBox.getValue();
            if (typeToAdd == null || typeToAdd.isBlank()) {
                error = "Select a type for the task!";
            }

            String taskDesc = controller.taskDescField.getText();
            if (taskDesc == null || taskDesc.isBlank()) {
                error = "A valid description for the task must be provided!";
            }

            if (typeToAdd.equals("Deadline") && controller.startDatePicker.getValue() == null) {
                error = "Need a deadline!";
            } else if (typeToAdd.equals("Event")) {
                if (controller.startDatePicker.getValue() == null) {
                    error = "Need a start time for event!";
                } else if (controller.endDatePicker.getValue() == null) {
                    error = "Need an end time for event!";
                }
            }

            if (error != null) {
                Alert alert = new Alert(AlertType.ERROR, error, new ButtonType[] { ButtonType.OK });
                alert.show();
                event.consume();
                return;
            } 
        });

        return diag;
    }

    private Task createTask() {
        String typeToAdd = taskTypeComboBox.getValue();
        String taskDesc = taskDescField.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        LocalTime time = LocalTime.now();

        LocalDateTime start = LocalDateTime.of(startDate, time);
        LocalDateTime end = LocalDateTime.of(endDate, time);
        return taskCreators.get(typeToAdd).createTask(taskDesc, start, end);
    }

    @FXML
    private ComboBox<String> taskTypeComboBox;

    @FXML
    private TextField taskDescField;

    @FXML
    private DatePicker startDatePicker;
    
    @FXML
    private DatePicker endDatePicker;

    @FXML
    private DatePicker startTimePicker;
    
    @FXML
    private DatePicker endTimePicker;

    private void onTaskTypeChanged() {
        String type = taskTypeComboBox.getValue();
        if (type == null || type.equals("ToDo")) {
            startDatePicker.setDisable(true);
            startTimePicker.setDisable(true);

            endDatePicker.setDisable(true);
            endTimePicker.setDisable(true);
        } else if (type.equals("Deadline")) {
            startDatePicker.setDisable(true);
            startTimePicker.setDisable(true);

            endDatePicker.setDisable(false);
            endTimePicker.setDisable(false);
        } else if (type.equals("Event")) {
            startDatePicker.setDisable(false);
            startTimePicker.setDisable(false);

            endDatePicker.setDisable(false);
            endTimePicker.setDisable(false);
        } else {
            throw new RuntimeException("Should not get here");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> taskTypesList = FXCollections.observableArrayList(taskCreators.keySet());
        taskTypeComboBox.setItems(taskTypesList);
        taskTypeComboBox
            .getSelectionModel()
            .selectedItemProperty()
            .addListener((option, oldValue, newValue) -> onTaskTypeChanged());
        startDatePicker.setDisable(true);
        endDatePicker.setDisable(true);
    }
}
