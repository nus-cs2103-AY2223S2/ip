package gui;

import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Helps to create dialogue box from given text.
 */
public class DialogueBoxHelper {
    private static final Image userImg = new Image(
            Objects.requireNonNull(
                    DialogueBoxHelper.class.getResourceAsStream("/images/DaUser.png")));
    private static final Image tachImg = new Image(
            Objects.requireNonNull(
                    DialogueBoxHelper.class.getResourceAsStream("/images/DaTach.png")));

    /**
     * Creates a DialogueBox.
     * @param prompt Text to display.
     * @return DialogueBox as desired.
     */
    public static DialogueBox ofUser(String prompt) {
        ImageView userDiv = new ImageView(userImg);
        userDiv.setFitWidth(100.0);
        userDiv.setFitHeight(100.0);
        return new DialogueBox(LabelHelper.from(prompt), userDiv, true);
    }

    /**
     * Creates a DialogueBox.
     * @param prompt Text to display.
     * @return DialogueBox as desired.
     */
    public static DialogueBox ofTach(String prompt) {
        ImageView tachDIv = new ImageView(tachImg);
        tachDIv.setFitWidth(100.0);
        tachDIv.setFitHeight(100.0);
        return new DialogueBox(LabelHelper.from(prompt), tachDIv, false);
    }
}
