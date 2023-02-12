package vic.commands;

import vic.exceptions.DukeException;
import vic.tasks.ITask;
import vic.utilities.Parser;

/**
 * Represents list tasks action command. A <code>ListTasks</code> object corresponds to
 * the action to list all task in the program
 */
public class ListTasks extends ICommand {

    public ListTasks(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        int i = 1;
        StringBuilder sentence = new StringBuilder("You have total ");
        sentence.append(getParser()
                .getTaskManager().size()).append(" tasks in the list.\n");
        for (ITask item : getParser().getTaskManager().getTasks()) {
            sentence.append(i).append(".").append(item).append("\n");
            i++;
        }

        setMsg(sentence.toString());
        return false;

    }
}
