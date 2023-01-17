package command;

import java.util.function.Function;

import task.Task;


public class AddTaskFunc implements Function<CommandInput, String> {
    private final Function<CommandInput, Task> taskCreator;

    public AddTaskFunc(Function<CommandInput, Task> taskCreator) {
        this.taskCreator = taskCreator;
    }


    @Override
    public String apply(CommandInput input) throws IllegalArgumentException {
        Task task = taskCreator.apply(input);
        input.getMainManager().getTaskManager().add(task);
        return String.format("Added: %s", task.toString());
    }
}
