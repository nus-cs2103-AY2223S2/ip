package duke.command;

import duke.DukeException;
import duke.FormatChecker;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command {
    private String content;

    public EventCommand(String content) {
        this.content = content;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!FormatChecker.isCorrectEventCmd(this.content)) {
            throw new DukeException(
                "Please use the correct format to add an event.");
        }
        String res = "";
        String[] eventTask = content.split("/from|/to");
        eventTask[0] = eventTask[0].trim();
        eventTask[1] = eventTask[1].trim();
        eventTask[2] = eventTask[2].trim();
        if (!FormatChecker.isCorrectDateInput(eventTask[1]) || !FormatChecker.isCorrectDateInput(eventTask[2])) {
            throw new DukeException("Please use the correct format for date (dd/MM/yyyy HH:mm)");
        }
        try {
            res += tasks.add(eventTask[0], eventTask[1], eventTask[2]);
        } catch (DukeException e) {
            throw e;
        }
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
