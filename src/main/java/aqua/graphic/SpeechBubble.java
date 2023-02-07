package aqua.graphic;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


/** A speech bubble to display messages. */
public class SpeechBubble extends UiComponent<Region> {
    private static final String PATH_FXML_LEFT_SIDE = "SpeechBubbleLeft.fxml";
    private static final String PATH_FXML_RIGHT_SIDE = "SpeechBubbleRight.fxml";

    private final Region root;

    @FXML private ImageView iconView;
    @FXML private VBox bubbleDisplayArea;


    /**
     * Constructs a {@code SpeechBubble} from the specified parameters.
     *
     * @param isUser - {@code true} if the bubble is for the user and
     *      {@code false} otherwise.
     */
    public SpeechBubble(boolean isUser) {
        super(getPath(isUser));
        this.root = initialiseRoot(isUser);
        iconView.setClip(new Circle(
                iconView.getFitWidth() / 2,
                iconView.getFitHeight() / 2,
                iconView.getFitHeight() / 2));
    }


    private static String getPath(boolean isUser) {
        return (isUser) ? PATH_FXML_RIGHT_SIDE : PATH_FXML_LEFT_SIDE;
    }


    private Region initialiseRoot(boolean isUser) {
        Region bubble = super.getRoot();
        HBox box = new HBox(bubble);
        box.setAlignment((isUser) ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        return box;
    }


    /**
     * Sets the text being displayed on the speech bubble.
     *
     * @param text - the text to display.
     */
    public void setText(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setMaxWidth(bubbleDisplayArea.getMaxWidth());
        bubbleDisplayArea.getChildren().setAll(label);
    }


    @Override
    public Region getRoot() {
        return root;
    }
}
