package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class StatisticsCommand extends Command {

    public StatisticsCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        return ui.printStatistics(taskList);
    }
}
