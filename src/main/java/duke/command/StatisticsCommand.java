package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * StatisticsCommand returns user insights and statistics.
 */
public class StatisticsCommand extends Command {

    /**
     * Creates a StatisticsCommand.
     *
     * @param textCmd user input.
     */
    public StatisticsCommand(String textCmd) {
        super(textCmd);
    }

    /**
     * Returns user statistics and insights.
     *
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @param taskList List of tasks.
     * @return String output to be displayed by Chat bot.
     * @throws DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        return ui.printStatistics(taskList);
    }
}
