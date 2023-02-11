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
        assert FormatChecker.isCorrectDeadlineCmd(content) == true
                : "Please use the correct format to add a deadline.";
        String res = "";
        String[] dlTask = content.split("/by");
        String title = dlTask[0].trim();
        String deadline = dlTask[1].trim();
        assert FormatChecker.isCorrectDateInput(dlTask[1]) == true
                : "Please use the correct format for date (dd/MM/yyyy HH:mm)";
        try {
            res = tasks.add(title, deadline);
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
