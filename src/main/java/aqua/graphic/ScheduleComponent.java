package aqua.graphic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import aqua.util.Timeable;
import javafx.scene.layout.VBox;

public class ScheduleComponent extends VBox {
    private static final int DAYS_IN_WEEK = 7;


    public ScheduleComponent(LocalDateTime startTime, List<Timeable> timeables) {
        List<? extends List<Timeable>> rows = split(startTime, timeables);
        for (int i = 0; i < rows.size(); i++) {
            addRow(startTime.plusDays(i), rows.get(i));
        }
    }


    private List<? extends List<Timeable>> split(LocalDateTime startTime, List<Timeable> timeables) {
        ArrayList<ArrayList<Timeable>> sepTimeables = new ArrayList<>(DAYS_IN_WEEK);
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            sepTimeables.add(new ArrayList<>());
        }
        for (Timeable timeable : timeables) {
            int startDay = (int) startTime.until(timeable.getStart(), ChronoUnit.DAYS);
            int endDay = (int) startTime.until(timeable.getEnd(), ChronoUnit.DAYS);
            for (int i = startDay; i <= endDay; i++) {
                sepTimeables.get(i).add(timeable);
            }
        }
        return sepTimeables;
    }


    private void addRow(LocalDateTime startTime, List<Timeable> timeables) {
        getChildren().add(new DaySchedule(startTime, timeables));
    }
}
