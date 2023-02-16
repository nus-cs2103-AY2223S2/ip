package peppa.commands;

import java.io.File;

import peppa.PeppaException;
import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents a select file command.
 */
public class SelectCommand implements Command {
    public static final String COMMAND_WORD = "select";
    private int fileIndex;

    /**
     * Constructs a select file command with the index of the file to be used as the data source.
     * Files are zero-indexed.
     *
     * @param fileIndex Index of the file we wish to select.
     *                  Should be >= 0 and < number of files in the /data directory.
     */
    public SelectCommand(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        if (this.fileIndex >= 0 && this.fileIndex < storage.getDataSources().size()) {
            File file = storage.getDataSources().get(fileIndex);
            storage.setFile(file);
            TaskList newTaskList = new TaskList();
            storage.loadData(newTaskList);
            return Ui.getSelectFileMessage(file);
        } else {
            throw new PeppaException("Boink! Peppa could not find the requested data source. "
                    + "Please enter a valid integer and try again.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
