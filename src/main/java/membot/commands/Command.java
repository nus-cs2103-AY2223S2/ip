package membot.commands;

import membot.Membot;
import membot.utils.EmptyInputException;
import membot.utils.InvalidCommandException;
import membot.view.Printable;

/**
 * Represents a command based on the user input. A <code>Command</code> object has its own
 * characteristic based on its <code>execute()</code> function.
 */
public abstract class Command {
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
    public static Command parse(String input, Printable ui, Membot membot)
            throws EmptyInputException, InvalidCommandException {
        if (input.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty");
        }

        String[] inputArr = input.split(" ");

        try {
            switch (CommandType.valueOf(inputArr[0].toUpperCase())) {
            case HELP:
                return new HelpCommand(ui);
            case LIST:
                return new ListCommand(ui);
            case EXIT:
                return new ExitCommand(ui, membot);
            case DONE:
                return new DoneCommand(input, ui);
            case UNDONE:
                return new UndoneCommand(input, ui);
            case DELETE:
                return new DeleteCommand(input, ui);
            case FIND:
                return new FindCommand(input, ui);
            case TODO:
                return new TodoCommand(input, ui);
            case DEADLINE:
                return new DeadlineCommand(input, ui);
            case EVENT:
                return new EventCommand(input, ui);
            case SORT:
                return new SortCommand(input, ui);
            default:
                assert false : "Unknown command";
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(String.format("%s is not a valid command!", inputArr[0]));
        }

        throw new InvalidCommandException(String.format("%s is not a valid command!", inputArr[0]));
    }

    /**
     * Runs a chunk of code depending on the <code>Command</code> type.
     */
    public abstract void execute();
}

