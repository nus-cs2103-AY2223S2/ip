package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private int unmarkIndex;
    public UnmarkCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        if (tokens.size()>2 || tokens.size()==1) {
            throw new DukeException(
                    "Invalid input received! \n" +
                    "Unmark commands are in the form of: mark i \n" +
                    "(only 1 whitespace allowed)");
        }
        int index = Integer.parseInt(tokens.get(1));

        unmarkIndex = index;
    }

    @Override
    public String execute(TaskList tasks,Storage storage) throws DukeException {
        if (unmarkIndex < 1 || unmarkIndex > tasks.size()){
            throw new DukeException("index " + unmarkIndex +" not in range!");
        }
        tasks.unmark(unmarkIndex);
        return "OK, I've marked this task as not done yet:\n" + tasks.getTask(unmarkIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
