package dukeexceptions;

/**
 * An exception thrown when an invalid command is created or issued by the user
 * @author clydelhui
 */
public class IllegalCommandException extends DukeException {
    public IllegalCommandException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Sorry, I do not understand your command." + getMessage();
    }
}
