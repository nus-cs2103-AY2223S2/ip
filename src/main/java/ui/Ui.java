package ui;

import exception.DukeException;
import parser.Parser;
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
    public void printLine() {
        System.out.printf("%64s%n", "    ____________________________________________________________");
    }

    /**
     * Prints Duke's greeting message (bounded by lines above and below).
     */
    public void greet() {
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

    public void farewell() {
        printLine();
        System.out.printf("     %s%n", "Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Mark task (selected by position in list).
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get task number.
     */
    public void markTask(TaskList tasks, Parser parser) throws DukeException {
        int taskNumber;
        try {
            taskNumber = parser.getTaskNumber();
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
     * @param tasks List of current tasks.
     * @param parser Parser object to get task number.
     */
    public void unmarkTask(TaskList tasks, Parser parser) throws DukeException {
        int taskNumber;
        try {
            taskNumber = parser.getTaskNumber();
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
     * @param tasks List of current tasks.
     * @param t Task to confirm addition of.
     */
    private void confirmAddition(TaskList tasks, Task t) {
        System.out.printf("     %s%n", "Got it. I've added this task:");
        System.out.printf("       %s%n", t.toString());
        System.out.printf("     %s%d%s%n", "Now you have ", tasks.getSize(), " tasks in the list.");
    }

    /**
     * Add ToDo task to list.
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get ToDo details from input.
     * @throws DukeException on empty ToDo description.
     */
    public void addToDo(TaskList tasks, Parser parser) throws DukeException {
        if (!parser.isValidToDo()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = parser.parseToDoDescription();
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
     * @param tasks List of current tasks.
     * @param parser Parser object to get Deadline details from input.
     */
    public void addDeadline(TaskList tasks, Parser parser) {
        Task t = new Deadline(parser.parseDeadlineDescription(),
                parser.parseDeadlineDate());
        tasks.addTask(t);
        confirmAddition(tasks, t);
    }

    /**
     * Add Event task to list.
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get Event details from input.
     */
    public void addEvent(TaskList tasks, Parser parser) {
        Task t = new Event(parser.parseEventDescription(),
                parser.parseEventFrom(), parser.parseEventTo());
        tasks.addTask(t);
        confirmAddition(tasks, t);
    }

    /**
     * Delete task from list.
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get task number.
     * @throws DukeException
     */
    public void deleteTask(TaskList tasks, Parser parser) throws DukeException {
        int taskNumber;
        try {
            taskNumber = parser.getTaskNumber();
            Task t = tasks.removeTask(taskNumber - 1);
            System.out.printf("     %s%n", "Noted. I've removed this task:");
            System.out.printf("       %s%n", t.toString());
            System.out.printf("     %s%d%s%n", "Now you have ", tasks.getSize(), " tasks in the list.");
        } catch (NumberFormatException|IndexOutOfBoundsException e) {
            throw new DukeException("Input a valid task number.");
        }
    }

    /**
     * List tasks to user.
     *
     * @param tasks List of current tasks.
     * @throws DukeException
     */
    public void listTasks(TaskList tasks) throws DukeException {
        System.out.printf("     %s%n", "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.printf("     %d.%s%n",
                    i + 1,
                    tasks.getTask(i).toString());
        }
    }

    public void showLoadingError() {
        System.out.println("Unable to load saved data.");
        System.out.println("Creating empty task list...");
    }
}
