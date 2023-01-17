package command;

import java.util.function.Function;

public enum Command {
    GREET(input -> "Kon aqua~~"),
    TODO(new AddTaskFunc(new ToDoTaskCreator())),
    DEADLINE(new AddTaskFunc(new DeadlineTaskCreator())),
    EVENT(new AddTaskFunc(new EventTaskCreator())),
    LIST(new ListTaskFunc()),
    MARK(new MarkTaskFunc(true)),
    UNMARK(new MarkTaskFunc(false)),
    DELETE(new DeleteTaskFunc()),
    BYE(input -> "Otsu aqua~~");


    private final Function<CommandInput, String> commandFunc;

    Command(Function<CommandInput, String> commandFunc) {
        this.commandFunc = commandFunc;
    }


    public String execute(CommandInput input) throws IllegalArgumentException {
        return commandFunc.apply(input);
    }
}
