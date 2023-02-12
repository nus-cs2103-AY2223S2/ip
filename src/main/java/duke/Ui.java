package duke;

import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Prints messages to the user during execution.
 */
public class Ui {

    /**
     * Prints a line which encloses messages.
     */
    public void showLine() {
        System.out.println("--------------------------------");
    }

    /**
     * Prints application logo and welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        System.out.println("Bye~ Hope to see you next time! >v<");
    }

    /**
     * Prints error message for loading error.
     */
    public void showLoadingError() {
        System.out.println("Loading error: No saved task list found.");
    }

    /**
     * Reads user inputs.
     * @return A string of user's inputs.
     */
    public String readCommand() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }

    /**
     * Prints error messages for general error.
     * Exception: no message will be printed for error of "Failed Command Generation".
     * @param msg Given error massage.
     */
    public void showError(String msg) {
        if (!msg.equals("Failed Command Generation")) {
            System.out.println(msg);
        }
    }

    /**
     * Prints messages to show a new Todo task is added.
     * @param t New Todo task.
     */
    public void addTodo(Todo t) {
        System.out.println("This todo has been added!");
        System.out.println("  " + t);
    }

    /**
     * Prints messages to show a new Deadline task is added.
     * @param d New Deadline task.
     */
    public void addDeadline(Deadline d) {
        System.out.println("This deadline had been added! Try to finish it early 0v0");
        System.out.println("  " + d);
    }

    /**
     * Prints messages to show a new Event task is added.
     * @param e New Event task.
     */
    public void addEvent(Event e) {
        System.out.println("This event has been added! Hope you will enjoy it :D");
        System.out.println("  " + e);
    }

    /**
     * Prints the total number of tasks in current task list.
     * @param tasks Current task list.
     */
    public void showCurrentTaskNo(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Prints the current task list.
     * @param tasks Current task list.
     */
    public void showList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("Here are the current tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print((i + 1) + ".");
                System.out.println(tasks.get(i).toString());
            }
        }
    }

    /**
     * Print messages to alert user when input task index exceeds the length of task list.
     * @param index Input task index.
     */
    public void showIdExceedsList(int index) {
        System.out.println("I cannot find task " + (index) + " as it exceeds the total tasks number");
    }

    /**
     * Print messages to show that the target task has been marked done.
     * @param tasks Current task list.
     * @param index Input task index.
     */
    public void markTask(TaskList tasks, int index) {
        System.out.println("Nice! Great job for completing this task:");
        System.out.println((tasks.get(index).toString()));
    }

    /**
     * Print messages to show that the target task have been marked as not done.
     * @param tasks Current task list.
     * @param index Input task index.
     */
    public void unmarkTask(TaskList tasks, int index) {
        System.out.println("This item is marked as not done yet");
        System.out.println((tasks.get(index).toString()));
    }

    /**
     * Prints messages to show that the target task have been deleted.
     * @param tasks Current task list.
     * @param index Input task index.
     */
    public void deleteTask(TaskList tasks, int index) {
        System.out.println("This task is deleted from the list:");
        System.out.println("  " + (tasks.get(index).toString()));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list");
    }

    /**
     * Prints alert to user when a new Todo task is not created due to incorrect input format.
     */
    public void todoFormatAlert() {
        System.out.println("Adding new todo failed");
        System.out.println("The task name cannot be empty");
    }

    /**
     * Prints alert to user when a new Deadline task is not created due to incorrect input format.
     */
    public void deadlineFormatAlert() {
        System.out.println("Adding new deadline failed");
        System.out.println("Please enter the deadline with format [name /ddmmyyyy time]");
    }

    /**
     * Prints alert to user when a new Event task is not created due to incorrect input format.
     */
    public void eventFormatAlert() {
        System.out.println("Adding new event failed");
        System.out.println("Please enter the task with the format [name /ddMMyyyy HHmm /ddMMyyyy HMmm]");
    }
}
