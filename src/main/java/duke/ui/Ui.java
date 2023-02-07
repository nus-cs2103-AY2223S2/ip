package duke.ui;

import duke.TaskList;
import duke.Task;

public class Ui {
    /**
     * Ui class to handle Duke's output
     */

    /**
     * Duke's own display logo.
     */
    public void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void displayLine() {
        System.out.println(" _______________________________________________________________");
    }

    /**
     * Prints out all of Duke's tasks.
     * @param tasks list of tasks.
     */
    public void displayTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Prints out the appropriate message when adding task to the list
     * @param task task to be added
     * @param taskList list of task
     */
    public void displayAddTaskMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Prints out the appropriate message when adding task to the list
     * @param task task to be added
     * @param taskList list of task
     */
    public void displayDeleteTaskMessage(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Prints out the appropriate message when adding task to the list
     * @param task task to be added
     */
    public void displayMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints out the appropriate message when unmarking a task
     * @param task task to be added
     */
    public void displayUnmarkedMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints out Duke's goodbye message
     */
    public void displayGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the appropriate misc message
     */
    public void displayMsg(String output) {
        System.out.println(output);
    }

}
