package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.ITask;
import duke.utilities.Parser;

public class ListTasks extends ICommand {

    public ListTasks(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        int i = 1;
        StringBuilder sentence = new StringBuilder("Here are the duke.tasks in your list:\n");
        for (ITask item : getParser().getTaskManager().getTasks()) {
             sentence.append(i).append(".").append(item).append("\n");
            i++;
        }
        sentence.append("You have total ").append(getParser().getTaskManager().size()).append(" duke.tasks in the list.");
        setMsg(sentence.toString());
        return false;

    }
}
