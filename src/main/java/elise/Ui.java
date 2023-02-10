package elise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * User interface class to interact with the user.
 */
public class Ui {
    private static final String BORDER = "-----------------------------------------------";
    private final String HELP_MESSAGE = new BufferedReader(
            new InputStreamReader(this.getClass().getResourceAsStream("/help.txt")))
            .lines().collect(Collectors.joining("\n"));

    /**
     * Prints welcome message.
     */
    protected String showWelcome() {
        String logo = "█▀▀ █░░ █ █▀ █▀▀\n"
                + "██▄ █▄▄ █ ▄█ ██▄\n";
        logo += "Hello! I am a personal Chat-bot who keep track of various things!\nEnter help to view commands!";
        return wrapText(logo);
    }

    public static String wrapText(String text) {
        return BORDER + "\n" + text + "\n" + BORDER;
    }

    /**
     * Prints error message.
     *
     * @param e EliseException.
     * @return Error message.
     */
    protected String showError(EliseException e) {
        return wrapText(e.getMessage());
    }

    /**
     * Prints mark done message of task.
     *
     * @param t Task marked done.
     */
    protected String markDoneMessage(Task t) {
        return wrapText("Nice! I've marked this task as done:\n"
                + t.fullMessage());
    }

    /**
     * Prints mark undone message of task.
     *
     * @param t Task marked undone.
     */
    protected String markUndoneMessage(Task t) {
        return wrapText("Nice! I've marked this task as not done yet:\n"
                + t.fullMessage());
    }

    /**
     * Prints number of elements in the task.
     *
     * @param taskList Task manager.
     */
    private String sizeMessage(TaskList taskList) {
        return "Now you have " + taskList.getSize() + " tasks in this list";
    }

    /**
     * Prints add message of Task.
     *
     * @param t Task added.
     * @param taskList Task manager.
     */
    protected String addMessage(Task t, TaskList taskList) {
        return wrapText("Got it. I've added this task:\n" + t.fullMessage() + "\n"
                + sizeMessage(taskList));
    }

    /**
     * Prints delete message of Task.
     *
     * @param t Task deleted.
     * @param taskList Task manager.
     */
    protected String deleteMessage(Task t, TaskList taskList) {
        return wrapText("Noted. I've removed this task:\n" + t.fullMessage() + "\n"
                + sizeMessage(taskList));
    }

    /**
     * Prints help message.
     */
    protected String showHelp() {
        return wrapText(HELP_MESSAGE);
    }
}
