package duke;

/*
 * Exception for when command is invalid
 */
public class EmptyDescriptionException extends Exception {
    
    /**
     * Constructs a new EmptyDescriptionException instance.
     * 
     * @param errorMessage Error message to display when exception thrown.
     */
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }

}