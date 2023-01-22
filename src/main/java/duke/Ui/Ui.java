package duke.Ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.Task.Task;
import duke.TaskList.TaskList;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public void showLine() {
        System.out.println("  ------------------------------------");
    }

    public void showWelcome() {
        showLine();
        System.out.println("  Hello! I'm Duke\n  What can I do for you?");
        showLine();
    }

    public void showBye() {
        System.out.println("  Bye. Hope to see you again soon!");
    }

    public void showLoading(String filePath) {
        System.out.println(String.format("%n  Trying to load tasks from %s...", filePath));
    }

    public void showSuccessfulLoad(TaskList tasks) {
        if (tasks.size() == 0)
            System.out.println("  Data file was loaded but no tasks could be found.");
        else
            System.out.println("  Tasks loaded successfully!");
    }

    public void showError(String message) {
        System.out.println(String.format("  %s", message));
    }

    public void showLoadingError() {
        System.out.println(
                "\n  Data file could not be found.\n  A new data file will be auto generated upon insert of a task.");
    }

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    public void closeScanner() {
        this.sc.close();
    }

    public void printTask(Task task, ArrayList<Task> taskList, boolean toAdd) {
        if (toAdd)
            System.out.println("  Got it. I've added this task:");
        else
            System.out.println("  Noted. I've removed this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("  Now you have %d %s in the list.", taskList.size(),
                taskList.size() == 1 ? "task" : "tasks"));
    }

    public void printMarkTask(Task task, boolean toMark) {
        if (toMark)
            System.out.println("  Nice! I've marked this task as done:");
        else
            System.out.println("  I've marked this task as not done:");
        System.out.println(String.format("  %s", task.toString()));
    }

    public void printNoTasks() {
        System.out.println("  No tasks added yet");
    }

    public void printTasks(TaskList tasks) {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("  %d.%s", i + 1, tasks.getTask(i).toString()));
        }
    }

    /**
     * Prints all tasks that contain the keyword.
     * @param matchingTasksIndex An arraylist of the indexes of the tasks that contain the keyword.
     * @param tasks The current task list.
     */
    public void printMatchingTasks(ArrayList<Integer> matchingTasksIndex, ArrayList<Task> tasks) {
        if (matchingTasksIndex.isEmpty()) {
            System.out.println("  No matching tasks found.");
        } else {
            System.out.println("  Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasksIndex.size(); i++) {
                int taskIndex = matchingTasksIndex.get(i);
                System.out.println(String.format("  %d.%s", taskIndex + 1, tasks.get(taskIndex).toString()));
            }
        }
    }

}
