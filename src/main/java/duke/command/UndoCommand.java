package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.IncorrectFileFormatException;

import java.io.FileNotFoundException;

public class UndoCommand extends Command{

    public String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) {
        TaskList oldTaskList;
        String errorMsg = "";
        try {
            oldTaskList = s.load();
            duke.setTaskList(oldTaskList);
            return ui.showList(oldTaskList);
        } catch (FileNotFoundException | IncorrectFileFormatException e) {
            return "Unable to undo. " + ui.showError(e);
        }
    }
}
