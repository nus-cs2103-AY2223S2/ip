package aqua.graphic;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


public class SpeechBubble extends UiComponent<Region> {
    private static final String PATH_FXML_LEFT_SIDE = "SpeechBubbleLeft.fxml";
    private static final String PATH_FXML_RIGHT_SIDE = "SpeechBubbleRight.fxml";

    private final boolean isUser;

    @FXML private VBox bubbleDisplayArea;


    public SpeechBubble(boolean isUser) {
        super(getPath(isUser));
        this.isUser = isUser;
    }


    private static String getPath(boolean isUser) {
        return (isUser) ? PATH_FXML_RIGHT_SIDE : PATH_FXML_LEFT_SIDE;
    }


    public void setText(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setMaxWidth(bubbleDisplayArea.getMaxWidth());
        bubbleDisplayArea.getChildren().setAll(label);
    }


    @Override
    public Region getRoot() {
        Region bubble = super.getRoot();
        HBox box = new HBox(bubble);
        box.setAlignment((isUser) ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        return box;
    }
}
