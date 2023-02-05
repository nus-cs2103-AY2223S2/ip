package aqua.graphic.schedule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import aqua.util.DateUtils;
import aqua.util.Timeable;
import javafx.css.PseudoClass;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class DaySchedule extends HBox {
    private static final double ROW_WIDTH = 1000;
    private static final double ROW_HEIGHT = 20;
    private static final double HOURS_IN_A_DAY = 24;
    private static final double MINUTES_IN_A_DAY = 1440;
    private static final double MIN_WIDTH_PIXS = ROW_HEIGHT;
    private static final double MIN_WIDTH_MINS = (MIN_WIDTH_PIXS / ROW_WIDTH) * MINUTES_IN_A_DAY;
    private static final double TOOLTIP_SHOW_DELAY = 0;
    private static final double TOOLTIP_HIDE_DELAY = 0;

    private final VBox rowDisplayArea = new VBox();


    public DaySchedule(LocalDateTime startTime, List<ScheduleTimeable> timeables) {
        getChildren().addAll(rowDisplayArea);
        List<? extends List<ScheduleTimeable>> sepTimeable = separateConflicting(timeables);
        for (List<ScheduleTimeable> row : sepTimeable) {
            rowDisplayArea.getChildren().add(createRow(startTime, row));
        }
        if (sepTimeable.isEmpty()) {
            rowDisplayArea.getChildren().add(createRow());
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
                        storedTimeable.getStart(), storedTimeable.getEnd(),
                        MIN_WIDTH_MINS)) {
                return true;
            }
        }
        return false;
    }


    private Pane createRow() {
        Pane pane = new Pane();
        pane.setMinWidth(ROW_WIDTH);
        pane.setMaxHeight(ROW_HEIGHT);
        pane.getChildren().add(drawMarkings());
        return pane;
    }


    private Pane createRow(LocalDateTime startTime, List<ScheduleTimeable> timeables) {
        Pane pane = createRow();

        for (ScheduleTimeable timeable : timeables) {
            double startX = (startTime.until(timeable.getStart(), ChronoUnit.MINUTES) / MINUTES_IN_A_DAY)
                    * ROW_WIDTH;
            startX = Math.max(0, (int) startX);
            double endX = (startTime.until(timeable.getEnd(), ChronoUnit.MINUTES) / MINUTES_IN_A_DAY)
                    * ROW_WIDTH;
            endX = Math.min(ROW_WIDTH, (int) endX);

            if (Math.abs(endX - startX) <= MIN_WIDTH_PIXS) {
                startX -= MIN_WIDTH_PIXS / 2;
                endX += MIN_WIDTH_PIXS / 2;
            }

            Pane scheduleBox = new Pane();
            scheduleBox.setMinHeight(ROW_HEIGHT);
            scheduleBox.setMinWidth(endX - startX);
            scheduleBox.getStyleClass().setAll(timeable.getStyleClass());
            Tooltip.install(scheduleBox, createTooltip(timeable));
            for (PseudoClass pseudoClass : timeable.getPseudoClass()) {
                scheduleBox.pseudoClassStateChanged(pseudoClass, true);
            }

            pane.getChildren().add(scheduleBox);
            scheduleBox.setLayoutX(startX);
        }

        return pane;
    }


    private Canvas drawMarkings() {
        Canvas canvas = new Canvas(ROW_WIDTH, ROW_HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();

        for (int i = 0; i <= HOURS_IN_A_DAY; i++) {
            double xMark = ((int) ((i / HOURS_IN_A_DAY) * ROW_WIDTH)) + 0.5;
            context.strokeLine(xMark, 0, xMark, ROW_HEIGHT);
        }

        return canvas;
    }


    private Tooltip createTooltip(ScheduleTimeable timeable) {
        Tooltip tooltip = new Tooltip(timeable.toString());
        tooltip.setShowDelay(Duration.seconds(TOOLTIP_SHOW_DELAY));
        tooltip.setHideDelay(Duration.seconds(TOOLTIP_HIDE_DELAY));
        tooltip.setShowDuration(Duration.seconds(Double.MAX_VALUE));
        return tooltip;
    }
}
