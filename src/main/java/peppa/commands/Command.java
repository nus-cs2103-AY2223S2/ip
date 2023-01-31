package peppa.commands;

import peppa.PeppaException;
import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

public interface Command {
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException;
    public boolean isExit();
}
