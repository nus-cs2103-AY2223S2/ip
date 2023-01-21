package command;

import aqua.exception.IllegalSyntaxException;
import task.ToDo;


public class ToDoTaskCreator implements TaskCreator {
    @Override
    public ToDo apply(CommandInput input) throws IllegalSyntaxException {
        return input.getMainInput()
            .map(name -> new ToDo(name))
            .orElseThrow(() -> new IllegalSyntaxException("Missing task name"));
    }
}
