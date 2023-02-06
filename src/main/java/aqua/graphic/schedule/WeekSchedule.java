package aqua.graphic.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


/** A graphical display of a week's schedule. */
public class WeekSchedule extends VBox {
    private static final double LABEL_WIDTH = 100;
    private static final double ROW_WIDTH = 1000;

    private static final int DAYS_IN_WEEK = 7;


    /**
     * Constructs a {@code WeekSchedule} from the given parameters.
     *
     * @param startTime - the start time of the week.
     * @param timeables - the list of {@code ScheduleTimeable} to display.
     */
    public WeekSchedule(LocalDateTime startTime, List<? extends SchedulePeriod> timeables) {
        List<? extends List<SchedulePeriod>> rows = split(startTime, timeables);
        getChildren().add(new ScheduleHeader(LABEL_WIDTH, ROW_WIDTH));
        for (int i = 0; i < rows.size(); i++) {
            addRow(startTime.plusDays(i), rows.get(i), i);
        }
    }


    /**
     * Splits a list of {@code ScheduleTimeable} into their day in the week.
     * The return type is a list of 7 lists of {@code ScheduleTimeable}.
     *
     * @param startTime - the time of the start of the week.
     * @param timeables - the list of {@code ScheduleTimeable} to split.
     */
    private List<? extends List<SchedulePeriod>> split(
                LocalDateTime startTime, List<? extends SchedulePeriod> timeables) {
        ArrayList<ArrayList<SchedulePeriod>> sepTimeables = new ArrayList<>(DAYS_IN_WEEK);
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            sepTimeables.add(new ArrayList<>());
        }
        for (SchedulePeriod timeable : timeables) {
            // timeables might span multiple days.
            // thus need to determine start and end days.
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


    /**
     * Creates and adds a {@code DaySchedule}.
     *
     * @param startTime - the time of the start of the day to create.
     * @param timeables - the list {@code ScheduleTimeable} to include in the
     *      day.
     * @param index - the index of the day.
     */
    private void addRow(LocalDateTime startTime, List<SchedulePeriod> timeables, int index) {
        HBox box = createDayBox(index);

        Label dateLabel = createDateLabel(startTime);

        // wrap in VBox to center component
        VBox scheduleContainer = new VBox(new DaySchedule(startTime, timeables, ROW_WIDTH));
        scheduleContainer.setAlignment(Pos.CENTER_LEFT);

        box.getChildren().addAll(dateLabel, scheduleContainer);
        getChildren().add(box);
    }


    private HBox createDayBox(int index) {
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_LEFT);
        box.setMaxWidth(ROW_WIDTH);
        box.getStyleClass().add("schedule-date-box");
        if (index % 2 == 0) {
            box.pseudoClassStateChanged(PseudoClass.getPseudoClass("even"), true);
        } else {
            box.pseudoClassStateChanged(PseudoClass.getPseudoClass("odd"), true);
        }
        return box;
    }


    private Label createDateLabel(LocalDateTime startTime) {
        Label dateLabel = new Label();
        dateLabel.setMinWidth(LABEL_WIDTH);
        dateLabel.setMaxWidth(LABEL_WIDTH);
        dateLabel.setPrefWidth(LABEL_WIDTH);
        dateLabel.setTextAlignment(TextAlignment.LEFT);
        dateLabel.setAlignment(Pos.CENTER_LEFT);
        dateLabel.setText(startTime.format(DateTimeFormatter.ofPattern("LLL d (EEE)")));
        return dateLabel;
    }
}
