package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    public String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) {
        try {
            s.save(l);
        } catch (IOException e) {
            return ui.showError(e); //might wanna chg to showExit(e)
        }
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
