package Duke.Exceptions;

/**
 * Exceptions when user inputs delete and mark command
 * without indicating the number of the object task.
 */

public class EmptyOrder extends DukeException {
  public String emptyOrder;

  public EmptyOrder(String input) {
    this.emptyOrder = "OOPS!!! The order of a "
        + input +  " cannot be empty.";
  }

  @Override
  public String toString() {
    return emptyOrder;
  }
}
