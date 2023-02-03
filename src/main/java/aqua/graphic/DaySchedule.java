package aqua.graphic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import aqua.util.DateUtils;
import aqua.util.Timeable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


public class DaySchedule extends VBox {
    private static final double CANVAS_WIDTH = 600;
    private static final double CANVAS_HEIGHT = 15;
    private static final double MINUTES_IN_A_DAY = 1440;


    public DaySchedule(LocalDateTime startTime, List<Timeable> timeables) {
        System.out.println(timeables);
        List<? extends List<Timeable>> sepTimeable = separateConflicting(timeables);
        System.out.println(sepTimeable);
        for (List<Timeable> row : sepTimeable) {
            System.out.println(startTime);
            addRow(startTime, row);
        }
    }


    private List<? extends List<Timeable>> separateConflicting(List<Timeable> timeables) {
        ArrayList<ArrayList<Timeable>> sepTimeables = new ArrayList<>();
        for (Timeable timeable : timeables) {
            boolean isAdded = false;
            for (List<Timeable> timeableSet : sepTimeables) {
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


    private boolean hasConflict(Timeable timeable, List<Timeable> timeables) {
        for (Timeable storedTimeable : timeables) {
            if (DateUtils.isIntersecting(
                        timeable.getStart(), timeable.getEnd(),
                        storedTimeable.getStart(), storedTimeable.getEnd())) {
                return true;
            }
        }
        return false;
    }


    private void addRow(LocalDateTime startTime, List<Timeable> timeables) {
        Pane pane = new Pane();
        pane.setPrefWidth(CANVAS_WIDTH);
        pane.setPrefHeight(CANVAS_HEIGHT);
        for (Timeable timeable : timeables) {
            double startX = (startTime.until(timeable.getStart(), ChronoUnit.MINUTES) / MINUTES_IN_A_DAY)
                    * CANVAS_WIDTH;
            double endX = (startTime.until(timeable.getEnd(), ChronoUnit.MINUTES) / MINUTES_IN_A_DAY)
                    * CANVAS_WIDTH;
            System.out.println(startX);
            System.out.println(endX);
            Rectangle rectangle = new Rectangle(endX - startX, CANVAS_HEIGHT);
            pane.getChildren().add(rectangle);
            rectangle.setLayoutX(startX);
        }
        getChildren().add(pane);
    }
}
