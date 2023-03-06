package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command to mark a task as done at the index
 */
public class SnoozeCommand extends Command {
    private static final String NOT_DEADLINE_MESSAGE = "Please enter the index of a task which is a deadline";
    private final String index;

    /**
     * Returns a MarkCommand with the index stored
     *
     * @param index Integer as String
     */
    public SnoozeCommand(String index) {
        super("snooze");
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
            Task task = tasks.get(idx);
            if (task.isDeadline()) {
                task.snooze();
            } else {
                throw new DukeException(NOT_DEADLINE_MESSAGE);
            }
            ui.print(output(task));
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
            Task task = tasks.get(idx);
            if (task.isDeadline()) {
                task.snooze();
            } else {
                throw new DukeException(NOT_DEADLINE_MESSAGE);
            }
            storage.saveList(tasks);
            return output(task);
        } catch (NumberFormatException notANumber) {
            throw new DukeException(Command.INVALID_NUMBER_MESSAGE);
        }
    }

    private String output(Task task) {
        return String.format("Nice! I've snoozed this task: \n\t%s", task.toString());
    }
}
