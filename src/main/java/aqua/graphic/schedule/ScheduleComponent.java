package aqua.graphic.schedule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;

public class ScheduleComponent extends VBox {
    private static final int DAYS_IN_WEEK = 7;


    public ScheduleComponent(LocalDateTime startTime, List<? extends ScheduleTimeable> timeables) {
        List<? extends List<ScheduleTimeable>> rows = split(startTime, timeables);
        for (int i = 0; i < rows.size(); i++) {
            addRow(startTime.plusDays(i), rows.get(i));
        }
    }


    private List<? extends List<ScheduleTimeable>> split(
                LocalDateTime startTime, List<? extends ScheduleTimeable> timeables) {
        ArrayList<ArrayList<ScheduleTimeable>> sepTimeables = new ArrayList<>(DAYS_IN_WEEK);
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            sepTimeables.add(new ArrayList<>());
        }
        for (ScheduleTimeable timeable : timeables) {
            int startDay = (int) startTime.until(timeable.getStart(), ChronoUnit.DAYS);
            startDay = Math.max(0, startDay);
            int endDay = (int) startTime.until(timeable.getEnd(), ChronoUnit.DAYS);
            endDay = Math.min(DAYS_IN_WEEK - 1, endDay);
            for (int i = startDay; i <= endDay; i++) {
                sepTimeables.get(i).add(timeable);
            }
        }
        return sepTimeables;
    }


    private void addRow(LocalDateTime startTime, List<ScheduleTimeable> timeables) {
        getChildren().add(new DaySchedule(startTime, timeables));
    }
}
