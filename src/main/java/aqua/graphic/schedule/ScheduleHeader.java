package aqua.graphic.schedule;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

class ScheduleHeader extends AnchorPane {
    private static final double HOURS_IN_A_DAY = 24;
    private static final double HEADER_HEIGHT = 20;
    private static final double LABEL_WIDTH = 30;


    ScheduleHeader(double labelWidth, double rowWidth) {
        setMinSize(labelWidth + rowWidth, HEADER_HEIGHT);

        for (int i = 0; i < HOURS_IN_A_DAY; i++) {
            Label label = new Label(String.format("%02d00", i));
            label.setMinWidth(LABEL_WIDTH);
            label.setAlignment(Pos.CENTER);
            getChildren().add(label);

            double leftAnchor = labelWidth;
            leftAnchor += ((rowWidth / HOURS_IN_A_DAY) * i);
            leftAnchor -= LABEL_WIDTH / 2D;

            setLeftAnchor(label, leftAnchor);
            setBottomAnchor(label, 0D);
        }
    }
}
