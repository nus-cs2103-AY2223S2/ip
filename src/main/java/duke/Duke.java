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
    private final String HAS_DUPLICATE_RESPONSE =
            "You already have a similar task in you list. "
                    + "Would you like me to add it in anyway?";
    private final String NO_RESPONSE = "Got it. I will not add it to your list.";
    private Task bufferedTask;

    public Duke() {
        this.parser = new Parser();
        this.tasklist = new Tasklist(new TaskStorage(FILE_PATH));
    }

    /**
     * Prints a greeting message to the user on the standard output.
     */
    public String getGreeting() {
        return "Hello I'm Duke. \n"
                + "What can I do for you?";
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
                    if (hasDuplicate(parser.getName(), TaskTypes.TODO)) {
                        parser.setAwaitingConfirmation(true);
                        bufferedTask = new Todo(parser.getName());
                        return HAS_DUPLICATE_RESPONSE;
                    }
                    return this.addTask(parser.getName());
                case DEADLINE:
                    if (hasDuplicate(parser.getName(), TaskTypes.DEADLINE)) {
                        parser.setAwaitingConfirmation(true);
                        bufferedTask =
                                new Deadline(parser.getName(),
                                        parser.getDeadline());
                        return HAS_DUPLICATE_RESPONSE;
                    }
                    return this.addTask(
                            parser.getName(),
                            parser.getDeadline());
                case EVENT:
                    if (hasDuplicate(parser.getName(), TaskTypes.EVENT)) {
                        parser.setAwaitingConfirmation(true);
                        bufferedTask =
                                new Event(parser.getName(),
                                        parser.getStartDate(),
                                        parser.getEndDate());
                        return HAS_DUPLICATE_RESPONSE;
                    }
                    return this.addTask(
                            parser.getName(),
                            parser.getStartDate(),
                            parser.getEndDate());
                case DELETE:
                    return this.deleteTask(parser.getIndex());
                case FIND:
                    return this.findTask(parser.getName());
                case YES:
                    parser.setAwaitingConfirmation(false);
                    String response = addTask(bufferedTask);
                    bufferedTask = null;
                    return response;
                case NO:
                    parser.setAwaitingConfirmation(false);
                    bufferedTask = null;
                    return NO_RESPONSE;
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

    private String addTask(Task task) {
        if (task instanceof Todo) {
            this.tasklist.addTask(task, TaskTypes.TODO);
        } else if (task instanceof Deadline) {
            this.tasklist.addTask(task, TaskTypes.DEADLINE);
        } else if (task instanceof Event) {
            this.tasklist.addTask(task, TaskTypes.EVENT);
        }
        return "I've added the following to your list of tasks:\n"
                + task
                + "\n You now have "
                + this.tasklist.size()
                + " task(s) in the list.";
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

    private boolean hasDuplicate(String word, TaskTypes type) {
        ArrayList<Task> tasks = this.tasklist.find(word);
        if (tasks.size() == 0) {
            return false;
        }
        switch (type) {
        case TODO:
            for (Task t : tasks) {
                if (t instanceof Todo) {
                    return true;
                }
            }
            break;
        case DEADLINE:
            for (Task t : tasks) {
                if (t instanceof Deadline) {
                    return true;
                }
            }
            break;
        case EVENT:
            for (Task t : tasks) {
                if (t instanceof Event) {
                    return true;
                }
            }
            break;
        }
        return false;
    }
}
