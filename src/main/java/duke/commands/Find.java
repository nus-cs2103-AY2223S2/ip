package duke.commands;

import duke.exceptions.DukeException;
import duke.utilities.Parser;



public class Find extends ICommand{
    public Find(Parser parser) {
        super(parser);
    }

    @Override
    public boolean run() throws DukeException {
        getParser().forFind();
        Object[] tasks =  getParser().getTaskManager().find(getParser().getDescription());
        StringBuilder result = new StringBuilder(); // Arrays.stream(tasks).map(x -> getParser().getTaskManager().getObjectIndex(x));
        for (Object task : tasks) {
             result.append(getParser().getTaskManager().getObjectIndex(task)).append(1).append(".").append(task.toString());
             result.append("\n");
        }
        setMsg(result.toString());
        return false;
    }
}
