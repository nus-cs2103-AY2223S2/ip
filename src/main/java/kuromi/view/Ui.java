package kuromi.view;

import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Deals with interactions with the user (e.g. reading inputs, showing outputs).
 */
public class Ui {
    private String lines = "____________________________________________________________";

    private Stage stage;

    public Ui() {
        this.stage = null;
    }

    public Ui(Stage stage) {
        this.stage = stage;
    }

    /**
     * Read user's inputs.
     * @return User input as a string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Show error if cannot load the file that is in the hard disk.
     */
    public String showLoadingError() {
        return ("Cannot load file. :(\n");
    }

    /**
     * Show welcome message.
     */
    public static String showWelcomeMessage() {
        String msg = "Hello! I'm Kuromi\nWhat can I do for you?\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "Type 'help' to see the list of commands available.\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\nNote:\n";
        msg += "I know you're Melody -_-";
        return msg;
    }

    /**
     * Show a message to the user.
     * @param msg Message to be shown.
     */
    public String show(String msg) {
        return (msg);
    }

    /**
     * Show bye message to the user.
     * @param msg Message to be shown.
     * @return Message
     */
    public String showBye(String msg) {
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> stage.close());
        delay.play();
        return (msg);
    }
}
