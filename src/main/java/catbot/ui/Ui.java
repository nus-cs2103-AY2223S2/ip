package catbot.ui;

import javafx.scene.text.Text;

/**
 * Handles all UI features.
 */
public class Ui {
    private Text nextOutput;

    /**
     * Initialises a Ui instance.
     */
    public Ui() {
        nextOutput = null;
    }

    /**
     * Sets the next line buffer for {@code showNext}.
     * @param message is the message to show.
     */
    public void setNextOutput(String message) {
        nextOutput = new Text(message);
        nextOutput.setStyle("-fx-text-fill: snow; -fx-font-family: Monospaced");
    }

    /**
     * Returns a Text object containing errors to be shown to the user.
     * @param message is the error message.
     */
    public Text getError(String message) {
        Text error = new Text(message);
        error.setStyle("-fx-text-fill: firebrick; -fx-font-family: Monospaced");
        return error;
    }

    /**
     * Returns CatBot's next response.
     * Requires {@code setNextOutput} to be called first.
     */
    public Text getNextOutput() {
        Text temp = nextOutput;
        nextOutput = null;
        return temp;
    }
}
