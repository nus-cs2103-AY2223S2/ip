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


/** A graphical display of a day's schedule. */
public class DaySchedule extends HBox {
    private static final double ROW_HEIGHT = 20;
    private static final double HOURS_IN_A_DAY = 24;
    private static final double MINUTES_IN_A_DAY = 1440;
    private static final double MICRO_IN_MINUTE = 6E7;
    private static final double MIN_BLOCK_WIDTH = ROW_HEIGHT;
    private static final double TOOLTIP_SHOW_DELAY = 0;
    private static final double TOOLTIP_HIDE_DELAY = 0;

    private static final PseudoClass PSEUDO_CLASS_HOVER = PseudoClass.getPseudoClass("hover");

    private final VBox rowDisplayArea = new VBox();

    private final double rowWidth;


    /**
     * Constructs a {@code DaySchedule} from the given parameters.
     *
     * @param startTime - the start time of the day.
     * @param timeables - the list of {@code ScheduleTimeable} to display.
     */
    public DaySchedule(LocalDateTime startTime, List<ScheduleTimeable> timeables, double rowWidth) {
        this.rowWidth = rowWidth;
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
        double threshold = (MIN_BLOCK_WIDTH / rowWidth) * MINUTES_IN_A_DAY;
        ArrayList<ArrayList<ScheduleTimeable>> sepTimeables = new ArrayList<>();
        for (ScheduleTimeable timeable : timeables) {
            boolean isAdded = false;
            for (List<ScheduleTimeable> timeableSet : sepTimeables) {
                if (!hasConflict(timeable, timeableSet, threshold)) {
                    timeableSet.add(timeable);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                sepTimeables.add(new ArrayList<>(List.of(timeable)));
            }
        }
        return sepTimeables;
    }


    private boolean hasConflict(Timeable timeable, List<? extends Timeable> timeables, double threshold) {
        for (Timeable storedTimeable : timeables) {
            if (DateUtils.isIntersecting(
                        timeable.getStart(), timeable.getEnd(),
                        storedTimeable.getStart(), storedTimeable.getEnd(),
                        threshold)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Creates an empty row.
     * 
     * @return the graphical representation of a row of {@code Timeable}.
     */
    private Pane createRow() {
        Pane pane = new Pane();
        pane.setMinWidth(rowWidth);
        pane.setMaxHeight(ROW_HEIGHT);
        pane.getChildren().add(createMarkings());
        return pane;
    }


    /**
     * Creates a row populated with blocks of {@code Timeable} as specified.
     * 
     * @param startTime - the start time of the day.
     * @param timeables - the list of {@code Timeable} to display.
     * @return the graphical representation of a row of {@code Timeable}.
     */
    private Pane createRow(LocalDateTime startTime, List<ScheduleTimeable> timeables) {
        Pane rowPane = createRow();

        for (ScheduleTimeable timeable : timeables) {
            double startOffset = startTime.until(timeable.getStart(), ChronoUnit.MINUTES);
            double startX = convertMinsToRowPix(startOffset);

            double durationMins = timeable.duration() / MICRO_IN_MINUTE;
            double width = convertMinsToRowPix(durationMins);

            if (width <= MIN_BLOCK_WIDTH) {
                startX -= MIN_BLOCK_WIDTH / 2;
                width = MIN_BLOCK_WIDTH;
            }

            Pane block = createDisplayBlock(timeable, width);

            rowPane.getChildren().add(block);
            block.setLayoutX(startX);
        }

        return rowPane;
    }


    /**
     * Creates the marking display for a row.
     * 
     * @return the marking display for a row.
     */
    private Canvas createMarkings() {
        Canvas canvas = new Canvas(rowWidth, ROW_HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();

        for (int i = 0; i <= HOURS_IN_A_DAY; i++) {
            double xMark = ((int) ((i / HOURS_IN_A_DAY) * rowWidth)) + 0.5;
            context.strokeLine(xMark, 0, xMark, ROW_HEIGHT);
        }

        return canvas;
    }


    private double convertMinsToRowPix(double mins) {
        double dayFrac = mins / MINUTES_IN_A_DAY;
        dayFrac = Math.min(Math.max(0D, dayFrac), 1D);
        return dayFrac * rowWidth;
    }


    private Pane createDisplayBlock(ScheduleTimeable timeable, double width) {
        Pane block = new Pane();
        block.setMinHeight(ROW_HEIGHT);
        block.setMinWidth(width);
        block.getStyleClass().setAll(timeable.getStyleClass());
        Tooltip.install(block, createTooltip(timeable));
        for (PseudoClass pseudoClass : timeable.getPseudoClass()) {
            block.pseudoClassStateChanged(pseudoClass, true);
        }
        block.setOnMouseEntered(e -> block.pseudoClassStateChanged(PSEUDO_CLASS_HOVER, true));
        block.setOnMouseExited(e -> block.pseudoClassStateChanged(PSEUDO_CLASS_HOVER, false));
        return block;
    }


    private Tooltip createTooltip(ScheduleTimeable timeable) {
        Tooltip tooltip = new Tooltip(timeable.toString());
        tooltip.setShowDelay(Duration.seconds(TOOLTIP_SHOW_DELAY));
        tooltip.setHideDelay(Duration.seconds(TOOLTIP_HIDE_DELAY));
        tooltip.setShowDuration(Duration.seconds(Double.MAX_VALUE));
        return tooltip;
    }
}
