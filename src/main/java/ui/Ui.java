package ui;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;

public class Ui {

    /**
     * Prints a line 4 spaces away from the left edge of the screen to visually
     * separate Duke's replies from user input.
     */
    public static void printLine() {
        System.out.printf("%64s%n", "    ____________________________________________________________");
    }

    /**
     * Prints Duke's greeting message (bounded by lines above and below).
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.printf("     %s%n", "Hello! I'm Duke");
        System.out.printf("     %s%n", "What can I do for you?");
        printLine();
    }

    public static void farewell() {
        printLine();
        System.out.printf("     %s%n", "Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Mark task (selected by position in list).
     *
     * @param input User input.
     */
    public static void markTask(TaskList tasks, String input) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.getTask(taskNumber - 1).markAsDone();
            System.out.printf("     %s%n", "Nice! I've marked this task as done:");
            System.out.printf("       %s%n", tasks.getTask(taskNumber - 1).toString());
        } catch (NumberFormatException|IndexOutOfBoundsException e) {
            System.out.printf("     %s%n", "Please input valid task number.");
        }
    }

    /**
     * Unmark task (selected by position in list).
     *
     * @param input User input.
     */
    public static void unmarkTask(TaskList tasks, String input) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.getTask(taskNumber - 1).markAsNotDone();
            System.out.printf("     %s%n", "OK, I've marked this task as not done yet:");
            System.out.printf("       %s%n", tasks.getTask(taskNumber - 1).toString());
        } catch (NumberFormatException|IndexOutOfBoundsException e) {
            throw new DukeException("Input a valid task number.");
        }
    }

    /**
     * Prints confirmation of added task and number of tasks currently in list.
     *
     * @param t Task to confirm addition of.
     */
    private static void confirmAddition(TaskList tasks, Task t) {
        System.out.printf("     %s%n", "Got it. I've added this task:");
        System.out.printf("       %s%n", t.toString());
        System.out.printf("     %s%d%s%n", "Now you have ", tasks.getSize(), " tasks in the list.");
    }

    /**
     * Add ToDo task to list.
     *
     * @param input User input.
     * @throws DukeException on empty ToDo description.
     */
    public static void addToDo(TaskList tasks, String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task t = new ToDo(description);
        tasks.addTask(t);
        confirmAddition(tasks, t);
    }

    /**
     * Add Deadline task to list.
     *
     * @param input User input.
     */
    public static void addDeadline(TaskList tasks, String input) {
        int byIndex = input.indexOf("/by");
        String description = input.substring(9, byIndex - 1);
        String by = input.substring(byIndex + 4);
        Task t = new Deadline(description, by);
        tasks.addTask(t);
        confirmAddition(tasks, t);
    }

    /**
     * Add Event task to list.
     *
     * @param input User input.
     */
    public static void addEvent(TaskList tasks, String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(6, fromIndex - 1);
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);
        Task t = new Event(description, from, to);
        tasks.addTask(t);
        confirmAddition(tasks, t);
    }

    /**
     * Delete task from list.
     *
     * @param input User input.
     */
    public static void deleteTask(TaskList tasks, String input) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.split(" ")[1]);
            Task t = tasks.removeTask(taskNumber - 1);
            System.out.printf("     %s%n", "Noted. I've removed this task:");
            System.out.printf("       %s%n", t.toString());
            System.out.printf("     %s%d%s%n", "Now you have ", tasks.getSize(), " tasks in the list.");
        } catch (NumberFormatException|IndexOutOfBoundsException e) {
            throw new DukeException("Input a valid task number.");
        }
    }

    public void showLoadingError() {
        System.out.println("Unable to load saved data.");
        System.out.println("Creating empty task list...");
    }
}
