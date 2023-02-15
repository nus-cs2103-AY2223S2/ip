package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.gui.Ui;

/**
 * DeleteCommand - If user enters the delete command.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor
     * @param index helps identify which item of the list to delete. -1 because
     *         TaskList uses 0-based indexing.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Deletes the given task
     * @return a success message that shows the task being removed and the total
     *         number of tasks remaining in the tasklist
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String message = "Noted. I've removed this task: \n" +
                tasks.get(index) + "\n" +
                ui.showNumberOfListings(tasks.size() - 1);
        tasks.remove(index);
        storage.empty();
        storage.writeFromList(tasks, storage.getFilePath());
        return message;
    }
}
