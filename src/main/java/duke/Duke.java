package duke;

import duke.enums.Commands;
import duke.enums.TaskTypes;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.TaskStorage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.Tasklist;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * Duke is the personal assistant for managing user
 * tasks such as todos, deadlines, and events.
 */
public class Duke {
    private static final String FILE_PATH = "data/data.txt";
    private Parser parser;
    private final Tasklist tasklist;
    private boolean isActive;

    public Duke() {
        this.parser = new Parser();
        this.tasklist = new Tasklist(new TaskStorage(FILE_PATH));
    }

    /**
     * Runs Duke and begins parsing input from the user.
     * Duke will deactivate and exit upon receiving "bye"
     * from user input.
     */
    public void run() {
        this.isActive = true;
    }

    public String getResponse(String input) {
        if (this.isActive) {
            try {
                Commands command = this.parser.parseInput(input);
                switch (command) {
                case BYE:
                    return this.exit();
                case LIST:
                    return this.viewList();
                case MARK:
                    return this.mark(parser.getIndex());
                case UNMARK:
                    return this.unmark(parser.getIndex());
                case TODO:
                    return this.addTask(parser.getName());
                case DEADLINE:
                    return this.addTask(
                            parser.getName(),
                            parser.getDeadline());
                case EVENT:
                    return this.addTask(
                            parser.getName(),
                            parser.getStartDate(),
                            parser.getEndDate());
                case DELETE:
                    return this.deleteTask(parser.getIndex());
                case FIND:
                    return this.findTask(parser.getName());
                case DEFAULT:
                    return "I don't quite get what that means.";
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return "Duke is currently offline";
    }

    private String exit() {
        this.isActive = false;
        return "Bye. Hope to see you again soon!";
    }

    private String viewList() {
        if (this.tasklist.size() == 0) {
            return "You currently have no tasks.";
        } else {
            String tasks = formatTasks(this.tasklist.getList());
            return "Here is a list of your tasks:\n" + tasks;
        }
    }

    private String formatTasks(ArrayList<Task> tasks) {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += i + 1 + "." +  tasks.get(i);
            res += "\n";
        }
        return res;
    }

    private String mark(int index) throws DukeException {
        if (this.tasklist.mark(index)) {
            return "I've marked this as done:\n "
                    + this.tasklist.get(index);
        } else {
            return "The selected task has already been marked as done.";
        }
    }

    private String unmark(int index) throws DukeException {
        if (this.tasklist.unmark(index)) {
            return "I've marked this as not done yet:\n "
                    + this.tasklist.get(index);
        } else {
            return "The selected task has not yet been marked as done.";
        }
    }

    private String addTask(String name) {
        Task t = new Todo(name);
        this.tasklist.addTask(t, TaskTypes.TODO);
        return "I've added the following to your list of tasks:\n"
                + t
                + "\n You now have "
                + this.tasklist.size()
                + " task(s) in the list.";
    }

    private String addTask(String name, LocalDate byDate) {
        Task t = new Deadline(name, byDate);
        this.tasklist.addTask(t, TaskTypes.DEADLINE);
        return "I've added the following to your list of tasks:\n"
                + t
                + "\n You now have "
                + this.tasklist.size()
                + " task(s) in the list.";
    }

    private String addTask(String name, LocalDate startDate, LocalDate endDate) {
        Task t = new Event(name, startDate, endDate);
        this.tasklist.addTask(t, TaskTypes.EVENT);
        return "I've added the following to your list of tasks:\n"
                + t
                + "\n You now have "
                + this.tasklist.size()
                + " task(s) in the list.";
    }

    private String deleteTask(int index) throws DukeException {
        Task task = this.tasklist.deleteTask(index);
        return "I've removed the following from your list of tasks:\n"
                + task
                + "\n You now have "
                + this.tasklist.size()
                + " task(s) in the list.";
    }

    private String findTask(String word) {
        ArrayList<Task> tasks = this.tasklist.find(word);
        if (tasks.size() == 0) {
            return "I could not find any task matching your request.";
        } else {
            String matchingTasks = this.formatTasks(tasks);
            return "Here are the matching tasks in your list:\n"
                    + matchingTasks;
        }
    }
}
