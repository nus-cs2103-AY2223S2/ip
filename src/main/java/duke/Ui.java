package duke;

/**
 * The class responsible for the user interface for the program
 */
public class Ui {
    /**
     * Prints the welcome message of the program
     */
    public void welcomeMessage() {
        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints out that the task done message
     */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    public String setMarkAsDone() {
        return "Nice! I've marked this task as done:\n";
    }

    /**
     * Prints out that the task is not done yet message
     */
    public String setUnMarkTask() {
        return "OK, I've marked this task as not done yet:\n";
    }

    /**
     * Prints out that the task is added to the list.
     */
    public String setAddedTask() {
        return "Got it, I've added this task:\n";
    }

}
