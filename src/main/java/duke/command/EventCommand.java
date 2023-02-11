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
        assert FormatChecker.isCorrectEventCmd(this.content) == true
                : "Please use the correct format to add an event.";
        String res = "";
        String[] eventTask = content.split("/from|/to");
        String title = eventTask[0].trim();
        String fromDateTime = eventTask[1].trim();
        String toDateTime = eventTask[2].trim();
        assert FormatChecker.isCorrectDateInput(fromDateTime) == true
                && !FormatChecker.isCorrectDateInput(toDateTime) == true
                : "Please use the correct format for date (dd/MM/yyyy HH:mm)";
        try {
            res += tasks.add(title, fromDateTime, toDateTime);
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
