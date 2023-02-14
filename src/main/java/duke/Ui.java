package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;

/**
 * The class that handling the UI of Duke.
 */
public class Ui {
    /** The indentation of the printed output. */
    private static final int INDENT_LEVEL = 4;
    /** The scanner that is used to read the command-line inputs. */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the error in the console UI.
     *
     * @param e The error to show.
     */
    public void showError(DukeException e) {
        this.show(e.getDukeMessage());
    }

    /**
     * Formats and display a string into the console UI.
     *
     * @param whatToShow What to show.
     */
    public void show(String whatToShow) {
        String indentation = " ".repeat(Ui.INDENT_LEVEL);
        String horizontalLine = "_".repeat(60);
        String indentedInput = whatToShow.replaceAll("(?<=^|\n)", indentation);

        System.out.println(indentation + horizontalLine);
        System.out.println(indentedInput);
        System.out.println(indentation + horizontalLine + '\n');
    }

    /**
     * Checks whether there's a user command waiting to be parsed.
     *
     * @return Whether there's a user command.
     */
    public boolean hasCommand() {
        return this.scanner.hasNextLine();
    }

    /**
     * Gets the next user command.
     *
     * @return The next user command.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Stops the scanning of the console for user commands. After this method is
     * called, 'hasCommand' and 'readCommand' will stop working.
     */
    public void close() {
        this.scanner.close();
    }
}
