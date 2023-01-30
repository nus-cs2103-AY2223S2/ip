package command;

import exception.DukeException;
import main.TaskList;
import main.Ui;
import main.Storage;

public class FindCommand implements Command {
    String input;

    public FindCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        TaskList result = list.find(input);
        ui.list(result);
        storage.save(list);
    }

    public boolean isExit() {
        return false;
    }
}
