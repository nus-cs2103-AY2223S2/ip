package aqua.graphic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import aqua.aquatask.AquaTask;
import aqua.graphic.schedule.ScheduleComponent;
import aqua.graphic.schedule.ScheduleTimeable;
import aqua.manager.TaskFilterReport;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TaskView extends UiComponent<VBox> {
    private static final String PATH_FXML_FILE = "TaskView.fxml";

    private static final PseudoClass PSEUDO_CLASS_COMPLETE = PseudoClass.getPseudoClass("complete");
    private static final PseudoClass PSEUDO_CLASS_INCOMPLETE = PseudoClass.getPseudoClass("incomplete");

    @FXML private VBox scheduleDisplayArea;
    @FXML private VBox todoDisplayArea;


    public TaskView(LocalDateTime startTime, TaskFilterReport report) {
        super(PATH_FXML_FILE);
        initialiseSchedule(startTime, report.filtered);
    }


    private void initialiseSchedule(LocalDateTime startTime, List<AquaTask> tasks) {
        List<ScheduleTimeable> timeables = tasks.stream()
                .map(task -> new TimeableAquaTask(task))
                .collect(Collectors.toList());
        scheduleDisplayArea.getChildren().add(new ScheduleComponent(startTime, timeables));
    }





    private class TimeableAquaTask extends ScheduleTimeable {
        private final AquaTask task;


        TimeableAquaTask(AquaTask task) {
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
}
