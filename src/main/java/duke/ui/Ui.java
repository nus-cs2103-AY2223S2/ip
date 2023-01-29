package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

public class Ui {

    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    public static final String GREETING_MESSAGE =
            HORIZONTAL_LINE + "\n" + "Hello! I'm Duke" +
                    "\n" + "What can I do for you?" + "\n" + HORIZONTAL_LINE;

    public static final String BYE_MESSAGE =
            HORIZONTAL_LINE + "\n" + "Bye. Hope to see you again soon!" +
                    "\n" + HORIZONTAL_LINE;

    public void greetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public void byeMessage() {
        System.out.println(BYE_MESSAGE);
    }

    public void listResponse(TaskList list) {
        System.out.println(HORIZONTAL_LINE + "\n" +
                "Here are the tasks in your list:");
        for (int i = 1; i <= list.getSize(); i++) {
            System.out.println(i + "." + list.getTask(i - 1));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void deleteResponse(Task task, TaskList taskList) {
        System.out.println(HORIZONTAL_LINE + "\n" +
                "Noted. I've removed this task:" + "\n" +
                task + "\n" + "Now you have " + taskList.getSize() +
                " tasks in the list." + "\n" + HORIZONTAL_LINE);
    }

    public void markResponse(Task task) {
        System.out.println(HORIZONTAL_LINE + "\n" +
                "Nice! I've marked this task as done:" +
                "\n" + task + "\n" + HORIZONTAL_LINE);
    }

    public void unmarkResponse(Task task) {
        System.out.println(HORIZONTAL_LINE + "\n" +
                "OK, I've marked this task as not done yet:" +
                "\n" + task + "\n" + HORIZONTAL_LINE);
    }

    public void addResponse(Task task, TaskList taskList) {
        System.out.println(HORIZONTAL_LINE + "\n" + "Got it. I've added this task:" +
                "\n" + task + "\n" + "Now you have " + taskList.getSize() +
                " tasks in the list." + "\n" + HORIZONTAL_LINE);
    }

    public void findResponse(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task task : taskList) {
            sb.append(String.format("%d.%s\n", i, task.toString()));
            ++i;
        }
        System.out.print(HORIZONTAL_LINE+ "\n" + sb + HORIZONTAL_LINE + "\n");
    }

}
