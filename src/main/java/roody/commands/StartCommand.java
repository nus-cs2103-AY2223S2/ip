package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.tasks.Task;

/**
 * Represents a start command.
 */
public class StartCommand extends Command {
    /**
     * Creates a start command.
     */
    public StartCommand() {}
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) {
        return ui.greet();
    }
}
