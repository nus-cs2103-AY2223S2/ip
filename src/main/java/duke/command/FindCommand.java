package duke.command;

import duke.DukeResponse;
import duke.MessageGenerator;
import duke.Task;
import duke.TaskList;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;
    private final TaskList taskList;

    public FindCommand(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public DukeResponse execute() {
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
