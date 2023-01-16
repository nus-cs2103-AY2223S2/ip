package tasks;

import exceptions.DukeException;
import exceptions.IncompleteCommandException;

public class Event extends ITask {
    private static String[] parser(String input) throws DukeException {
        if (!input.contains("/from")) {
            throw new IncompleteCommandException(convertTaskTypeEnumToStr(TaskTypes.Events), "/from");
        }
        String[] result = new String[3];
        String[] temp = input.split("/from");
        result[0] = temp[0];
        String[] temp2 = temp[1].split("/to");

        if (!input.contains("/to")) {
            throw new IncompleteCommandException(convertTaskTypeEnumToStr(TaskTypes.Events), "/to");
        }
        result[1] = temp2[0];
        result[2] = temp2[1];
        return result;
    }

    private final String from;
    private final String to;

    public Event(String description) throws DukeException {
        super(parser(description)[0], TaskTypes.Events);
        String[] temp = parser(description);
        this.from = temp[1];
        this.to = temp[2];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + "to: " + this.to + ")";
    }
}
