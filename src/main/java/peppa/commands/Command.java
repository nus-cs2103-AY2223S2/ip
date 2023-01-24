package peppa.commands;

import peppa.PeppaException;
import peppa.TaskList;
import peppa.Ui;
import peppa.Storage;

public interface Command {
    public void execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException;
    public boolean isExit();
}
