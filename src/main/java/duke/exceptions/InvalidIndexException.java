package duke.exceptions;

/**
 * An exception thrown by the Duke chatbot whenever an invalid index is provided.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException(int idx) {
        super(String.format("Index of %d is out of bounds!", idx));
    }
}
