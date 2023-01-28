package commands;

import components.Storage;
import components.TaskList;
import components.Ui;
import exceptions.DukeException;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(ArrayList<String> tokens) {
        super(tokens);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
