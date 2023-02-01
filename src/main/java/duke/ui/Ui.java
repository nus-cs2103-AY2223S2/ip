package duke.ui;

import java.util.List;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Ui handles user interaction.
 */

public class Ui {

    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
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
    public void greetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    /**
     * Prints a bye message to the user.
     */
    public void byeMessage() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Prints a response to List command to the user.
     */
    public void listResponse(TaskList list) {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Here are the tasks in your list:");
        for (int i = 1; i <= list.getSize(); i++) {
            System.out.println(i + "." + list.getTask(i - 1));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a response to Delete command to the user.
     */
    public void deleteResponse(Task task, TaskList taskList) {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Noted. I've removed this task:" + "\n"
                + task + "\n" + "Now you have " + taskList.getSize()
                + " tasks in the list." + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Prints a response to Mark command to the user.
     */
    public void markResponse(Task task) {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Nice! I've marked this task as done:"
                + "\n" + task + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Prints a response to Unmark command to the user.
     */
    public void unmarkResponse(Task task) {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "OK, I've marked this task as not done yet:"
                + "\n" + task + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Prints a response to Todo, Event or Deadline command to the user.
     * @param task The task added.
     * @param taskList The task list the task is added to.
     */
    public void addResponse(Task task, TaskList taskList) {
        System.out.println(HORIZONTAL_LINE + "\n" + "Got it. I've added this task:"
                + "\n" + task + "\n" + "Now you have " + taskList.getSize()
                + " tasks in the list." + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Prints a response to Find command to the user.
     * @param taskList The filtered task list
     */
    public void findResponse(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task task : taskList) {
            sb.append(String.format("%d.%s\n", i, task.toString()));
            ++i;
        }
        System.out.print(HORIZONTAL_LINE + "\n" + sb + HORIZONTAL_LINE + "\n");
    }

}
