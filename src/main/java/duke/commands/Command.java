package duke.commands;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import duke.Duke;

/**
 * Class representing a command that Duke can execute. Override
 * the execute method to provide the functionality of the command
 */
public abstract class Command implements BiConsumer<String[], Duke> {
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

    private Consumer<String> outputFunc;

    public Command setOutputFunc(Consumer<String> outputFunc) {
        this.outputFunc = outputFunc;
        return this;
    }

    public Command(String label, Consumer<String> outputFunc) {
        this.label = label;
        this.outputFunc = outputFunc;
    }

    public Command(String label) {
        this(label, System.out::println);
    }

    /**
     * Output the given string to the desired location
     * @param str String to output
     */
    protected void output(String str) {
        outputFunc.accept(str);
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
     * Run the command with the given arguments and Duke instance
     * @param tokens Array of parsed tokens, including the command label
     * @param instance Instance of Duke to run the command with
     * @throws ValidationException
     */
    protected abstract void execute(String[] tokens, final Duke instance) throws ValidationException;

    @Override
    public final void accept(String[] tokens, final Duke instance) {
        try {
            execute(tokens, instance);
        } catch (ValidationException e) {
            output(e.getMessage());
        }
    }
}
