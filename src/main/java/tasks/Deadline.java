package tasks;

public class Deadline extends ITask {
    private final String _by;

    public Deadline(String description, String by) {
        super(description);
        _by = by;
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description,isDone);
        _by = by;
    }
    @Override
    public String toSaveFormat() {
        return "[D] " + "/by: " + _by + " /content: " + super.toString() ;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this._by + ")";
    }
}
