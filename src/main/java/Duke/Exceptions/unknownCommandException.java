
package Duke.Exceptions;

/**
 * Contains a duke exception object when unknown command is imput by user
 */
public class unknownCommandException extends DukeException {

    /**
     * Obtain the string representation of the exception.
     * @return string representation of the exception
     */
    @Override
    public String toString(){
        return  String.format("%s I'm sorry, but I don't know what that means :-(", super.toString());
    }
}
