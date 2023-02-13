package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) throws Exception;

    protected String saveToFile(Storage s, TaskList l, Ui ui, Command prevCommand) { //NEED UI???
        if (prevCommand instanceof FindCommand ||
                prevCommand instanceof ListCommand) {
            return "";
        }

        try {
            s.save(l);
        } catch (IOException e) {
            return ui.showError(e);
        }
        return "";
    }

}
