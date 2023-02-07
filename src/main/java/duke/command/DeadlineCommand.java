package duke.command;

import duke.DukeException;
import duke.FormatChecker;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeadlineCommand extends Command {
    private String content;

    public DeadlineCommand(String content) {
        this.content = content;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!FormatChecker.isCorrectDeadlineCmd(this.content)) {
            throw new DukeException(
                    "Please use the correct format to add a deadline.");
        }
        String res = "";
        String[] dlTask = content.split("/by");
        dlTask[0] = dlTask[0].trim();
        dlTask[1] = dlTask[1].trim();
        if (!FormatChecker.isCorrectDateInput(dlTask[1])) {
            throw new DukeException("Please use the correct format for date (dd/MM/yyyy HH:mm)");
        }
        try {
            res = tasks.add(dlTask[0], dlTask[1]);
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
