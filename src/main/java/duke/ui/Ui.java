package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * The component that interfaces with the users, handling user commands and printing the application response.
 */
public class Ui {
    private StringBuilder sb;

    public Ui() {
        sb = new StringBuilder();
    }

    /**
     * Prints a sequence of strings in a pretty way into buffer.
     *
     * @param chunks sequence of strings to be printed.
     */
    public void respond(String... chunks) {
        for (String lines : chunks) {
            for (String line: lines.split("\n")) {
                sb.append(line);
                sb.append("\n");
            }
        }
    }

    /**
     * Prints an exception into buffer
     *
     * @param e Exception to be printed.
     */
    public void error(Exception e) {
        sb.append("Oh No!\n\n");
        sb.append(e.getMessage());
        sb.append("\n");
    }

    /**
     * Prints a introduction message to the user.
     */
    public void introduce() {
        respond(
            "Hello I am Duke!",
            "What can I do for you?"
        );
    }

    /**
     * Prints a goodbye message and stops receiving user commands.
     */
    public void bye() {
        respond("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a list of tasks.
     *
     * @param tasks List of tasks to be printed.
     */
    public void listTasks(TaskList tasks) {
        respond(
            "Here are the tasks in your list:",
            tasks.toString()
        );
    }

    /**
     * Prints a list of tasks that have been filtered with a pattern.
     *
     * @param tasks List of tasks to be printed.
     */
    public void listMatchingTasks(TaskList tasks) {
        respond(
            "Here are the matching tasks in your list:",
            tasks.toString()
        );
    }

    /**
     * Prints the response after a task has been marked as completed.
     *
     * @param task The task that has been marked as completed.
     */
    public void markTask(Task task) {
        respond(
            "Nice! I've marked this task as done:",
            String.format("=> %s", task
        ));
    }

    /**
     * Prints the response after a task has been marked as uncompleted.
     *
     * @param task The task that has been marked as uncompleted.
     */
    public void unmarkTask(Task task) {
        respond(
            "OK, I've marked this task as not done yet:",
            String.format("=> %s", task)
        );
    }

    /**
     * Prints the response after the addition of a task.
     *
     * @param task Task that was added.
     * @param length Length of the list after addition of task.
     */
    public void addTask(Task task, int length) {
        respond(
            "Got it. I've added this task:",
            String.format("=> %s", task),
            "",
            String.format("Now you have %d tasks in the list", length)
        );
    }

    /**
     * Prints the response after the deletion of a task.
     *
     * @param task Task that was deleted.
     * @param length Length of the list after deletion of task.
     */
    public void deleteTask(Task task, int length) {
        respond(
            "Noted. I've removed this task",
            String.format("=> %s", task),
            "",
            String.format("Now you have %d tasks in the list", length)
        );
    }

    /**
     * Clears the buffer and returns the string that was stored.
     *
     * @return String that was stored in the buffer.
     */
    public String flush() {
        String s = sb.toString();
        sb.setLength(0);
        return s;
    }
}
