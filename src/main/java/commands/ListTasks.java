package commands;

import exceptions.DukeException;
import tasks.ITask;
import utilities.Parser;

public class ListTasks extends ICommand {

    public ListTasks(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {

        int i = 1;
        StringBuilder sentence = new StringBuilder("Here are the tasks in your list:\n");
        for (ITask item : getParser().getTaskManager().getTasks()) {
             sentence.append(i).append(".").append(item).append("\n");
            i++;
        }
        sentence.append("You have total ").append(getParser().getTaskManager().size()).append(" tasks in the list.");
        setMsg(sentence.toString());
        return false;

    }
}
