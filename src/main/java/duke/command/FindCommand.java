package duke.command;

import java.util.ArrayList;

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
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i != taskList.size(); ++i) {
            Task t = taskList.get(i);
            String desc = t.getDescription();
            if (desc.contains(keyword)) {
                tasksOfInterest.add(t);
                indexes.add(i);
            }
        }

        String responseMessage = "";
        for (int i = 0; i != tasksOfInterest.size(); ++i) {
            responseMessage += (indexes.get(i) + 1) + ". " + tasksOfInterest.get(i).toString() + "\n";
        }

        return new DukeResponse(MessageGenerator.genFindTasksMsg(responseMessage));
    }
}
