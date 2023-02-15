package duke;

import duke.task.Task;

/**
 * User Interface class that manages messages being shown to the user.
 */
public class Ui {

    /**
     * Constructor that creates an instance of Ui.
     */
    public Ui() {
    }

    /**
     * Prints the given TaskList.
     *
     * @param tasks The TaskList to be printed.
     */
    public static void listMessage(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            System.out.println(j + "." + tasks.get(i).toString());
        }
    }

    /**
     * Prints the TaskList containing the queried term in a find command.
     *
     * @param searchFor The queried term.
     * @param tasks The TaskList to be printed.
     */
    public static void findMessage(String searchFor, TaskList tasks) {
        System.out.println("Here are the tasks in your list that contain [" + searchFor + "]:");
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            System.out.println(j + "." + tasks.get(i).toString());
        }
    }

    /**
     * Prints the marked task.
     *
     * @param task The task marked as done.
     */
    public static void markMessage(Task task) {
        System.out.println("Solid! I'm marking this task as done:\n" + task);
    }

    /**
     * Prints the unmarked task.
     *
     * @param task The task marked as not done.
     */
    public static void unmarkMessage(Task task) {
        System.out.println("Aight, marking this as not done:\n" + task);
    }

    /**
     * Prints the deleted task.
     *
     * @param task The task deleted.
     */
    public static void deleteMessage(Task task) {
        System.out.println("Swee! One less task to go! Removing...\n" + task);
    }

    /**
     * Prints the added task.
     *
     * @param task The task added to the TaskList.
     */
    public static void addMessage(Task task, int taskListSize) {
        System.out.println("Roger. This task has been added:\n" + "  " + task);
        System.out.println("Now you have " + taskListSize + " tasks in your list.");
    }

    /**
     * Welcome message during startup.
     */
    public static void welcomeMessage() {
        System.out.println("Greetings Sigma.\n" + "I am GigaChad. What do you wish to do?\n");
    }

    /**
     * Farewell message on bye command.
     */
    public static void farewellMessage() {
        System.out.println("Writing list to storage. Kay thanks bye!");
    }

    /**
     * Prints the given Exception's error message.
     *
     * @param e The given Exception.
     */
    public static void errorMessage(Exception e) {
        System.out.println(e);
    }
}
