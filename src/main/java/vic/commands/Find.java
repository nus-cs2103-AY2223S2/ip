package vic.commands;

import vic.exceptions.DukeException;
import vic.utilities.Parser;

/**
 * Represents Find action command.
 * A <code>Find</code> object corresponds to
 * the action find from a task
 */
public class Find extends ICommand {
    public Find(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {
        getParser().forFind();
        Object[] tasks = getParser().getTaskManager().find(getParser().getDescription());
        StringBuilder result = new StringBuilder();
        for (Object task : tasks) {
            result.append(getParser().getTaskManager().getObjectIndex(task) + 1)
                    .append(".").append(task.toString());
            result.append("\n");
        }
        setMsg(result.toString());
        return false;
    }
}
