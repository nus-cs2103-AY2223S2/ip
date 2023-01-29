package duke.command;

import duke.tasklist.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.listTasks();
    }

}
