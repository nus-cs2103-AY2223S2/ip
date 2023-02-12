package duke;

/*
 * Exception for when command is invalid
 */
public class InvalidCommandException extends Exception {
    
    /**
     * Constructs a new InvalidCommandException instance.
     * 
     * @param errorMessage Error message to display when exception thrown.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }

}