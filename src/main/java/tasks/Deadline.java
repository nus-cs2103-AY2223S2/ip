package tasks;

public class Deadline extends ITask {
    private final String _by;

    public Deadline(String description , String by) {
        super(description);
        _by =  by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this._by + ")";
    }
}
