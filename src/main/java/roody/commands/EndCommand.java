package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.tasks.Task;

/**
 * Represents a command to end the chat.
 */
public class EndCommand extends Command {
    /**
     * Creates an end command.
     */
    public EndCommand() {}
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) {
        storage.saveFile(taskList);
        return ui.bye();
    }
}
