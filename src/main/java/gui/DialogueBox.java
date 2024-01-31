package gui;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Helps to create dialogue box from given text.
 */
public class DialogueBox extends HBox {
    private static final Image userImg = new Image(
            Objects.requireNonNull(
                    DialogueBox.class.getResourceAsStream("/images/DaUser.png")));
    private static final Image tachImg = new Image(
            Objects.requireNonNull(
                    DialogueBox.class.getResourceAsStream("/images/DaTach.png")));
    @FXML
    private ImageView avatar;
    @FXML
    private Label textBox;

    private DialogueBox(String s, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        avatar.setImage(img);
        textBox.setText(s);
        textBox.setMinHeight(s.lines().count() * 40);
        RoundAvatarHelper.clip(avatar, 45);
        if (isUser) {
            textBox.setAlignment(Pos.CENTER_RIGHT);
            this.getChildren().addAll(textBox, avatar);
        } else {
            textBox.setAlignment(Pos.CENTER_LEFT);
            this.getChildren().addAll(avatar, textBox);
        }
    }

    /**
     * Creates a DialogueBox.
     * @param prompt Text to display.
     * @return DialogueBox as desired.
     */
    public static HBox ofUser(String prompt) {
        return new DialogueBox(prompt, userImg, true);
    }

    /**
     * Creates a DialogueBox.
     * @param prompt Text to display.
     * @return DialogueBox as desired.
     */
    public static HBox ofTach(String prompt) {
        return new DialogueBox(prompt, tachImg, false);
    }
}
