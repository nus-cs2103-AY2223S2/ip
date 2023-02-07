package aqua.graphic.schedule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import aqua.util.DateUtils;
import aqua.util.Period;
import javafx.css.PseudoClass;
import javafx.scene.Node;
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
    public DaySchedule(LocalDateTime startTime, List<SchedulePeriod> timeables, double rowWidth) {
        this.rowWidth = rowWidth;
        getChildren().addAll(rowDisplayArea);
        List<? extends List<SchedulePeriod>> sepTimeable = separateConflicting(timeables);
        for (List<SchedulePeriod> row : sepTimeable) {
            rowDisplayArea.getChildren().add(createRow(startTime, row));
        }
        if (sepTimeable.isEmpty()) {
            rowDisplayArea.getChildren().add(createRow());
        }
    }


    /**
     * Separates a list of {@code ScheduleTimeables} such that none of them are
     * conflicting. The return type is in the structure of a list of a list of
     * unconflicting {@code ScheduleTimeable}.
     *
     * @param timeables - the list of {@code ScheduleTimeable} to separate.
     * @return a list of a list of unconflicting {@code ScheduleTimeable}.
     */
    private List<? extends List<SchedulePeriod>> separateConflicting(List<SchedulePeriod> timeables) {
        double threshold = (MIN_BLOCK_WIDTH / rowWidth) * MINUTES_IN_A_DAY;
        ArrayList<ArrayList<SchedulePeriod>> sepTimeables = new ArrayList<>();
        for (SchedulePeriod timeable : timeables) {
            boolean isAdded = false;
            for (List<SchedulePeriod> timeableSet : sepTimeables) {
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


    private boolean hasConflict(Period timeable, List<? extends Period> timeables, double threshold) {
        for (Period storedTimeable : timeables) {
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
     * @return the graphical representation of a row of
     *      {@code ScheduleTimeable}.
     */
    private Pane createRow() {
        Pane pane = new Pane();
        pane.setMinWidth(rowWidth);
        pane.setMaxHeight(ROW_HEIGHT);
        pane.getChildren().add(createMarkings());
        return pane;
    }


    /**
     * Creates a row populated with blocks of {@code ScheduleTimeable} as
     * specified.
     *
     * @param startTime - the start time of the day.
     * @param timeables - the list of {@code ScheduleTimeable} to display.
     * @return the graphical representation of a row of
     *      {@code ScheduleTimeable}.
     */
    private Pane createRow(LocalDateTime startTime, List<SchedulePeriod> timeables) {
        Pane rowPane = createRow();

        for (SchedulePeriod timeable : timeables) {
            double startOffset = startTime.until(timeable.getStart(), ChronoUnit.MINUTES);
            double startX = convertMinsToRowPix(startOffset);

            double endOffset = startTime.until(timeable.getEnd(), ChronoUnit.MINUTES);
            double endX = convertMinsToRowPix(endOffset);

            double width = endX - startX;
            if (width <= MIN_BLOCK_WIDTH) {
                startX -= MIN_BLOCK_WIDTH / 2;
                startX = Math.max(0D, startX);
                startX = Math.min(rowWidth - MIN_BLOCK_WIDTH, startX);
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


    /**
     * Creates a block to represent a {@code ScheduleTimeable} graphically.
     *
     * @param timeable - the {@code ScheduleTimeable} to display.
     * @param width - the width of the block.
     * @return a {@code Pane} that represents the {@code ScheduleTimeable}
     *      graphically.
     */
    private Pane createDisplayBlock(SchedulePeriod timeable, double width) {
        Pane block = new Pane();
        block.setMinHeight(ROW_HEIGHT);
        block.setMinWidth(width);
        block.getStyleClass().setAll(timeable.getStyleClass());
        Tooltip.install(block, createTooltip(timeable));
        for (PseudoClass pseudoClass : timeable.getPseudoClass()) {
            block.pseudoClassStateChanged(pseudoClass, true);
        }
        block.setOnMouseEntered(e -> setBlockHoverState(timeable, true));
        block.setOnMouseExited(e -> setBlockHoverState(timeable, false));
        timeable.addLink(block);
        return block;
    }


    private void setBlockHoverState(SchedulePeriod period, boolean isHover) {
        for (Node block : period.getLinks()) {
            block.pseudoClassStateChanged(PSEUDO_CLASS_HOVER, isHover);
        }
    }


    private Tooltip createTooltip(SchedulePeriod timeable) {
        Tooltip tooltip = new Tooltip(timeable.toString());
        tooltip.setShowDelay(Duration.seconds(TOOLTIP_SHOW_DELAY));
        tooltip.setHideDelay(Duration.seconds(TOOLTIP_HIDE_DELAY));
        tooltip.setShowDuration(Duration.seconds(Double.MAX_VALUE));
        return tooltip;
    }
}
