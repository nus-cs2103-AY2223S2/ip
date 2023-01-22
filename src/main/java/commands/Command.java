package commands;

import utils.EmptyInputException;
import utils.InvalidCommandException;
import view.Printable;

abstract public class Command {
    protected String input;
    protected Printable ui;
    protected boolean isExit = false;

    protected Command(Printable ui) {
        this.ui = ui;
    }

    protected Command(String input, Printable ui) {
        this.input = input;
        this.ui = ui;
    }

    public static Command parse(String input, Printable ui) throws EmptyInputException, InvalidCommandException {
        if (input.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty");
        }

        String[] inputArr = input.split(" ");

        try {
            switch (CommandType.valueOf(inputArr[0].toUpperCase())) {
                case LIST:
                    return new ListCommand(ui);
                case BYE:
                    return new ByeCommand(ui);
                case DONE:
                    return new DoneCommand(input, ui);
                case UNDONE:
                    return new UndoneCommand(input, ui);
                case DELETE:
                    return new DeleteCommand(input, ui);
                case TODO:
                    return new TodoCommand(input, ui);
                case DEADLINE:
                    return new DeadlineCommand(input, ui);
                case EVENT:
                    return new EventCommand(input, ui);
                default:
                    // Should not reach here
                    throw new InvalidCommandException(String.format("%s is not a valid command!", inputArr[0]));
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(String.format("%s is not a valid command!", inputArr[0]));
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    abstract public void execute();
}

enum CommandType {
    LIST,
    DONE,
    UNDONE,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    BYE
}
