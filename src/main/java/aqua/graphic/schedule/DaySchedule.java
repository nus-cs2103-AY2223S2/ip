package aqua.graphic.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import aqua.util.DateUtils;
import aqua.util.Timeable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


public class DaySchedule extends HBox {
    private static final double LABEL_WIDTH = 80;
    private static final double ROW_WIDTH = 600;
    private static final double ROWHEIGHT = 15;
    private static final double MINUTES_IN_A_DAY = 1440;

    private final VBox rowDisplayArea = new VBox();


    public DaySchedule(LocalDateTime startTime, List<Timeable> timeables) {
        initialiseDay(startTime);
        List<? extends List<Timeable>> sepTimeable = separateConflicting(timeables);
        for (List<Timeable> row : sepTimeable) {
            addRow(startTime, row);
        }
    }


    private final void initialiseDay(LocalDateTime startTime) {
        Label label = initialiseLabel(startTime);
        getChildren().addAll(label, rowDisplayArea);
    }


    private Label initialiseLabel(LocalDateTime startTime) {
        Label dateLabel = new Label();
        dateLabel.setMinWidth(LABEL_WIDTH);
        dateLabel.setPrefWidth(LABEL_WIDTH);
        dateLabel.setMaxWidth(LABEL_WIDTH);
        dateLabel.setAlignment(Pos.CENTER);
        dateLabel.setText(startTime.format(DateTimeFormatter.ofPattern("LLL d (EEE)")));
        return dateLabel;
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
        pane.setMinWidth(ROW_WIDTH);
        pane.setMaxHeight(ROWHEIGHT);
        for (Timeable timeable : timeables) {
            double startX = (startTime.until(timeable.getStart(), ChronoUnit.MINUTES) / MINUTES_IN_A_DAY)
                    * ROW_WIDTH;
            startX = Math.max(0, startX);
            double endX = (startTime.until(timeable.getEnd(), ChronoUnit.MINUTES) / MINUTES_IN_A_DAY)
                    * ROW_WIDTH;
            endX = Math.min(ROW_WIDTH, endX);
            Rectangle rectangle = new Rectangle(endX - startX, ROWHEIGHT);
            pane.getChildren().add(rectangle);
            rectangle.setLayoutX(startX);
        }
        rowDisplayArea.getChildren().add(pane);
    }
}
