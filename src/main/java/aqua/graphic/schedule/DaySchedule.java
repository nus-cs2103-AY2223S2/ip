package aqua.graphic.schedule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import aqua.util.DateUtils;
import aqua.util.Timeable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class DaySchedule extends HBox {
    private static final double ROW_WIDTH = 600;
    private static final double ROW_HEIGHT = 15;
    private static final double MINUTES_IN_A_DAY = 1440;

    private final VBox rowDisplayArea = new VBox();


    public DaySchedule(LocalDateTime startTime, List<ScheduleTimeable> timeables) {
        getChildren().addAll(rowDisplayArea);
        List<? extends List<ScheduleTimeable>> sepTimeable = separateConflicting(timeables);
        for (List<ScheduleTimeable> row : sepTimeable) {
            addRow(startTime, row);
        }
    }


    private List<? extends List<ScheduleTimeable>> separateConflicting(List<ScheduleTimeable> timeables) {
        ArrayList<ArrayList<ScheduleTimeable>> sepTimeables = new ArrayList<>();
        for (ScheduleTimeable timeable : timeables) {
            boolean isAdded = false;
            for (List<ScheduleTimeable> timeableSet : sepTimeables) {
                if (!hasConflict(timeable, timeableSet)) {
                    timeableSet.add(timeable);
                    isAdded = true;
                    break;
                }
            }
            if (isAdded) {
                continue;
            }
            sepTimeables.add(new ArrayList<>(List.of(timeable)));
        }
        return sepTimeables;
    }


    private boolean hasConflict(Timeable timeable, List<? extends Timeable> timeables) {
        for (Timeable storedTimeable : timeables) {
            if (DateUtils.isIntersecting(
                        timeable.getStart(), timeable.getEnd(),
                        storedTimeable.getStart(), storedTimeable.getEnd())) {
                return true;
            }
        }
        return false;
    }


    private void addRow(LocalDateTime startTime, List<ScheduleTimeable> timeables) {
        Pane pane = new Pane();
        pane.setMinWidth(ROW_WIDTH);
        pane.setMaxHeight(ROW_HEIGHT);
        for (ScheduleTimeable timeable : timeables) {
            double startX = (startTime.until(timeable.getStart(), ChronoUnit.MINUTES) / MINUTES_IN_A_DAY)
                    * ROW_WIDTH;
            startX = Math.max(0, startX);
            double endX = (startTime.until(timeable.getEnd(), ChronoUnit.MINUTES) / MINUTES_IN_A_DAY)
                    * ROW_WIDTH;
            endX = Math.min(ROW_WIDTH, endX);

            Pane scheduleBox = new Pane();
            scheduleBox.setMinHeight(ROW_HEIGHT);
            scheduleBox.setMinWidth(endX - startX);
            scheduleBox.getStyleClass().setAll(timeable.getStyleClass());

            pane.getChildren().add(scheduleBox);
            scheduleBox.setLayoutX(startX);
        }
        rowDisplayArea.getChildren().add(pane);
    }
}
