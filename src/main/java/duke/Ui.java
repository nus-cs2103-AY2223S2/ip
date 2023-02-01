package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class for Ui
 */
public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Greet user
     */
    public void greetUser() {
        System.out.println(LOGO);
        System.out.println("Hello I'm Duke\n"
                + "What can I do for you?\n");
    }

    /**
     * Say goodbye to user
     */
    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Inform user that task has been deleted
     * @param toDelete Task to be deleted
     * @param tasksSize Current task list size
     */
    public void informDeletion(Task toDelete, int tasksSize) {
        String taskCount = (tasksSize - 1 == 1) ? "task" : "tasks";
        System.out.println("Got it. I've removed this task:\n   "
                + toDelete
                + "\nNow you have " + (tasksSize - 1) + " " + taskCount + " in your list\n");
    }

    /**
     * Inform user that task has been marked
     * @param toMarkTask Task that has been marked
     */
    public void informTaskIsMarked(Task toMarkTask) {
        System.out.println("Nice! I've marked this task as done:\n   "
                + toMarkTask + "\n");
    }

    /**
     * Inform user that task has been unmarked
     * @param toUnMarkTask Task that has been unmarked
     */
    public void informTaskIsUnMarked(Task toUnMarkTask) {
        System.out.println("Nice! I've unmarked this task as incomplete:\n   "
                + toUnMarkTask + "\n");
    }

    /**
     * Inform user that task has been added to list of existing tasks
     * @param task Task that has been added
     * @param size Size of new task list
     */
    public static void informTaskIsAdded(Task task, int size) {
        if (Objects.isNull(task)) {
            return;
        }
        String taskCount = (size == 1) ? "task" : "tasks";
        System.out.println("Got it. I've added this task:\n   "
                + task
                + "\nNow you have " + size + " " + taskCount + " in your list\n");
    }

    /**
     * Print all tasks in current Task List
     * @param tasks TaskList of existing tasks
     */
    public void printTasks(TaskList tasks) {
        int taskNumber = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(taskNumber + ". " + tasks.getTask(i).toString());
            taskNumber++;
        }
        System.out.print("\n");
    }

    /**
     * Print all tasks containing keyword
     * @param tasks ArrayList of tasks containing keyword
     */
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

    /**
     * Shows error message to user
     * @param e Exception thrown
     */
    public void showError(Exception e) {
        System.out.print(e.getMessage());
    }

    public static String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
