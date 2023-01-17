package command;

import java.util.function.Function;

import task.Task;
import task.ToDo;


public class ToDoTaskCreator implements Function<CommandInput, Task> {
    @Override
    public ToDo apply(CommandInput input) {
        return input.getMainInput()
            .map(name -> new ToDo(name))
            .orElseThrow(() -> new IllegalArgumentException("Missing task name"));
    }
}
