package lulu;

import java.lang.StringBuilder;

/**
 * This class mainly handles String output to be printed to the User.
 * It is representative of the chatbot, Lulu, communicating with the user.
 */
public class Ui {
    private static final String LINE = "______________________________________";

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Returns a String that can then be printed.
     *
     * @param content a variable number of string to be wrapped
     * @return the String wrapped around by lines
     */
    public String showContainer(String... content) {
        StringBuilder container = new StringBuilder();
        container.append(LINE);
        container.append('\n');
        for (String s : content) {
            container.append(s);
            container.append('\n');
        }
        container.append(LINE);
        return container.toString();
    }

    public void showGreetText() {
        System.out.println(LINE);
        System.out.println("Hello! I am lulu (=◕ᆽ◕ฺ=)");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Returns a String to be printed on the screen.
     * Primarily used by the ByeCommand class.
     *
     * @return a String that bids goodbye to the user
     */
    public String showExitText() {
        return ("Bye! Hope to see you again soon!");
    }

    /**
     * Returns a String to be printed on the screen.
     * Primarily used by any Command that adds a new task.
     *
     * @param taskDescription the description of the task to be added
     * @param size            the total number of tasks in the list, after addition
     * @return a String to tell the user the task added, and the total number of tasks in the list
     */
    public String showAddText(String taskDescription, int size) {
        StringBuilder text = new StringBuilder();
        text.append("Got it. I've added this task:");
        text.append('\n');
        text.append("  " + taskDescription);
        text.append('\n');
        text.append("Now you have " + size + " tasks in the list.");
        return text.toString();
    }

    /**
     * Returns a String to be printed on the screen.
     * Primarily used by the DeleteCommand class.
     *
     * @param taskDescription the description of the task to be deleted
     * @param size            the total number of tasks in the list, after deletion
     * @return a String to tell the user the task that was deleted, and the total number of tasks in the list
     */
    public String showDeleteText(String taskDescription, int size) {
        StringBuilder text = new StringBuilder();
        text.append("Noted! I've removed this task:");
        text.append('\n');
        text.append("  " + taskDescription);
        text.append('\n');
        text.append("Now you have " + size + " tasks in the list.");
        return text.toString();
    }

    /**
     * Returns a String to be printed on the screen.
     * Primarily used by the ListCommand class.
     * Should be used with the printList() method from TaskList.
     *
     * @return a String that comes before the printList() method from TaskList.
     */
    public String listText() {
        return (" Here are the tasks in your list: ");
    }

    /**
     * Returns a String to be printed on the screen.
     * Primarily used by the FindCommand class.
     * Should be used with the printList() method from TaskList.
     *
     * @return a String that comes before the printList() method from TaskList.
     */
    public String listMatchText() {
        return ("Here are the matching tasks in your list: ");
    }

    /**
     * Returns a String to be printed on the screen.
     * Primarily used by the MarkCommand class.
     *
     * @param taskMark the description of the task to be marked
     * @return a String that tells the user the task that was marked
     */
    public String showMarkText(String taskMark) {
        StringBuilder text = new StringBuilder();
        text.append("Nice! I've marked this task as done:");
        text.append('\n');
        text.append(" " + taskMark);
        return text.toString();
    }

    /**
     * Returns a String to be printed on the screen.
     * Primarily used by the UnmarkCommand class.
     *
     * @param taskUnmark the description of the task to be unmarked
     * @return a String that tells the user the task that was unmarked
     */
    public String showUnmarkText(String taskUnmark) {
        StringBuilder text = new StringBuilder();
        text.append("OK, I've marked this task as not done yet:");
        text.append('\n');
        text.append(" " + taskUnmark);
        return text.toString();
    }

    /**
     * Returns a String to be printed on the screen.
     *
     * @return a String tha tells the user that data from save file has been loaded
     */
    public String showLoadComplete() {
        return ("loading complete.");
    }

    /**
     * Returns a String to be printed on the screen.
     * Primarily used for exceptions.
     *
     * @return a String that tells the user the input command was invalid
     */
    public String showOutOfBounds() {
        return ("(=ಠᆽಠ=) That is out of bounds!");
    }
}
