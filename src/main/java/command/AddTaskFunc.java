package command;

import java.util.function.Function;

import task.Task;


public class AddTaskFunc implements Function<CommandInput, String> {
    private final Function<CommandInput, ? extends Task> taskCreator;

    public AddTaskFunc(Function<CommandInput, ? extends Task> taskCreator) {
        this.taskCreator = taskCreator;
    }


    @Override
    public String apply(CommandInput input) throws IllegalArgumentException {
        Task task = taskCreator.apply(input);
        input.getMainManager().getTaskManager().add(task);
        return String.format(
            "Got it. I've added this task:\n" +
            "  %s\n" +
            "Now you have %d tasks in the list.",
            task.toString(),
            input.getMainManager().getTaskManager().size()
        );
    }
}
