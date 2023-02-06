package aqua.graphic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import aqua.graphic.schedule.WeekSchedule;
import aqua.graphic.schedule.ScheduleTimeable;
import aqua.manager.TaskFilterReport;
import aqua.usertask.UserTask;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


/** A {@code UiComponent} to display a view of tasks. */
public class TaskView extends UiComponent<VBox> {
    private static final String PATH_FXML_FILE = "TaskView.fxml";

    private static final PseudoClass PSEUDO_CLASS_COMPLETE = PseudoClass.getPseudoClass("complete");
    private static final PseudoClass PSEUDO_CLASS_INCOMPLETE = PseudoClass.getPseudoClass("incomplete");

    @FXML private VBox scheduleDisplayArea;
    @FXML private VBox todoDisplayArea;


    /**
     * Constructs a {@code TaskView} from the given parameters.
     *
     * @param startTime - the start time.
     * @param report - the filter of tasks to display.
     */
    public TaskView(LocalDateTime startTime, TaskFilterReport report) {
        super(PATH_FXML_FILE);
        initialiseSchedule(startTime, report.filtered);
        initialiseTodoView(report.unknown);
    }


    private void initialiseSchedule(LocalDateTime startTime, List<UserTask> tasks) {
        List<ScheduleTimeable> timeables = tasks.stream()
                .map(task -> new TimeableAquaTask(task))
                .collect(Collectors.toList());
        scheduleDisplayArea.getChildren().add(new WeekSchedule(startTime, timeables));
    }


    private void initialiseTodoView(List<UserTask> tasks) {
        for (UserTask task : tasks) {
            todoDisplayArea.getChildren().add(new TodoLabel(task));
        }
    }





    private static class TimeableAquaTask extends ScheduleTimeable {
        private final UserTask task;


        TimeableAquaTask(UserTask task) {
            this.task = task;
        }


        @Override
        public LocalDateTime getStart() {
            return task.getStart().orElseGet(this::getEnd);
        }


        @Override
        public LocalDateTime getEnd() {
            return task.getEnd().orElseThrow();
        }


        @Override
        public List<String> getStyleClass() {
            if (getStart().isEqual(getEnd())) {
                return List.of("deadline-box");
            }
            return List.of("schedule-box");
        }


        @Override
        public List<PseudoClass> getPseudoClass() {
            if (task.isComplete()) {
                return List.of(PSEUDO_CLASS_COMPLETE);
            }
            return List.of(PSEUDO_CLASS_INCOMPLETE);
        }


        @Override
        public String toString() {
            return task.toString();
        }
    }





    private static class TodoLabel extends VBox {
        private static final double MAX_WIDTH = 600;
        private static final Insets MARGIN = new Insets(10, 10, 10, 10);


        TodoLabel(UserTask task) {
            setMaxWidth(MAX_WIDTH);
            getStyleClass().add("todo-label");
            if (task.isComplete()) {
                pseudoClassStateChanged(PSEUDO_CLASS_COMPLETE, true);
            } else {
                pseudoClassStateChanged(PSEUDO_CLASS_INCOMPLETE, true);
            }
            initialiseLabel(task.toString());
        }


        private void initialiseLabel(String name) {
            Label label = new Label(name);
            label.setMaxWidth(MAX_WIDTH);
            label.setWrapText(true);
            label.setTextAlignment(TextAlignment.CENTER);
            VBox.setMargin(label, MARGIN);
            getChildren().add(label);
        }
    }
}
