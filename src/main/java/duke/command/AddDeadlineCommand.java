package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * A command that stores the command to add a new deadline task. The action of adding the task can be carried out
 * when called.
 */
public class AddDeadlineCommand extends Command {
    /**
     * The description and due date stored as string representation.
     */
    private final String DATA;

    /**
     * Constructor for a command to add new deadline task.
     *
     * @param commandString  The add deadline command in string representation
     * @param DATA The description and due date of the deadline task
     */
    public AddDeadlineCommand(String commandString, String DATA) {
        super(Commands.ADD_DEADLINE, commandString);
        this.DATA = DATA;
    }

    /**
     * Adds a new deadline task into the task list.
     * The due date is filtered out. If it exists, then a deadline task will be created. Otherwise, an exception
     * would be thrown stating that a due date was not specified.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] splitData = this.DATA.split(" /by ", 2);
        if (splitData.length < 2) {
            throw new DukeException("Deadline command format error. Missing /by");
        }

        Task deadline = new Deadline(splitData[0], splitData[1]);
        tasks.addTask(deadline);

        ui.showAddTask(deadline.toString(), tasks.size());
    }
}
