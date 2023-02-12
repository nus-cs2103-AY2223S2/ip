package peppa.commands;

import peppa.PeppaException;
import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

public class FilesCommand implements Command {
    public static final String COMMAND_WORD = "files";

    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        String response = Ui.getDataSources(storage.getDataSources());
        if (storage.getFile() != null) {
            return response;
        } else {
            return response + Ui.getLoadFileMessage();
        }
    }
    public boolean isExit() {
        return false;
    }
}
