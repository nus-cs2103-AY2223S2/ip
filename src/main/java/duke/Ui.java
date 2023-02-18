package duke;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import task.Task;
import task.Tasklist;

/**
 * This class handles all the user interface interactions.
 * It provides methods to show a welcome message, read user input,
 * show a separator line, close the Scanner, and display error messages.
 */
public class Ui {
    /**
     * Constructor that initializes the Scanner to read input from the standard input.
     */
    public Ui() {
    }

    /**
     * Show the welcome message to the user.
     * @return The welcome message to the user.
     */
    public String getWelcomeReply() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("What's up! I'm duuk.What do you want?");
        return stringBuilder.toString();
    }

    /**
     * Displays the error message to the user.
     * @param message the error message to be displayed.
     */
    public String displayErrorMessage(String message) {
        return ("Oh no there is an error:" + message);
    }

    /**
     * Returns a string representation of the reply for a successfully deleted task.
     * @param deletedTask The deleted task.
     * @param listSize The current size of the task list.
     * @return A string representation of the reply.
     */
    public String getDeleteReply(Task deletedTask, int listSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Noted. I've removed this task:\n").append(deletedTask.toString()).append("\n");
        stringBuilder.append("Now you have " + listSize + " tasks in the list.");
        return stringBuilder.toString();
    }

    /**
     * Returns a string representation of the reply for a successfully added task.
     * @param addedTask The added task.
     * @return A string representation of the reply.
     */
    public String getAddReply(Task addedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task:\n " + addedTask.toString());
        return stringBuilder.toString();
    }

    /**
     * Returns a string representation of the reply for a list of tasks matching a description.
     * @param tasklist The list of tasks matching the description.
     * @return A string representation of the reply.
     */
    public String getMatchedListReply(Tasklist tasklist) {
        StringBuilder stringBuilder = new StringBuilder();
        if (tasklist.isEmpty()) {
            stringBuilder.append("Oh no you don't have any matching tasks.\n");
            return stringBuilder.toString();
        }

        stringBuilder.append("Here are the tasks that matches yor description:\n");
        String[] taskLines = tasklist.toString().split("\n");

        for (int i = 1; i <= taskLines.length; i++) {
            stringBuilder.append(i).append(". ").append(taskLines[i]).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns a string representation of the reply for the full list of tasks.
     * @param tasklist The full list of tasks.
     * @return A string representation of the reply.
     */
    public String getListReply(Tasklist tasklist) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are your tasks boy!\n ").append(tasklist.toString());
        return stringBuilder.toString();
    }

    /**
     * Returns the reply to be displayed after marking a task as done.
     * @param markedTask The task that was marked as done.
     * @return The reply to be displayed.
     */
    public String getMarkReply(Task markedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nice! I've marked this task as done:\n").append(markedTask.toString());
        return stringBuilder.toString();
    }

    /**
     * Returns the reply to be displayed after unmarking a task as not done.
     * @param unMarkedTask The task that was unmarked as done.
     * @return The reply to be displayed.
     */
    public String getUnMarkReply(Task unMarkedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nice! I've unmarked this task:\n").append(unMarkedTask.toString());
        return stringBuilder.toString();
    }

    /**
     * Returns the reply to be displayed before exiting the program.
     * @return The reply to be displayed.
     */
    public String getExitReply() {
        return "Bye. Hope to see you again soon!";
    }

    public String getTagReply(Task taggedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nice. I've tagged this task:\n").append(taggedTask.toString());
        return stringBuilder.toString();
    }

    public void closeWindow(String response) {
        if (response.equals("Bye. Hope to see you again soon!")) {
            Timeline delayExit = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                Platform.exit();
            }));
            delayExit.play();
        }
    }
}
