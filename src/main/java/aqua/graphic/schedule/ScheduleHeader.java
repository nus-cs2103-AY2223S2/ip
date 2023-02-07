package aqua.graphic.schedule;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


/** The graphical display of the schedule's header. */
public class ScheduleHeader extends AnchorPane {
    private static final double HOURS_IN_A_DAY = 24;
    private static final double HEADER_HEIGHT = 20;
    private static final double LABEL_WIDTH = 30;


    /**
     * Constructs a {@code ScheduleHeader} from the given parameters.
     *
     * @param offsetWidth - the width offset in pixel before the start of the
     *      header.
     * @param width - the width of the header in pixels.
     */
    public ScheduleHeader(double offsetWidth, double width) {
        setMinSize(offsetWidth + width, HEADER_HEIGHT);

        for (int i = 0; i < HOURS_IN_A_DAY; i++) {
            Label label = new Label(String.format("%02d00", i));
            label.setMinWidth(LABEL_WIDTH);
            label.setAlignment(Pos.CENTER);
            getChildren().add(label);

            double leftAnchor = offsetWidth;
            leftAnchor += ((width / HOURS_IN_A_DAY) * i);
            leftAnchor -= LABEL_WIDTH / 2D;

            setLeftAnchor(label, leftAnchor);
            setBottomAnchor(label, 0D);
        }
    }
}
