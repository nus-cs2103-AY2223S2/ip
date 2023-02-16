package peppa.commands;

import peppa.PeppaException;
import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents a list files command.
 */
public class FilesCommand implements Command {
    public static final String COMMAND_WORD = "files";

    /**
     * Constructs a list files command.
     */
    public FilesCommand() {

    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        String response = Ui.getDataSources(storage.getDataSources());
        if (storage.getFile() != null) {
            return response;
        } else {
            return response + Ui.getLoadFileMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
