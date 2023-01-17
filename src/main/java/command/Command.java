package command;

import exception.DukeIllegalArgumentException;

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


    private final CommandFunction commandFunc;

    Command(CommandFunction commandFunc) {
        this.commandFunc = commandFunc;
    }


    public String execute(CommandInput input) throws DukeIllegalArgumentException {
        return commandFunc.apply(input);
    }
}
