package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public boolean isGoodbye() {
        return true;
    }

    /**
     * Saves existing state of task collection to data file.
     * Returns message generated from saving Tasklist state.
     *
     * @param tasks Tasklist object.
     * @param storage Storage object.
     * @param ui Ui Object.
     * @return Messages from saving TaskList instance.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String output = "";
        output += ui.uploading();
        output += storage.save(tasks);
        output += ui.goodbye();
        ui.close();
        return output;
    }
}
