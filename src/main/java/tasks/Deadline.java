package tasks;

import uitilties.Parser;
import java.util.Date;

public class Deadline extends ITask {
    private final Date _by;

    public Deadline(String description , Date by) {
        super(description);
        _by =  by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.outputFormat.format(this._by) + ")";
    }
}
