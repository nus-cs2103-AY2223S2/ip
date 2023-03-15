package jeo.command;

import jeo.database.TaskList;
import jeo.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.exitMessage();
    }

    @Override
    public String toString() {
        return "bye";
    }
}
