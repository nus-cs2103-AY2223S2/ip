package duke.backend;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a duke.Ui object that handles the user interface.
 */
public class ResponseGenerator {

    public ResponseGenerator() {}


    /**
     * @return the greeting message.
     */
    public static String greetingMessage() {
        return "Hello! I'm Duke!\nWhat can I do for you?";
    }

    /**
     * @return the goodbye message.
     */
    public static String goodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * @param taskList The task list to be printed.
     * @return the message to be printed.
     */
    public static String taskListToMessage(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You have no tasks in your list.";
        } else {
            return "Here are the tasks in your list:\n" + taskList;
        }
    }

    /**
     * @param taskList The task list to be printed.
     * @return the message to be printed.
     */
    public static String tasksFoundToMessage(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "No tasks in list matching the keyword.";
        } else {
            return "Here are the matching tasks in your list:\n" + taskList;
        }
    }

    /**
     * @param e The duke.exception.DukeException to be printed.
     * @return the message to be printed.
     */
    public static String dukeExceptionMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * @param task The task that was added.
     * @param size The size of the task list after addition.
     * @return the message to be printed.
     */
    public static String printTaskAdded(Task task, int size) {
        assert task != null : "task should not be null";
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
    }


    /**
     * @param task The task that was marked.
     * @return the message to be printed.
     */
    public static String printTaskMarked(Task task) {
        assert task != null : "task should not be null";
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * @param task The task that was unmarked.
     * @return the message to be printed.
     */
    public static String printTaskUnmarked(Task task) {
        assert task != null : "task should not be null";
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * @param task The task that was deleted.
     * @param size The size of the task list after deletion.
     * @return the message to be printed.
     */
    public static String printTaskDeleted(Task task, int size) {
        assert task != null : "task should not be null";
        return "Noted. I've removed this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
    }
}
