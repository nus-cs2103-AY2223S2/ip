package command;

import exception.DukeIllegalArgumentException;
import task.ToDo;


public class ToDoTaskCreator implements TaskCreator {
    @Override
    public ToDo apply(CommandInput input) throws DukeIllegalArgumentException {
        return input.getMainInput()
            .map(name -> new ToDo(name))
            .orElseThrow(() -> new DukeIllegalArgumentException("Missing task name"));
    }
}
