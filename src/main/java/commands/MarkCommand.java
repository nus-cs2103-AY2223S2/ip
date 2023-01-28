package commands;

import components.Storage;
import components.TaskList;
import components.Ui;
import exceptions.DukeException;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private int markIndex;
    public MarkCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        if (tokens.size()>2 || tokens.size()==1) {
            throw new DukeException(
                    "Invalid input received! \n" +
                    "Mark commands are in the form of: mark i \n" +
                    "(only 1 whitespace allowed)");
        }
        int index = Integer.parseInt(tokens.get(1));
        markIndex = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (markIndex < 1 || markIndex > tasks.size()){
            throw new DukeException("index " + markIndex +" not in range!");
        }
        tasks.mark(markIndex);
        ui.showMarkCompletion(this, tasks.getTask(markIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
