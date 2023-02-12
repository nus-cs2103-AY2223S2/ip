package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;

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
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (markIndex < 1 || markIndex > tasks.size()){
            throw new DukeException("index " + markIndex +" not in range!");
        }
        tasks.mark(markIndex);
        return "Nice! I've marked this task as done:\n" + tasks.getTask(markIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
