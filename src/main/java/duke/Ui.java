package duke;

/**
 * Encapsulates a User Interface that interacts with user inputs.
 *
 * @author Sean Chin Jun Kai
 */
public class Ui {

    /**
     * Returns error message to be displayed to user when error occurs while using Duke.
     * @param error exception caught.
     * @return the message string of the thrown exception.
     */
    public String showErrorMessage(Exception error) {
        return error.getMessage();
    }

    /**
     * Returns the string representation of Task List to be shown to user.
     * @param tasks Task List
     * @return string representation of the Task List
     */
    public String showTasksMessage(TaskList tasks) {
        if (tasks.size() != 0) {
            return tasks.toString();
        } else {
            return "There are no tasks currently!";
        }
    }

    /**
     * Returns the goodbye message when user triggers the bye command.
     * @return goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the message when user successfully adds a task to the Task List.
     * @param task Task added.
     * @param tasks Task List.
     * @return added Task success message.
     */
    public String addedTaskMessage(Task task, TaskList tasks) {
        String result = "";
        result += ("Got it. I've added this task\n" + task);
        result += String.format("Now you have %d tasks in the list.\n", tasks.size());
        return result;
    }

    /**
     * Returns the message when user successfully marks a task in the Task List.
     * @param task Task marked.
     * @return mark Task success message.
     */
    public String markTaskMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Returns the message when user successfully unmarks a task in the Task List.
     * @param task Task unmarked.
     * @return unmark Task success message.
     */
    public String unmarkTaskMessage(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n" + task);
    }


    /**
     * Returns the message when user successfully deletes a task from the Task List.
     * @param task Task deleted.
     * @param tasks Task List.
     * @return delete Task success message.
     */
    public String deleteTaskMessage(Task task, TaskList tasks) {
        String result = "";
        result += ("Noted. I've removed this task:\n" + task);
        result += String.format("Now you have %d tasks in the list.\n", tasks.size());
        return result;
    }

}
