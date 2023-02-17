package duke.exception;

import duke.Duke;

public class DukeSnoozeDateException extends DukeException {
    private static String SNOOZE_MESSAGE = "    OOPS!!! Cannot snooze to an earlier or same timing!";
    public DukeSnoozeDateException() {
        super(SNOOZE_MESSAGE);
    }
}
