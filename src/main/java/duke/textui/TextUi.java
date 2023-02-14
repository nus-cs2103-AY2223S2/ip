package duke.textui;

import java.util.Scanner;

/**
 * UI that deals with the display of data for the user once the chatbot is started up.
 */
public class TextUi {

    /**
     * Divider line.
     */
    private static final String LINE = "__________________________________________";
    /**
     * A scanner that scans for user input.
     */
    private final Scanner scanner;

    /**
     * Constructor for a new UI with a scanner.
     */
    public TextUi() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the chatbot is started.
     *
     * @return The string being displayed
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String introMsg = "Hello! I'm Duke.\nWhat can I do for you today?";

        String output = concatMsgs(LINE, logo, introMsg, LINE);
        return showMsg(output);
    }

    /**
     * Display the message provided.
     *
     * @param msg The message to be displayed
     * @return The string being displayed
     */
    public String showMsg(String msg) {
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays the error/hint message.
     *
     * @param errorMsg The error/hint message
     * @return The string being displayed
     */
    public String showError(String errorMsg) {
        String output = concatMsgs(errorMsg, "Please try again!");
        return showMsg(output);
    }

    /**
     * Displays the string representation of the task being added, together with the new updated number of tasks in
     * the task list.
     *
     * @param taskString The string representation of the task being added
     * @param size       The number of tasks in the list of tasks
     * @return The string being displayed
     */
    public String showAddTask(String taskString, int size) {
        String msgHeader = "I've added this task into the list:";
        String msgFooter = String.format("Now you have a total of %s tasks in the list", size);

        String output = concatMsgs(msgHeader, taskString, msgFooter);
        return showMsg(output);
    }

    /**
     * Displays the string representation of the task being deleted, together with the new updated number of tasks in
     * the task list.
     *
     * @param taskString The string representation of the task being deleted
     * @param size       The number of tasks in the list of tasks
     * @return The string being displayed
     */
    public String showDeleteTask(String taskString, int size) {
        String msgHeader = "I've deleted this task into the list:";
        String msgFooter = String.format("Now you have a total of %s tasks in the list", size);

        String output = concatMsgs(msgHeader, taskString, msgFooter);
        return showMsg(output);
    }

    /**
     * Closes the scanner that reads user input.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Joins all the string messages into a single string separated by a new line.
     *
     * @param msgs All the messages to be joined together
     * @return A joined single string of all the messaged
     */
    public String concatMsgs(String... msgs) {
        return String.join("\n", msgs);
    }
}
