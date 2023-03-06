package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command to mark a task as done at the index
 */
public class MarkCommand extends Command {
    private final String index;

    /**
     * Returns a MarkCommand with the index stored
     *
     * @param index Integer as String
     */
    public MarkCommand(String index) {
        super("mark");
        this.index = index;
    }
    /**
     * Marks the task as done at the target index from TaskList
     * Display the output via Ui showing the updated marked task
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param ui Ui the user interface to interact with the user
     * @param storage Storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if the String index is not an integer OR if index is not in range of size of TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException(Command.EMPTY_LIST_MESSAGE);
        }
        try {
            int idx = Integer.parseInt(this.index);
            tasks.get(idx).setDone();
            ui.print(output(tasks.get(idx).toString()));
            storage.saveList(tasks);
        } catch (NumberFormatException notANumber) {
            throw new DukeException(Command.INVALID_NUMBER_MESSAGE);
        }
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException(Command.EMPTY_LIST_MESSAGE);
        }
        try {
            int idx = Integer.parseInt(this.index);
            tasks.get(idx).setDone();
            storage.saveList(tasks);
            return output(tasks.get(idx).toString());
        } catch (NumberFormatException notANumber) {
            throw new DukeException(Command.INVALID_NUMBER_MESSAGE);
        }
    }

    private String output(String taskString) {
        return String.format("Nice! I've marked this task as done: \n\t%s", taskString);
    }
}
