package duke.command;
import java.time.LocalDate;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command class main logic of code
 */
public class Command {
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for command class
     */
    public Command() {
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Execute command based on the different actions
     * @param action
     * @param userInput
     * @param tasks
     * @throws DukeException
     */
    public String executeCommand(Parser.Action action, String userInput, TaskList tasks, Storage storage)
            throws DukeException {
        String response = "";
        switch(action) {
        case LIST:
            response = this.listTasksCommand(tasks);
            return response;
        case FIND:
            response = tasks.findTask(this.parser.getKeyword(userInput));
            return response;
        case TODO:
            response = tasks.addTask(new ToDo(this.parser.getTodoDescription(userInput)));
            storage.saveData(tasks);
            return response;
        case DEADLINE:
            response = tasks.addTask(new Deadline(this.parser.getDeadlineDescription(userInput),
                    this.parser.getDeadlineDate(userInput)));
            storage.saveData(tasks);
            return response;
        case EVENT:
            LocalDate[] eventDetails = this.parser.getEventDateDetails(userInput);
            response = tasks.addTask(new Event(this.parser.getEventDescription(userInput),
                    eventDetails[0], eventDetails[1]));
            storage.saveData(tasks);
            return response;
        case MARK:
            response = tasks.markTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            return response;
        case UNMARK:
            response = tasks.unmarkTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            return response;
        case DELETE:
            response = tasks.removeTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            return response;
        case UNKNOWN:
            return "I'm sorry, but I don't know what that means :-(";
        default:
            throw new DukeException("");
        }
    }

    /**
     * Loop through task lists to print task details
     * @param store
     * @throws DukeException
     */
    public String listTasksCommand(TaskList store) throws DukeException {
        String s = "Here are the tasks in your list:\n";
        try {
            for (int i = 0; i < store.getSize(); i++) {
                s = s + ui.sendTaskDetails(i + 1, store.getTask(i));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return s;
    }
}
