package duke;

import task.Task;
import task.Tasklist;

/**

 This class handles all the user interface interactions.
 It provides methods to show a welcome message, read user input, show a separator line, close the Scanner, and display error messages.
 */
public class Ui {
    /**
     Constructor that initializes the Scanner to read input from the standard input.
     */
    public Ui() {
    }

    /**
     Show the welcome message to the user.
     */
    public String getWelcomeReply() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("What's up! I'm duuk.What do you want?");
        return stringBuilder.toString();
    }

    /**
     Reads a line of input from the user.
     @return the input string read from the user.
     */


    /**
     Shows a separator line.
     */
    public String showLine() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("________________________________________");
        return stringBuilder.toString();
    }


    /**
     Displays the error message to the user.
     @param message the error message to be displayed.
     */
    public String displayErrorMessage(String message) {
        return ("Oh no there is an error:" + message);
    }


    public String getDeleteReply(Task deletedTask, int listSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Noted. I've removed this task:\n").append(deletedTask.toString()).append("\n");
        stringBuilder.append("Now you have " + listSize + " tasks in the list.");
        return stringBuilder.toString();
    }

    public String getAddReply(Task addedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task:\n " + addedTask.toString());
        return stringBuilder.toString();
    }

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

    public String getListReply(Tasklist tasklist) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are your tasks boy!\n ").append(tasklist.toString());
        return stringBuilder.toString();
    }

    public String getMarkReply(Task markedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nice! I've marked this task as done:\n").append(markedTask.toString());
        return stringBuilder.toString();
    }

    public String getUnMarkReply(Task unMarkedTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nice! I've unmarked this task:\n").append(unMarkedTask.toString());
        return stringBuilder.toString();
    }

    public String getExitReply() {
        return "Bye. Hope to see you again soon!";
    }
}
