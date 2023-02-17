package commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dukeexceptions.DukeException;
import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;
import items.Deadline;
import items.Event;
import items.Task;
import items.ToDo;

/**
 * Represents a command which adds a <code>Task</code> to a given <code>TaskList</code>
 * @author clydelhui
 */
public class AddCommand extends Command {

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private final TaskType taskType;

    /**
     * Constructor that creates an <code>AddCommand</code> object given a keyword and
     * an <code>ArrayList</code> of strings of parameters
     * @param keyword The keyword for the <code>AddCommand</code>
     * @param params An <code>ArrayList</code> containing the parameters of the command
     *               in <code>String</code> type
     * @throws IllegalCommandException If the given keyword does not match any valid keywords
     */
    public AddCommand(String keyword, ArrayList<String> params) throws IllegalCommandException {
        super(keyword, params);

        switch (keyword) {
        case "todo":
            this.taskType = TaskType.TODO;
            break;
        case "deadline":
            this.taskType = TaskType.DEADLINE;
            break;
        case "event":
            this.taskType = TaskType.EVENT;
            break;
        default:
            throw new IllegalCommandException("Invalid keyword for AddCommand");
        }
    }

    /**
     * {@inheritDoc}
     * @param tasks The <code>TaskList</code> to be acted on by the <code>Command</code>
     * @param ui The <code>Ui</code> to be acted on by the <code>Command</code>
     * @param storage The <code>Storage</code> to be acted on by the <code>Command</code>
     * @throws DukeException When the <code>Task</code> is unable to be successfully added to the <code>TaskList</code>
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.params.size() == 0 || this.params.size() > 3) {
            throw new IllegalInputException("Wrong number of arguments to make a task!");
        }
        Task addedTask = null;
        switch (this.taskType) {
        case TODO:
            if (this.params.size() != 1) {
                throw new IllegalInputException("Too many arguments for a todo!");
            } else if (this.params.get(0).isEmpty()) {
                throw new IllegalInputException("Cannot make a todo without a description!");
            }
            addedTask = new ToDo(this.params.get(0));
            break;
        case DEADLINE:
            if (this.params.size() != 2) {
                throw new IllegalInputException("Wrong number of arguments for a deadline!");
            }
            try {
                addedTask = new Deadline(this.params.get(0), LocalDate.parse(this.params.get(1)));
            } catch (DateTimeParseException e) {
                throw new IllegalInputException("You have keyed in an invalid date");
            }
            break;
        case EVENT:
            if (this.params.size() != 3) {
                throw new IllegalInputException("Wrong number of arguments for an Event!");
            }
            try {
                addedTask = new Event(this.params.get(0),
                        LocalDate.parse(this.params.get(1)), LocalDate.parse(this.params.get(2)));
            } catch (DateTimeParseException e) {
                throw new IllegalInputException("You have keyed in an invalid date");
            }
            break;
        default:
            throw new IllegalCommandException("Unable to add task");
        }

        try {
            tasks.addTask(addedTask);
            storage.refreshStorage(tasks);
            ui.dukeDisplay("I have successfully added the following task for you! \n" + addedTask);
        } catch (IOException e) {
            ui.dukeDisplay("Seems like there is something wrong with the storage file \n"
                    + "Any updates will not be saved!");
            ui.errorDisplay(e);
            e.printStackTrace();
        }

    }
}
