package ui;

import java.util.Scanner;

/**
 * API of UI component.
 */
public class Ui {
    public static final String LS = System.lineSeparator();
    public static final String DIVIDER = "------------------------------------------------------------";
    public static final String WARNING = "************************************************************";
    public static final String DATE_TIME_FORMAT = "DD-MM-YYYY Time";
    private Scanner allCommands = new Scanner(System.in);

    /**
     * Method to display text with either DIVIDER or WARNING strings.
     */
    public String formatString(String input, String type) {
        return type + LS + input + LS + type;
    }

    /**
     * The starting message when application is run.
     */
    public void showWelcome() {
        String intro = "Hello! I'm Gerty, a cool Task Manager :)" + LS + "What can I do for you?";
        String usages = "Here are a list of my commands:" + LS
                + "todo <task>" + LS
                + "deadline <task> /by <" + DATE_TIME_FORMAT + ">" + LS
                + "event <task> /from <" + DATE_TIME_FORMAT + "> /to <" + DATE_TIME_FORMAT + ">";

        System.out.println(this.formatString(intro + LS + LS + usages, DIVIDER));
    }

    /**
     * Reads list of commands sequentially line by line.
     * @return Next line of command to be parsed.
     */
    public String readCommand() {
        return this.allCommands.nextLine();
    }

    /**
     * Prints DIVIDER string to output device.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays error if any when file is loaded to create Duke object.
     */
    public void showLoadingError() {
        String failToLoad = "I failed to load your tasks from my storage :(" + LS
                + "Let's record your tasks from scratch!";
        System.out.println(this.formatString(failToLoad, WARNING));
    }

    /**
     * Displays stylized error message.
     * @param err Error message from exception.
     */
    public void showError(String err) {
        System.out.println(this.formatString(err, WARNING));
    }

    /**
     * Prints to output device.
     * @param message Message to be printed.
     */
    public void display(String message) {
        System.out.println(message);
    }
}
