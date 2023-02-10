package peppa.commands;

import peppa.*;

import java.io.File;

public class SelectCommand implements Command {
    public static final String COMMAND_WORD = "select";
    private int fileIndex;

    public SelectCommand(int fileIndex) {
        this.fileIndex = fileIndex;
    }
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
    public boolean isExit() {
        return false;
    }
}
