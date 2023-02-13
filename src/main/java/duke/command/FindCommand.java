package duke.command;

import duke.DukeResponse;
import duke.MessageGenerator;
import duke.Task;
import duke.TaskList;

/**
 * Represents a command that when executed returns tasks found.
 */
public class FindCommand extends Command {
    private final String keyword;
    private final TaskList taskList;

    /**
     * Constructs a FindCommand with the given arguments.
     *
     * @param keyword
     * @param taskList
     */
    public FindCommand(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public DukeResponse execute() {
        assert taskList != null;
        assert keyword != null;

        TaskList tasksOfInterest = new TaskList();

        for (Task t : taskList) {
            String desc = t.getDescription();
            if (desc.contains(keyword)) {
                tasksOfInterest.add(t);
            }
        }

        return new DukeResponse(MessageGenerator.genFindTasksMsg(tasksOfInterest.toString()));
    }
}
