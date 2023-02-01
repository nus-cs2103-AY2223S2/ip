package duke;

/**
 * Encapsulates a User Interface that interacts with user inputs.
 *
 * @author Sean Chin Jun Kai
 */
public class Ui {

    /**
     * Displays welcome message to user when Duke is first booted.
     *
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }


    /**
     * Displays error message to user.
     *
     */
    public String showErrorMessage(String error) {
        return error;
    }

    /**
     * Displays all tasks in TaskList to user.
     *
     */
    public String showTasksMessage(TaskList tasks) {
        String result = "";
        if (tasks.size() != 0) {
            result += "Here are the tasks in your list:\n";
            result += tasks;
        } else {
            result = "There are no tasks currently!";
        }
        return result;

    }

    /**
     * Displays all matching tasks in TaskList to user.
     *
     */
    public String showMatchingTasksMessage(String tasksString) {
        return tasksString;
    }

    /**
     * Displays goodbye message to user upon bye command.
     *
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays message to user upon successfully adding the task to the TaskList.
     *
     */
    public String addedTaskMessage(Task task, TaskList tasks) {
        String result = "";
        result += ("Got it. I've added this task\n" + task);
        result += String.format("Now you have %d tasks in the list.\n", tasks.size());
        return result;
    }

    /**
     * Displays message to user upon successfully marking the task as done.
     *
     */
    public String markTaskMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays message to user upon successfully unmarking the task.
     *
     */
    public String unmarkTaskMessage(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n" + task);
    }


    /**
     * Displays message to user upon successfully deleting the task.
     *
     */
    public String deleteTaskMessage(Task task, TaskList tasks) {
        String result = "";
        result += ("Noted. I've removed this task:\n" + task);
        result += String.format("Now you have %d tasks in the list.\n", tasks.size());
        return result;
    }

}
