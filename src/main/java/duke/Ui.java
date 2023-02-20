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
        stringBuilder.append("HEY THERE YOU. I'm Duuk. Duke's evil twin brother");
        return stringBuilder.toString();
    }

    /**
     * Displays the error message to the user.
     * @param message the error message to be displayed.
     */
    public String displayErrorMessage(String message) {
        return ("You are a PAIN, there is an error:" + message);
    }

    /**
     * Returns a string representation of the reply for a successfully deleted task.
     * @param deletedTask The deleted task.
     * @param listSize The current size of the task list.
     * @return A string representation of the reply.
     */
    public String getDeleteReply(Task deletedTask, int listSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FINE. I've removed this task:\n").append(deletedTask.toString()).append("\n");
        stringBuilder.append("Boy you are not bad yourself...you have " + listSize + " tasks in the list.");
        return stringBuilder.toString();
    }

    /**
     * Returns a string representation of the reply for a successfully added task.
     * @param addedTask The added task.
     * @return A string representation of the reply.
     */
    public String getAddReply(Task addedTask, int listSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WHAT A BOTHER! Whatever...I've added this task:\n " + addedTask.toString());
        stringBuilder.append("Boy now you have " + listSize + " tasks in the list.");
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
            stringBuilder.append("Oh boy you don't have any matching tasks.\n");
            return stringBuilder.toString();
        }

        stringBuilder.append("I dug out the WHOLE BEDROOM and found want you wanted NOW LEAVE:\n");
        stringBuilder.append(tasklist);
        return stringBuilder.toString();
    }

    /**
     * Returns a string representation of the reply for the full list of tasks.
     * @param tasklist The full list of tasks.
     * @return A string representation of the reply.
     */
    public String getListReply(Tasklist tasklist) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AGAIN? TAKE THIS LIST AND LEAVE!\n ").append(tasklist.toString());
        return stringBuilder.toString();
    }

    /**
     * Returns the reply to be displayed after marking a task as done.
     * @param markedTask The task that was marked as done.
     * @return The reply to be displayed.
     */
    public String getMarkReply(Task markedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MARK MY WORDS...and this task:\n").append(markedTask.toString());
        return stringBuilder.toString();
    }

    /**
     * Returns the reply to be displayed after unmarking a task as not done.
     * @param unMarkedTask The task that was unmarked as done.
     * @return The reply to be displayed.
     */
    public String getUnMarkReply(Task unMarkedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Make up your mind! I will unmark just this once\n").append(unMarkedTask.toString());
        return stringBuilder.toString();
    }

    /**
     * Returns the reply to be displayed before exiting the program.
     * @return The reply to be displayed.
     */
    public String getExitReply() {
        return "Hope I never see your face here again!";
    }

    /**
     * Returns a string that includes a tag reply and the string representation of a Task object after execution of tag.
     * @param taggedTask The Task object that has been tagged.
     * @return A string that includes a message and the string representation of the tagged task.
     */
    public String getTagReply(Task taggedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You want me to what? Tag? Fine.\n").append(taggedTask.toString());
        return stringBuilder.toString();
    }

    /**
     * Closes the window if the response matches user interface's exit reply.
     * @param response The response to check.
     */
    public void closeWindow(String response) {
        if (response.equals("Hope I never see your face here again!")) {
            Timeline delayExit = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                Platform.exit();
            }));
            delayExit.play();
        }
    }
}
