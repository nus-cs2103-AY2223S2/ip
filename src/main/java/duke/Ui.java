package duke;

import java.util.Scanner;

import duke.exception.DukeException;

import duke.task.Task;

/** Class that handles user interface */
public class Ui {

    /** Scanner to read in user input */
    private Scanner sc = new Scanner(System.in);

    /**
     * Print greeting text.
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
    }

    /**
     * Print arrow to indicate user should input something.
     */
    public void showArrow() {
        System.out.print("> ");
    }

    /**
     * Print separator.
     */
    public void showSeparator() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Print error message.
     *
     * @param e Error.
     */
    public void showErrorMessage(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Read in user's next command.
     *
     * @return User's next command.
     */
    public String readCommand() {
        String command = this.sc.nextLine().trim();
        return command;
    }

    /**
     * Print user's tasks.
     *
     * @param tasks User's task list.
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    /**
     * Print text for adding task to task list.
     *
     * @param task Task to be added.
     * @param tasks Task list.
     */
    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Print text for marking task.
     *
     * @param task Task that has been marked.
     */
    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Print text for unmarking task.
     *
     * @param task Task that has been unmarked.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("Got it. I've marked this task as not done:");
        System.out.println(task);
    }

    /**
     * Print text for deleting task.
     *
     * @param task Deleted task.
     * @param tasks Task list.
     */
    public void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Print goodbye text.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void closeScanner() {
        this.sc.close();
    }

}
