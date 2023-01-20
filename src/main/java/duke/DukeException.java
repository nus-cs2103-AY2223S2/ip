package duke;

/** DukeException is the custom error class that is thrown by other classes
 * if the user inputs an illegal argument present in the blacklist. It
 * extends IllegalArgumentException since an invalid blacklisted input is
 * indeed an argument that is not allowed. 
 *
 * @author Muhammad Reyaaz
 * @version %I% %G% 
 * @since 11
 * @see IllegalArgumentException
 */
class DukeException extends IllegalArgumentException {
    

    /**
    * Sole constructor. (For invocation by subclass
    * constructors, typically implicit)
    */
    
    protected DukeException() {

    }
    
    /**
     * Allows the DukeException to take in a custom error message and call
     * the parent's class constructor
     *
     * @param message The error message to be displayed in String
     */

    DukeException(String message) {
        super(message + " Cannot be empty");
    }

    @Override
    public String toString() {
        return String.format("Illegal Argument. %s cannot be empty", getMessage());
    }
}
