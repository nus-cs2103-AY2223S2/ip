package commands;

import utils.EmptyInputException;
import utils.InvalidCommandException;
import view.Printable;

/**
 * Represents a command based on the user input. A <code>Command</code> object has its own
 * characteristic based on its <code>execute()</code> function.
 */
abstract public class Command {
    protected String input;
    protected Printable ui;
    protected boolean isExit = false;

    /**
     * Generates a <code>Command</code> object.
     *
     * @param ui A Printable object used for UI display.
     */
    protected Command(Printable ui) {
        this.ui = ui;
    }

    /**
     * Generates a <code>Command</code> object.
     *
     * @param input User input into the application.
     * @param ui A Printable object used for UI display.
     */
    protected Command(String input, Printable ui) {
        this.input = input;
        this.ui = ui;
    }

    /**
     * Returns the corresponding <code>Command</code> object based on the input.
     *
     * @param input User input into the application.
     * @param ui A <code>Printable</code> object used for UI display.
     * @return The corresponding <code>Command</code> object.
     * @throws EmptyInputException If the user input is empty.
     * @throws InvalidCommandException If the user input does not correspond to any valid <code>Command</code>.
     */
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

    /**
     * Returns true if the <code>Command</code> causes the application to exit, false otherwise.
     *
     * @return True if the <code>Command</code> causes the application to exit, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Runs a chunk of code depending on the <code>Command</code> type.
     */
    abstract public void execute();
}

/**
 * Valid commands that is recognised by Membot.
 */
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
