package sam.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sam.command.Result;

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
        Result result = new Result();
        for (String message : messages) {
            result.addMessage(message);
        }
        respond(result);
    }

    public void respond(Result result) {
        VBox samDialog = result.getResult();
        mainWindow.addSamDialog(samDialog);
    }

    /**
     * Disables the input field and send button
     */
    public void disable() {
        mainWindow.disable();
    }
}
