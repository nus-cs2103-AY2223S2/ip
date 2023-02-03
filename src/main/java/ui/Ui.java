package ui;

import java.time.format.DateTimeFormatter;

import tasklist.TaskList;
import task.Task;

/**
 * User Interface Class that deals with interactions with the user.
 */
public class Ui {
    final static String ENTRY_OUTPUT = "Hello! I'm Duke\nWhat can I do for you?";
    final static String BYE_OUTPUT = "Bye. Hope to see you again soon!";
    final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/d HHmm");
    final static DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public boolean isClosed;

    /**
     * Constructor.
     */
    public Ui() {
       this.isClosed = false;
    }

    /**
     * Prints the Exception given.
     * @param e Exception.
     */
    public void printException(Exception e) {
        System.out.println(e);
    }

    /**
     * Prints the exit output.
     */
    public void printBye() {
        System.out.println(BYE_OUTPUT);
    }

    /**
     * Prints the entry welcome.
     */
    public void printEntry() {
        System.out.println(ENTRY_OUTPUT);
    }

    /**
     * Prints the response of deleting the task from the list.
     *
     * @param task Task that is deleted.
     * @param list List of tasks.
     */
    public void printHandleDelete(Task task, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
    }

    /**
     * Prints the response of marking the task.
     *
     * @param task Task that is marked.
     */
    public void printHandleMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    /**
     * Prints the response of unmarking the task.
     *
     * @param task Task that is unmarked.
     */
    public void printHandleUnmark(Task task) {
        System.out.println("OK,, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    /**
     * Prints the response of adding the task to the list.
     *
     * @param newTask Task is added to the list
     * @param list List of tasks
     */
    public void printAddTask(Task newTask, TaskList list) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
    }

    /**
     * Prints the tasks in the list.
     *
     * @param list List of tasks.
     */
    public void printGetList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.getTask(i);
            System.out.println(i+1 + "." + task.toString());
        }
    }

    /**
     * Prints the error if unable to load list from the hard disk.
     */
    public void showLoadingError() {
        System.out.println("Could not load list. Using a new, empty list instead");
    }

    /**
     * Prints the search output.
     *
     * @param list List of tasks.
     * @param searchWord Search word.
     */
    public void printFind(TaskList list, String searchWord) {
        System.out.println("Here are the matching tasks in your list: ");
        for (Task task: list.list) {
            if (task.description.contains(searchWord)) {
                System.out.println(task.toString());
            }
        }
    }

}
