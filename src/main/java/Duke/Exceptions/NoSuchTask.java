package Duke.Exceptions;

/**
 * Exceptions when user try to modify task of index
 * out of the range of current list.
 */

public class NoSuchTask extends DukeException {
    private int index;
    private String noSuchTask;

    /**
   * Constructor for Exception when user
   * try to mark,unmark or delete a task
   * that is not in the list.
   */
    public NoSuchTask(int i) {
        this.index = i + 1;
        this.noSuchTask = "There is no task " + index + " in the list";
    }

    @Override
    public String toString() {
        return noSuchTask;
    }
}
