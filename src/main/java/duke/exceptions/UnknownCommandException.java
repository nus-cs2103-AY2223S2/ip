package duke.exceptions;

/**
 * Exception to be thrown when command cannot be parsed
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor for an UnknownCommandException.
     */
    public UnknownCommandException() {
        super("WAKANDEYO!!! >:("
                + "\nAvailable commands are:"
                + "\n1. list"
                + "\n2. todo [name]"
                + "\n3. deadline [name] /by [date]"
                + "\n4. event [name] /from [date] /to [date]"
                + "\n5. delete [index]"
                + "\n6. find [keyword]"
                + "\n7. mark [index]"
                + "\n8. unmark [index]"
                + "\n9. bye");
    }
}
