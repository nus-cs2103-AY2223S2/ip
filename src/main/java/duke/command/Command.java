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
    public void executeCommand(Parser.Action action, String userInput, TaskList tasks, Storage storage)
            throws DukeException {
        switch(action) {
        case LIST:
            this.listTasksCommand(tasks);
            break;
        case FIND:
            tasks.findTask(this.parser.getKeyword(userInput));
            break;
        case TODO:
            tasks.addTask(new ToDo(this.parser.getTodoDescription(userInput)));
            storage.saveData(tasks);
            break;
        case DEADLINE:
            tasks.addTask(new Deadline(this.parser.getDeadlineDescription(userInput),
                    this.parser.getDeadlineDate(userInput)));
            storage.saveData(tasks);
            break;
        case EVENT:
            LocalDate[] eventDetails = this.parser.getEventDateDetails(userInput);
            tasks.addTask(new Event(this.parser.getEventDescription(userInput),
                    eventDetails[0], eventDetails[1]));
            storage.saveData(tasks);
            break;
        case MARK:
            tasks.markTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            break;
        case UNMARK:
            tasks.unmarkTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            break;
        case DELETE:
            tasks.removeTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            break;
        case UNKNOWN:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        default:
            throw new DukeException("");
        }
    }

    /**
     * Loop through task lists to print task details
     * @param store
     * @throws DukeException
     */
    public void listTasksCommand(TaskList store) throws DukeException {
        try {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < store.getSize(); i++) {
                ui.sendTaskDetails(i + 1, store.getTask(i));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
