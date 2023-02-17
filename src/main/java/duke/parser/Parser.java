package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * The Parser class is responsible for processing user input, updating the data in the task list, and providing feedback
 * to the user through the UI.
 *
 * @author owen-yap
 *
 */
public class Parser {
    private Storage storage;
    private Ui ui;
    private TaskList data;
    /**
     * Constructs a Parser object with a reference to the Storage and Ui objects.
     *
     * @param storage The reference to the Storage object.
     * @param ui The reference to the Ui object.
     */
    public Parser(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
        this.data = storage.loadTasks();
    }
    /**
     * Parses the input string and processes the command before executing the logic of the command.
     *
     * @param input The input string to be parsed.
     * @return Returns false if the command is "bye", otherwise returns true.
     */
    public String parse(String input) {
        String cmd;

        try {
            String[] inputs = input.split(" ", 2);
            cmd = inputs[0];

            switch (cmd) {
            case "bye":
                return byeCommand();
            case "list":
                return listCommand(data.toStringArray());
            case "mark":
                return markCommand(inputs);
            case "unmark":
                return unmarkCommand(inputs);
            case "todo":
                return todoCommand(inputs);
            case "deadline":
                return deadlineCommand(inputs);
            case "event":
                return eventCommand(inputs);
            case "delete":
                return deleteCommand(inputs);
            case "find":
                return findCommand(inputs);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            }

        } catch (DukeException | IllegalArgumentException e) {
            return this.ui.formatMsg(e.getMessage());
        }
    }

    private String byeCommand() {
        return "Bye. Hope to see you again soon!";
    }

    private String listCommand(String[] list) {
        return this.ui.listToString(list);
    }

    private String markCommand(String[] inputs) {
        int idx = Integer.parseInt(inputs[1]) - 1;
        Task tsk = this.data.get(idx);
        tsk.mark();
        String markMsg = "Nice! I've marked this task as done:";
        String markedTask = String.format(" %s", tsk);
        String[] markedMsg = {markMsg, markedTask};
        this.storage.saveTasks(this.data);
        return this.ui.formatMsg(markedMsg);
    }

    private String unmarkCommand(String[] inputs) {
        int idx = Integer.parseInt(inputs[1]) - 1;
        Task tsk = this.data.get(idx);
        tsk.unmark();
        String markMsg = "OK, I've marked this task as not done yet:";
        String unmarkedTask = String.format(" %s", tsk);
        String[] unmarkedMsg = {markMsg, unmarkedTask};
        this.storage.saveTasks(this.data);
        return this.ui.formatMsg(unmarkedMsg);
    }

    private String todoCommand(String[] inputs) throws DukeException {
        if (inputs.length == 1)
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        Task tsk = new Todo(inputs[1]);
        this.data.add(tsk);
        String tskNum = String.format("Now you have %d tasks in the list.", data.size());
        String[] todoMsg = {"Got it. I've added this task:", " " + tsk.toString(), tskNum};
        this.storage.saveTasks(this.data);
        return this.ui.formatMsg(todoMsg);
    }

    private String deadlineCommand(String[] inputs) throws DukeException {
        if (inputs.length == 1)
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        String[] desc = inputs[1].split(" /by ");
        LocalDateTime date = parseDate(desc[1]);
        Task tsk = new Deadline(desc[0], date);
        this.data.add(tsk);
        String tskNum = String.format("Now you have %d tasks in the list.", data.size());
        String[] deadlineMsg = {"Got it. I've added this task:", " " + tsk.toString(), tskNum};
        this.storage.saveTasks(this.data);
        return this.ui.formatMsg(deadlineMsg);
    }

    private String eventCommand(String[] inputs) {
        String[] desc = inputs[1].split(" /from | /to ");
        LocalDateTime from = parseDate(desc[1]);
        LocalDateTime to = parseDate(desc[2]);
        Task tsk = new Event(desc[0], from, to);
        data.add(tsk);
        String tskNum = String.format("Now you have %d tasks in the list.", data.size());
        String[] eventMsg = {"Got it. I've added this task:", " " + tsk.toString(), tskNum};
        this.storage.saveTasks(this.data);
        return this.ui.formatMsg(eventMsg);
    }

    private String deleteCommand(String[] inputs) {
        int idx = Integer.parseInt(inputs[1]) - 1;
        Task tsk = this.data.get(idx);
        this.data.delete(idx);
        String tskNum = String.format("Now you have %d tasks in the list.", this.data.size());
        String[] delMsg = {"Noted. I've removed this task:", tsk.toString(), tskNum};
        this.storage.saveTasks(this.data);
        return this.ui.formatMsg(delMsg);
    }

    private String findCommand(String[] inputs) {
        String query = inputs[1];
        String[] taskStrList = this.data.find(query);
        if (taskStrList.length == 0) {
            return this.ui.formatMsg("Sorry, I could not find any matches.");
        } else {
            return this.ui.listToString("Here are the matching tasks in your list: ", data.find(query));
        }
    }
    /**
     * Parses a given date string into a {@link LocalDateTime} object using the format M/d/yyyy HHmm.
     *
     * @param dateString The date string to parse in the format "M/d/yyyy HHmm".
     * @return The {@link LocalDateTime} object parsed from the given date string.
     * @throws IllegalArgumentException If the given date string does not match the expected format "M/d/yyyy HHmm".
     */
    private LocalDateTime parseDate(String dateString) {
        LocalDateTime date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        try {
            date = LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString + ". Please give your date"
                    + " in the form M/d/yyyy HHmm.");
        }
        return date;
    }
}
