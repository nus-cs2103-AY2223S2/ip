package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.IncorrectFileFormatException;

import java.io.FileNotFoundException;

public class UndoCommand extends Command{

    public String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) {
        TaskList tl;
        try {
            tl = s.load();
            duke.setTaskList(tl);
        } catch (FileNotFoundException | IncorrectFileFormatException e) {
            return ui.showError(e);
        }
        return ui.showList(tl);
    }
}
