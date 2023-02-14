package Duke.Exceptions;

/**
 * Exceptions when user inputs delete and mark command
 * without indicating the number of the object task.
 */

public class EmptyOrder extends DukeException {
    private String emptyOrder;

    /**
   * Constructor for Exception when order of
     * mark/unmark/delete is empty.
   */
    public EmptyOrder(String input) {
        this.emptyOrder = new StringBuilder()
            .append("OOPS!!! The order of a ")
            .append(input).append(" cannot be empty.")
            .toString();
    }

    @Override
    public String toString() {
        return emptyOrder;
    }
}
