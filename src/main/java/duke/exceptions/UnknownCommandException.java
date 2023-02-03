package duke.exceptions;

/**
 * An exception thrown by the Duke chatbot whenever an unknown command is provided.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class UnknownCommandException extends DukeException {
    private String command;

    /**
     * Constructor for UnknownCommandException.
     * @param command The invalid command that was provided.
     */
    public UnknownCommandException(String command) {
        super(String.format("I'm sorry, but I don't know what <%s> means :-(", command));
        this.command = command;
    }
}
