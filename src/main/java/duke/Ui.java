package duke;

import duke.task.Task;

/**
 * User Interface class that manages messages being shown to the user.
 */
public class Ui {

    private static String input;
    private static String response;

    private static boolean dukeClosed = false;
    /**
     * Constructor that creates an instance of Ui.
     */
    public Ui() {
    }

    /**
     * Returns Duke's latest response to the GUI.
     *
     * @return Duke's response to GUI.
     */
    public static String dukeResponse() {
        return response;
    }

    /**
     * Returns user most recent input for Duke.
     *
     * @return User most recent input.
     */
    public static String getInput() {
        return input;
    }

    /**
     * Sets user most recent input from GUI.
     *
     * @param input User most recent input.
     */
    public static void receiveInput(String input) {
        assert !input.isEmpty();
        Ui.input = input;
    }

    /**
     * Prints priority of a task.
     *
     * @param task Task to be printed.
     */
    public static void getPriorityMessage(Task task) {
        response = "";
        response += "Priority of this task is: " + task.getPriority();
    }

    /**
     * Prints new priority of a task.
     *
     * @param task Task to be printed.
     */
    public static void setPriorityMessage(Task task) {
        response = "";
        response += "Priority of this task has been set to: " + task.getPriority();
    }

    /**
     * Prints the given TaskList.
     *
     * @param tasks The TaskList to be printed.
     */
    public static void listMessage(TaskList tasks) {
        response = "";
        response += "Working on it Sigma. Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            response += j + "." + tasks.get(i).toString() + "\n";
        }
    }

    /**
     * Prints the TaskList containing the queried term in a find command.
     *
     * @param searchFor The queried term.
     * @param tasks The TaskList to be printed.
     */
    public static void findMessage(String searchFor, TaskList tasks) {
        response = "";
        response += "Got it Sigma. Here are the tasks in your list that contain [" + searchFor + "]:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            response += j + "." + tasks.get(i).toString() +"\n";
        }
    }

    /**
     * Prints the marked task.
     *
     * @param task The task marked as done.
     */
    public static void markMessage(Task task) {
        response = "";
        response += "Nice work Sigma. I'm marking this task as done:\n" + task +"\n";
    }

    /**
     * Prints the unmarked task.
     *
     * @param task The task marked as not done.
     */
    public static void unMarkMessage(Task task) {
        response = "";
        response += "Sigma, I am marking this as not done:\n" + task +"\n";
    }

    /**
     * Prints the deleted task.
     *
     * @param task The task deleted.
     */
    public static void deleteMessage(Task task) {
        response = "";
        response += "Sigma move! One less task to go! Removing...\n" + task +"\n";
    }

    /**
     * Prints the added task.
     *
     * @param task The task added to the TaskList.
     */
    public static void addMessage(Task task, int taskListSize) {
        response = "";
        response += "Let's go Sigma. This task has been added:\n" + "  " + task +"\n";
        response += "Now you have " + taskListSize + " tasks in your list.";
    }

    /**
     * Welcome message during startup.
     */
    public static void welcomeMessage() {
        response = "Greetings fellow Sigma.\n" + "I am GigaChad. What do you wish to conquer today?\n";
    }

    /**
     * Farewell message on bye command.
     */
    public static void farewellMessage() {
        response = "";
        response += "Writing list to storage. Hope to Sigma you again! \n";
        response += "GigaChad is leaving the chat.....";
        dukeClosed = true;
    }

    public static boolean isDukeClosed() {
        return dukeClosed;
    }

    /**
     * Prints the given Exception's error message.
     *
     * @param e The given Exception.
     */
    public static void errorMessage(Exception e) {
        response = "";
        response += e;
    }
}
