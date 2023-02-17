package duke.ui;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

import javax.print.DocFlavor;

/**
 * Class to perform UI operations.
 */
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

    /**
     * Returns Saul's greeting message (bounded by lines above and below).
     */
    public StringBuilder getSaulGreeting() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello I'm Saul!");
        sb.append("What can I do for you?");
        return sb;
    }


    /**
     * Prints Duke's farewell message (bounded by lines above and below).
     */
    public void farewell() {
        printLine();
        System.out.printf("     %s%n", "Bye. Hope to see you again soon!");
        printLine();
    }

    public StringBuilder getSaulBye() {
        StringBuilder sb = new StringBuilder();
        sb.append("Until next time... Better call Saul!");
        return sb;
    }

    /**
     * Marks task (selected by position in list).
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get task number.
     */
    public StringBuilder markTask(TaskList tasks, Parser parser) throws DukeException {
        int taskNumber;
        StringBuilder sb = new StringBuilder();
        try {
            taskNumber = parser.getTaskNumber();
            tasks.getTask(taskNumber - 1).markAsDone();
            sb.append("Nice! I've marked this task as done:\n");
            sb.append(tasks.getTask(taskNumber - 1).toString());
            sb.append("\n");
            return sb;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Did you input a valid task number?");
        }
    }

    /**
     * Unmarks task (selected by position in list).
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get task number.
     */
    public StringBuilder unmarkTask(TaskList tasks, Parser parser) throws DukeException {
        int taskNumber;
        StringBuilder sb = new StringBuilder();
        try {
            taskNumber = parser.getTaskNumber();
            tasks.getTask(taskNumber - 1).markAsNotDone();
            sb.append("OK, I've marked this task as not done yet:\n");
            sb.append(tasks.getTask(taskNumber - 1).toString());
            sb.append("\n");
            return sb;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Did you input a valid task number?");
        }
    }

    /**
     * Prints confirmation of added task and number of tasks currently in list.
     *
     * @param tasks List of current tasks.
     * @param t Task to confirm addition of.
     */
    private StringBuilder confirmAddition(TaskList tasks, Task t) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(t.toString());
        sb.append("\nNow you have ").append(tasks.getSize()).append(" tasks in the list.");
        return sb;
    }

    /**
     * Adds ToDo task to list.
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get ToDo details from input.
     * @throws DukeException on empty ToDo description.
     */
    public StringBuilder addToDo(TaskList tasks, Parser parser) throws DukeException {
        if (!parser.hasDescription()) {
            throw new DukeException("Did you use the right format (todo <desc>)?");
        }
        String description = parser.parseToDoDescription();
        if (description.isBlank()) {
            throw new DukeException("Did you use the right format (todo <desc>)?");
        }
        Task t = new ToDo(description);
        tasks.addTask(t);
        return confirmAddition(tasks, t);
    }

    /**
     * Adds Deadline task to list.
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get Deadline details from input.
     */
    public StringBuilder addDeadline(TaskList tasks, Parser parser) throws DukeException {
        Task t = new Deadline(parser.parseDeadlineDescription(),
                parser.parseDeadlineDate());
        tasks.addTask(t);
        return confirmAddition(tasks, t);
    }

    /**
     * Adds Event task to list.
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get Event details from input.
     */
    public StringBuilder addEvent(TaskList tasks, Parser parser) throws DukeException {
        Task t = new Event(parser.parseEventDescription(),
                parser.parseEventFrom(), parser.parseEventTo());
        tasks.addTask(t);
        return confirmAddition(tasks, t);
    }

    /**
     * Deletes task from list.
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get task number.
     * @throws DukeException
     */
    public StringBuilder deleteTask(TaskList tasks, Parser parser) throws DukeException {
        int taskNumber;
        StringBuilder sb = new StringBuilder();
        try {
            taskNumber = parser.getTaskNumber();
            Task t = tasks.removeTask(taskNumber - 1);
            sb.append("Noted. I've removed this task:\n");
            sb.append(t.toString()).append("\n");
            sb.append("Now you have ").append(tasks.getSize()).append(" tasks in the list.\n");
            return sb;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Did you input a valid task number?");
        }
    }

    /**
     * Lists tasks to user.
     *
     * @param tasks List of current tasks.
     * @throws DukeException
     */
    public StringBuilder listTasks(TaskList tasks) throws DukeException {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(i + 1);
            sb.append(new StringBuilder(". "));
            sb.append(tasks.getTask(i).toString());
            sb.append("\n");
        }
        return sb;
    }

    /**
     * Lists tasks matching keyword.
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get task number.
     * @throws DukeException
     */
    public StringBuilder findAndListTasks(TaskList tasks, Parser parser) throws DukeException {
        if (!parser.hasDescription()) {
            throw new DukeException("Did you enter a keyword to search for?");
        }
        String keyword = parser.parseFindKeyword();
        if (keyword.isBlank()) {
            throw new DukeException("Did you enter a valid keyword?");
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        int foundCount = 0;
        for (int i = 0; i < tasks.getSize(); ++i) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                sb.append(++foundCount).append(".").append(task.toString());
            }
        }
        return sb;
    }

    /**
     * Tags task (selected by position in list).
     *
     * @param tasks List of current tasks.
     * @param parser Parser object to get task number and tag.
     */
    public StringBuilder tagTask(TaskList tasks, Parser parser) throws DukeException {
        int taskNumber;
        String tag;
        StringBuilder sb = new StringBuilder();
        try {
            taskNumber = parser.getTaskNumber();
            tag = parser.getTag();
            tasks.getTask(taskNumber - 1).setTag(tag);
            sb.append("I've tagged this task as #").append(tag).append("\n");
            sb.append(tasks.getTask(taskNumber - 1).toString());
            sb.append("\n");
            return sb;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Did you input a valid task number?");
        }
    }

    /**
     * Prints message to user indicating that loading from storage failed,
     * and that new empty task list will be created.
     */
    public void showLoadingError() {
        System.out.println("Unable to load saved data.");
        System.out.println("Creating empty task list...");
    }
}
