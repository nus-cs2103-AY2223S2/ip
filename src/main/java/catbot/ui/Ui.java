package catbot.ui;

import java.util.Scanner;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Handles all UI features.
 */
public class Ui {
    private static final int MAX_LINE_LENGTH = 120;
    private Text nextOutput;
    private final Scanner inputScanner;

    /**
     * Initialises a Ui instance.
     */
    public Ui() {
        nextOutput = null;
        inputScanner = new Scanner(System.in);
    }

    /**
     * Sets the next line buffer for {@code showNext}.
     * @param message is the message to show.
     */
    public void setNextOutput(String message) {
        nextOutput = new Text(message);
        nextOutput.setFont(Font.getDefault());
        nextOutput.setStyle("-fx-text-fill: snow");
    }

    /**
     * Returns a Text object containing errors to be shown to the user.
     * @param message is the error message.
     */
    public Text getError(String message) {
        Text error = new Text(message);
        error.setFont(Font.getDefault());
        error.setStyle("-fx-text-fill: firebrick;");
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
