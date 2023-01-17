package command;

import java.util.function.Function;

import task.Task;


public class AddTaskFunc implements Function<CommandInput, String>{
    @Override
    public String apply(CommandInput input) throws IllegalArgumentException {
        Task task = input.getMainInput()
            .map(name -> new Task(name))
            .orElseThrow(() -> new IllegalArgumentException("Missing task name"));
        input.getMainManager().getTaskManager().add(task);
        return String.format("Added: %s", task.toString());
    }
}
