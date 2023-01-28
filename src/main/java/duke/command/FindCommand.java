package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;

import java.time.format.DateTimeParseException;

public class FindCommand extends Command {

    private final String keyword;
    private final TaskList taskList;
    private final Ui ui;

    public FindCommand(String command, TaskList taskList, Ui ui) throws DukeException {
        this.keyword = getKeyword(command);
        this.taskList = taskList;
        this.ui = ui;
    }

    /*
     * adds deadline base on the string command
     * deadline requires taskName and EndDate
     */
    @Override
    public boolean execute() throws DukeException {
        TaskList foundList = new TaskList();

        for (Task task : taskList) {
            if (task.containsKeyword(keyword)) {
                foundList.add(task);
            }
        }

        if (foundList.isEmpty()) {
            ui.printNoTaskWithKeywordFound(keyword);
        } else {
            ui.printFoundList(foundList);
        }

        return false;
    }
}
