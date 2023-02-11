package vic.exceptions;
/**
 * A DukeException to check if index for mark or unmark action is valid
 */
public class InvalidMarkInput extends DukeException {
    /**
     * Constructor for InvalidMarkInput
     *
     * @param errorMessage the invalid index
     */
    public InvalidMarkInput(String errorMessage) {
        super("☹ OOPS!!! I'm sorry, you have enter invalid task index " + errorMessage);
    }
}
