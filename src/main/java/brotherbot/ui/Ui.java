package brotherbot.ui;

import brotherbot.DialogBox;
import brotherbot.exceptions.BroException;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Ui {

    private VBox dialogContainer;

    private final Image brother = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/brother.png")));


    /**
     * Constructor for Ui object.
     */
    public Ui() {
         dialogContainer = new VBox();
    }

    /**
     * Displays output string to user.
     *
     * @param output Output to be displayed to user.
     */
    public void toUser(String output) {
        Label broText = new Label(output);
        dialogContainer.getChildren().addAll(
                DialogBox.getBrotherDialog(broText, new ImageView(brother))
        );
    }

    /**
     * Displays welcome message to user.
     */
    public void showWelcome() {
        Label broText = new Label("Hello Brother\nWelcome to Brother Bot\nWhats up what can I do for you mi amigo");
        dialogContainer.getChildren().addAll(
                DialogBox.getBrotherDialog(broText, new ImageView(brother))
        );

    }

    /**
     * Displays BroException error message to user.
     *
     * @param x BroException to be conveyed to user.
     */
    public void showError(BroException x) {
        Label broText = new Label(x.getMessage());
        dialogContainer.getChildren().addAll(
                DialogBox.getBrotherDialog(broText, new ImageView(brother))
        );
    }

}
