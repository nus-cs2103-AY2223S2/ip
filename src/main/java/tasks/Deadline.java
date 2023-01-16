package tasks;

import exceptions.DukeException;
import exceptions.IncompleteCommandException;

public class Deadline extends ITask {
    private static String[] parser(String input) throws DukeException {
        if (!input.contains("/b")) {
            throw new IncompleteCommandException(convertTaskTypeEnumToStr(TaskTypes.Deadlines), "/b");
        }

        return input.split("/b");
    }

    private final String _by;

    public Deadline(String description) throws DukeException {
        super(parser(description)[0], TaskTypes.Deadlines);
        _by = parser(description)[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this._by + ")";
    }
}
