package sam.ui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Handles user interaction.
 */
public class Ui {

    protected Image userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
    protected Image samImage = new Image(getClass().getResourceAsStream("/images/sam.png"));

    private MainWindow mainWindow;


    /**
     * Sets up the application's stage.
     *
     * @param stage The stage to set up.
     */
    public void setStage(Stage stage) {
        mainWindow = new MainWindow(stage, this);
        respond(Dialog.GREETING.getDialog());
    }

    /**
     * Adds a dialogue from Sam formed by the given strings.
     *
     * @param messages A list of strings representing lines of dialogue.
     */
    public void respond(String... messages) {
        Label[] labels = new Label[messages.length];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new Label(messages[i]);
        }
        respond(labels);
    }

    /**
     * Adds a dialogue from Sam formed by the given nodes.
     *
     * @param nodes A list of nodes.
     */
    public void respond(Node... nodes) {
        mainWindow.addSamDialog(nodes);
    }

    /**
     * Disables the input field and send button
     */
    public void disable() {
        mainWindow.disable();
    }
}
