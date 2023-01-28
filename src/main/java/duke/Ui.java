package duke;

import java.util.ArrayList;
import java.util.Objects;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void greetUser() {
        System.out.println(LOGO);
        System.out.println("Hello I'm Duke\n"
                + "What can I do for you?\n");
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void informDeletion(Task toDelete, int tasksSize) {
        String taskCount = (tasksSize - 1 == 1) ? "task" : "tasks";
        System.out.println("Got it. I've removed this task:\n   "
                + toDelete
                + "\nNow you have " + (tasksSize - 1) + " " + taskCount + " in your list\n");
    }

    public void informTaskIsMarked(Task toMarkTask) {
        System.out.println("Nice! I've marked this task as done:\n   "
                + toMarkTask + "\n");
    }

    public static void informTaskIsAdded(Task task, int size) {
        if(Objects.isNull(task)) {
            return;
        }
        String taskCount = (size == 1) ? "task" : "tasks";
        System.out.println("Got it. I've added this task:\n   "
                + task
                + "\nNow you have " + size + " " + taskCount + " in your list\n");
    }

    public void printTasks(TaskList tasks) {
        int taskNumber = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(taskNumber + ". " + tasks.getTask(i).toString());
            taskNumber++;
        }
        System.out.print("\n");
    }

    public void printFoundTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("There are no matching tasks\n");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        int taskNumber = 1;
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(taskNumber + ". " + tasks.get(i).toString());
            taskNumber++;
        }
        System.out.print("\n");
    }

    public void showError(Exception e) {
        System.out.print(e.getMessage());
    }
}
