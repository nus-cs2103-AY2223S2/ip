package elise;

/**
 * User interface class to interact with the user.
 */
public class Ui {
    static final String BORDER = "----------------------------------------";

    /**
     * Prints welcome message.
     */
    protected void showWelcome() {
        showLine();
        String logo = "           " + "█▀▀ █░░ █ █▀ █▀▀\n"
                + "Hello from" + " ██▄ █▄▄ █ ▄█ ██▄";
        System.out.println(logo);
        showLine();
        System.out.println("I am a personal Chatbot who keep track of various things!\nEnter help to view commands!");
        showLine();
    }

    /**
     * Prints border.
     */
    protected void showLine() {
        System.out.println(BORDER);
    }

    /**
     * Prints error message.
     *
     * @param e EliseException.
     */
    protected void showError(EliseException e) {
        showLine();
        System.out.println(e.getMessage());
        showLine();
    }

    /**
     * Prints exit message.
     */
    protected void byeMessage() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints mark done message of task.
     *
     * @param t Task marked done.
     */
    protected void markDoneMessage(Task t) {
        showLine();
        System.out.println("Nice! I've marked this task as done:\n"
                + t.fullMessage());
        showLine();
    }

    /**
     * Prints mark undone message of task.
     *
     * @param t Task marked undone.
     */
    protected void markUndoneMessage(Task t) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:\n"
                + t.fullMessage());
        showLine();
    }

    /**
     * Prints number of elements in the task.
     *
     * @param taskList Task manager.
     */
    private void sizeMessage(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " tasks in this list");
    }

    /**
     * Prints add message of Task.
     *
     * @param t Task added.
     * @param taskList Task manager.
     */
    protected void addMessage(Task t, TaskList taskList) {
        showLine();
        System.out.println("Got it. I've added this task:\n" + t.fullMessage());
        sizeMessage(taskList);
        showLine();
    }

    /**
     * Prints delete message of Task.
     *
     * @param t Task deleted.
     * @param taskList Task manager.
     */
    protected void deleteMessage(Task t, TaskList taskList) {
        showLine();
        System.out.println("Noted. I've removed this task:\n" + t.fullMessage());
        sizeMessage(taskList);
        showLine();
    }

    /**
     * Prints help message.
     */
    protected void showHelp() {
        // Might be shifted all into a file.
        showLine();
        System.out.println("Available commands: \n");
        System.out.println("todo [M] - Adds todo task, replace [M] with message.");
        System.out.println("deadline [M] /by [D] - "
                + "Adds deadline task, replace [M] with message and [D] with date/time.");
        System.out.println("event [M] /from [D] /to [D] - "
                + "Adds event task, replace [M] with message and [D] with date/time.\n");
        System.out.println("Intended date/time should be in format dd-MM-yyyy HHmm or dd-MM-yyyy.");
        System.out.println("Otherwise, the date/time will be treated as plain text.\n");
        System.out.println("list - Lists all tasks.");
        System.out.println("mark [R] - Marks task as done, replace [R] with rank of task.");
        System.out.println("unmark [R] - Marks task as not done, replace [R] with rank of task.");
        System.out.println("find [K] - Lists all matching task, replace [K] with keyword.");
        System.out.println("bye - Exits the chat bot.");
        showLine();
    }
}
