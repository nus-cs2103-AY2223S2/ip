package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList);
        }
        storage.save(taskList);
    }
}
