package elise.internal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import elise.EliseException;
import elise.tasks.Task;

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
    public String showWelcome() {
        String logo = "█▀▀ █░░ █ █▀ █▀▀\n"
                + "██▄ █▄▄ █ ▄█ ██▄\n";
        logo += "Hello! I am a personal Chat-bot who keep track of various things!\nEnter help to view commands!";
        return wrapText(logo);
    }

    public String wrapText(String text) {
        return BORDER + "\n" + text + "\n" + BORDER;
    }

    /**
     * Prints error message.
     *
     * @param e EliseException.
     * @return Error message.
     */
    public String showError(EliseException e) {
        return wrapText(e.getMessage());
    }

    /**
     * Prints mark done message of task.
     *
     * @param t Task marked done.
     * @return Mark done message.
     */
    public String markDoneMessage(Task t) {
        return wrapText("Nice! I've marked this task as done:\n"
                + t.fullMessage());
    }

    /**
     * Prints mark undone message of task.
     *
     * @param t Task marked undone.
     * @return Mark undone message
     */
    public String markUndoneMessage(Task t) {
        return wrapText("Nice! I've marked this task as not done yet:\n"
                + t.fullMessage());
    }

    /**
     * Prints number of elements in the task.
     *
     * @param taskList Task manager.
     * @return Size message.
     */
    private String sizeMessage(TaskList taskList) {
        return "Now you have " + taskList.getSize() + " tasks in this list";
    }

    /**
     * Prints add message of Task.
     *
     * @param t Task added.
     * @param taskList Task manager.
     * @return Add message.
     */
    public String addMessage(Task t, TaskList taskList) {
        return wrapText("Got it. I've added this task:\n" + t.fullMessage() + "\n"
                + sizeMessage(taskList));
    }

    /**
     * Prints delete message of Task.
     *
     * @param t Task deleted.
     * @param taskList Task manager.
     * @return Delete message.
     */
    public String deleteMessage(Task t, TaskList taskList) {
        return wrapText("Noted. I've removed this task:\n" + t.fullMessage() + "\n"
                + sizeMessage(taskList));
    }

    /**
     * Prints help message.
     *
     * @return Help message.
     */
    public String showHelp() {
        return wrapText(HELP_MESSAGE);
    }
}
