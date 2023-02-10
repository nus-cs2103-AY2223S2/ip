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
        String undoMsg = "";
        try {
            oldTaskList = s.load();
            duke.setTaskList(oldTaskList);
            undoMsg = ui.showList(oldTaskList);
        } catch (FileNotFoundException | IncorrectFileFormatException e) {
            errorMsg = "Unable to undo.\n" + ui.showError(e);
        }
        return ui.showFullReplyMsg(errorMsg, undoMsg);
    }
}
