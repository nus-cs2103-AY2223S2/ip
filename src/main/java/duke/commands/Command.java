package duke.commands;

import java.io.PrintStream;
import java.util.function.BiConsumer;

import duke.Duke;
import duke.parser.Arguments;

/**
 * Class representing a command that Duke can execute. Override
 * the execute method to provide the functionality of the command
 */
public abstract class Command implements BiConsumer<Arguments, Duke> {
    /**
     * Represents errors in the user's input. Throw this exception within {@link #execute() execute}
     * with the given message to indicate that the user has entered invalid arguments for 
     * the given command.
     */
    protected static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }

        public ValidationException(String format, Object ...args) {
            this(String.format(format, args));
        }
    }

    /**
     * Throw {@link #ValidationException ValidationException} if the provided condition is false. 
     * @param condition Condition to check
     * @param errMsg Error message to return to the user
     * @throws ValidationException
     */
    protected static void validate(boolean condition, String errMsg) throws ValidationException {
        if (!condition) {
            throw new ValidationException(errMsg);
        }
    }

    /**
     * The label used to identify the command, i.e. deadline in 'deadline book /by 2pm'
     */
    private final String label;

    private PrintStream outputFunc;

    public Command setOutputStream(PrintStream outputFunc) {
        this.outputFunc = outputFunc;
        return this;
    }

    public Command(String label, PrintStream outputFunc) {
        this.label = label;
        this.outputFunc = outputFunc;
    }

    public Command(String label) {
        this(label, System.out);
    }

    /**
     * Output the given string to the desired location
     * @param str String to output
     */
    protected void output(String str) {
        outputFunc.println(str);
    }

    /**
     * Format the given string using the given arguments, and then send it to the output function
     * @param formatStr Format string to use
     * @param args Format string arguments
     */
    protected void output(String formatStr, Object ...args) {
        output(String.format(formatStr, args));
    }

    /**
     * The label of the command
     */
    public String getLabel() { 
        return label;
    }

    /**
     * Run the command with the given arguments and Duke instance. Override this function
     * to provide implementations for commands
     * @param tokens Array of parsed tokens, including the command label
     * @param instance Instance of Duke to run the command with
     * @throws ValidationException
     */
    protected abstract void executeInternal(Arguments args, final Duke instance) throws ValidationException;

    /**
     * Execute the command using the given arguments and Duke instance
     * @param args Parsed arguments object
     * @param instance Duke instance to use
     */
    public void execute(Arguments args, final Duke instance) {
        try {
            executeInternal(args, instance);
        } catch (ValidationException e) {
            output(e.getMessage());
        }
    }

    @Override
    public final void accept(Arguments args, final Duke instance) {
        execute(args, instance);
    }
}
