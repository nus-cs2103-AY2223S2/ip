package duke;

/***
 * The class responsible of the user interface for the program
 */
public class Ui {
    private final String MARK_AS_DONE = "Nice! I've marked this task as done:";
    private final String UNMARK_TASK = "OK, I've marked this task as not done yet:";
    private final String ADDED_TASK = "Got it, I've added this task:";

    /***
     * Prints the welcome message of the program
     */
    public void welcomeMessage() {
        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");
    }

    /***
     * Prints out that the task done message
     */
    public void setMarkAsDone() {
        System.out.println(MARK_AS_DONE);
    }
    /***
     * Prints out that the task is not done yet message
     */
    public void setUnMarkTask() {
        System.out.println(UNMARK_TASK);
    }

    /***
     * Prints out that the task is added to the list.
     */
    public void setAddedTask() {
        System.out.println(ADDED_TASK);
    }

}
