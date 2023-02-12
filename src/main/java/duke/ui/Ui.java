package duke.ui;

import java.util.List;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Ui handles user interaction.
 */

public class Ui {

    public static final String HORIZONTAL_LINE =
            "________________________________________________________";
    public static final String GREETING_MESSAGE =
            HORIZONTAL_LINE + "\n" + "Hello! I'm Duke"
                    + "\n" + "What can I do for you?" + "\n"
                    + HORIZONTAL_LINE;

    public static final String BYE_MESSAGE =
            HORIZONTAL_LINE + "\n" + "Bye. Hope to see you again soon!"
                    + "\n" + HORIZONTAL_LINE;

    /**
     * Prints a greeting message to the user.
     */
    public static String greetingMessage() {
        return GREETING_MESSAGE;
    }

    /**
     * Prints a bye message to the user.
     */
    public String byeMessage() {
        return BYE_MESSAGE;
    }

    /**
     * Format messages to a decided standard.
     *
     * @param string The string that needs to be formatted.
     */
    public static String formatString(String string) {
        return HORIZONTAL_LINE + "\n" + string + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints a response to List command to the user.
     *
     * @param list The list to print from.
     */
    public String listResponse(TaskList list) {
        return formatString("Here are the tasks in your list:\n"
                + list.toString());
    }

    /**
     * Prints a response to Delete command to the user.
     *
     * @param task The task to be deleted.
     * @param taskList The remaining taskList after deletion.
     */
    public String deleteResponse(Task task, TaskList taskList) {
        return HORIZONTAL_LINE + "\n"
                + "Noted. I've removed this task:" + "\n"
                + task + "\n" + "Now you have " + taskList.getSize()
                + " tasks in the list." + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints a response to Mark command to the user.
     *
     * @param task The task to be marked.
     */
    public String markResponse(Task task) {
        return HORIZONTAL_LINE + "\n"
                + "Nice! I've marked this task as done:"
                + "\n" + task + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints a response to Unmark command to the user.
     *
     * @param task The task to be unmarked.
     */
    public String unmarkResponse(Task task) {
        return HORIZONTAL_LINE + "\n"
                + "OK, I've marked this task as not done yet:"
                + "\n" + task + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints a response to Todo, Event or Deadline command to the user.
     *
     * @param task The task added.
     * @param taskList The task list the task is added to.
     */
    public String addResponse(Task task, TaskList taskList) {
        return HORIZONTAL_LINE + "\n" + "Got it. I've added this task:"
                + "\n" + task + "\n" + "Now you have " + taskList.getSize()
                + " tasks in the list." + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints a response to Find command to the user.
     *
     * @param taskList The filtered task list
     */
    public String findResponse(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task task : taskList) {
            sb.append(String.format("%d. %s\n", i, task.toString()));
            ++i;
        }
        return HORIZONTAL_LINE + "\n" + sb + HORIZONTAL_LINE + "\n";
    }

    public String undoResponse(String string) {
        return formatString("The previous command has been undone by: " + string);
    }

}
