package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;
import java.util.List;

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
     * Prints a response to List command to the user.
     */
    public String listResponse(TaskList list) {
        return "Here are the tasks in your list:\n"
                + list.toString();
    }

    /**
     * Prints a response to Delete command to the user.
     */
    public String deleteResponse(Task task, TaskList taskList) {
        return HORIZONTAL_LINE + "\n" +
                "Noted. I've removed this task:" + "\n" +
                task + "\n" + "Now you have " + taskList.getSize()
                + " tasks in the list." + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints a response to Mark command to the user.
     */
    public String markResponse(Task task) {
        return HORIZONTAL_LINE + "\n" +
                "Nice! I've marked this task as done:"
                + "\n" + task + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints a response to Unmark command to the user.
     */
    public String unmarkResponse(Task task) {
        return HORIZONTAL_LINE + "\n" +
                "OK, I've marked this task as not done yet:"
                + "\n" + task + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Prints a response to Todo, Event or Deadline command to the user.
     */
    public String addResponse(Task task, TaskList taskList) {
        return HORIZONTAL_LINE + "\n" + "Got it. I've added this task:"
                + "\n" + task + "\n" + "Now you have " + taskList.getSize()
                + " tasks in the list." + "\n" + HORIZONTAL_LINE;
    }

    public String findResponse(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task task : taskList) {
            sb.append(String.format("%d.%s\n", i, task.toString()));
            ++i;
        }
        return HORIZONTAL_LINE+ "\n" + sb + HORIZONTAL_LINE + "\n";
    }

}
