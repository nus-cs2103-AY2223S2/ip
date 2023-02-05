package duke;

/**
 * The class responsible of the user interface for the program
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
    public void setMarkAsDone() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Prints out that the task is not done yet message
     */
    public void setUnMarkTask() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Prints out that the task is added to the list.
     */
    public void setAddedTask() {
        System.out.println("Got it, I've added this task:");
    }

}
