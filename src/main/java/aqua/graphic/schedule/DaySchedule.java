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
    private static final double MIN_BLOCK_WIDTH = 7;
    private static final double TOOLTIP_SHOW_DELAY = 0;
    private static final double TOOLTIP_HIDE_DELAY = 0;

    private static final PseudoClass PSEUDO_CLASS_HOVER = PseudoClass.getPseudoClass("hover");

    private final VBox rowDisplayArea = new VBox();

    private final double rowWidth;


    /**
     * Constructs a {@code DaySchedule} from the given parameters.
     *
     * @param startTime - the start time of the day.
     * @param periods - the list of {@code SchedulePeriod} to display.
     */
    public DaySchedule(LocalDateTime startTime, List<SchedulePeriod> periods, double rowWidth) {
        this.rowWidth = rowWidth;
        getChildren().addAll(rowDisplayArea);
        List<? extends List<SchedulePeriod>> sepPeriods = separateConflicting(periods);
        for (List<SchedulePeriod> row : sepPeriods) {
            rowDisplayArea.getChildren().add(createRow(startTime, row));
        }
        if (sepPeriods.isEmpty()) {
            rowDisplayArea.getChildren().add(createRow());
        }
    }


    /**
     * Separates a list of {@code SchedulePeriod} such that none of them are
     * conflicting. The return type is in the structure of a list of a list of
     * unconflicting {@code SchedulePeriod}.
     *
     * @param periods - the list of {@code SchedulePeriod} to separate.
     * @return a list of a list of unconflicting {@code SchedulePeriod}.
     */
    private List<? extends List<SchedulePeriod>> separateConflicting(List<SchedulePeriod> periods) {
        double threshold = (MIN_BLOCK_WIDTH / rowWidth) * MINUTES_IN_A_DAY;
        ArrayList<ArrayList<SchedulePeriod>> sepPeriods = new ArrayList<>();
        for (SchedulePeriod period : periods) {
            boolean isAdded = false;
            for (List<SchedulePeriod> periodSet : sepPeriods) {
                if (!hasConflict(period, periodSet, threshold)) {
                    periodSet.add(period);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                sepPeriods.add(new ArrayList<>(List.of(period)));
            }
        }
        return sepPeriods;
    }


    private boolean hasConflict(Period period, List<? extends Period> periods, double threshold) {
        for (Period storedPeriod : periods) {
            if (DateUtils.isIntersecting(
                        period.getStart(), period.getEnd(),
                        storedPeriod.getStart(), storedPeriod.getEnd(),
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
     *      {@code SchedulePeriod}.
     */
    private Pane createRow() {
        Pane pane = new Pane();
        pane.setMinWidth(rowWidth);
        pane.setMaxHeight(ROW_HEIGHT);
        pane.getChildren().add(createMarkings());
        return pane;
    }


    /**
     * Creates a row populated with blocks of {@code SchedulePeriod} as
     * specified.
     *
     * @param startTime - the start time of the day.
     * @param periods - the list of {@code SchedulePeriod} to display.
     * @return the graphical representation of a row of
     *      {@code SchedulePeriod}.
     */
    private Pane createRow(LocalDateTime startTime, List<SchedulePeriod> periods) {
        Pane rowPane = createRow();

        for (SchedulePeriod period : periods) {
            double startOffset = startTime.until(period.getStart(), ChronoUnit.MINUTES);
            double startX = convertMinsToRowPix(startOffset);

            double endOffset = startTime.until(period.getEnd(), ChronoUnit.MINUTES);
            double endX = convertMinsToRowPix(endOffset);

            double width = endX - startX;
            if (width <= MIN_BLOCK_WIDTH) {
                startX -= MIN_BLOCK_WIDTH / 2;
                startX = Math.max(0D, startX);
                startX = Math.min(rowWidth - MIN_BLOCK_WIDTH, startX);
                width = MIN_BLOCK_WIDTH;
            }

            Pane block = createDisplayBlock(period, width);

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
     * Creates a block to represent a {@code SchedulePeriod} graphically.
     *
     * @param period - the {@code SchedulePeriod} to display.
     * @param width - the width of the block.
     * @return a {@code Pane} that represents the {@code SchedulePeriod}
     *      graphically.
     */
    private Pane createDisplayBlock(SchedulePeriod period, double width) {
        Pane block = new Pane();
        block.setMinHeight(ROW_HEIGHT);
        block.setMinWidth(width);
        block.getStyleClass().setAll(period.getStyleClass());
        Tooltip.install(block, createTooltip(period));
        for (PseudoClass pseudoClass : period.getPseudoClass()) {
            block.pseudoClassStateChanged(pseudoClass, true);
        }
        block.setOnMouseEntered(e -> setBlockHoverState(period, true));
        block.setOnMouseExited(e -> setBlockHoverState(period, false));
        period.addLink(block);
        return block;
    }


    private void setBlockHoverState(SchedulePeriod period, boolean isHover) {
        for (Node block : period.getLinks()) {
            block.pseudoClassStateChanged(PSEUDO_CLASS_HOVER, isHover);
        }
    }


    private Tooltip createTooltip(SchedulePeriod period) {
        Tooltip tooltip = new Tooltip(period.toString());
        tooltip.setShowDelay(Duration.seconds(TOOLTIP_SHOW_DELAY));
        tooltip.setHideDelay(Duration.seconds(TOOLTIP_HIDE_DELAY));
        tooltip.setShowDuration(Duration.seconds(Double.MAX_VALUE));
        return tooltip;
    }
}
