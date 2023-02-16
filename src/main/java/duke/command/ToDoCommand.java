package duke.command;

import duke.Storage;
import duke.TaskList;

public class ToDoCommand extends Command {

    private String taskDesc;

    public ToDoCommand(String commandParams) {
        this.taskDesc = commandParams;
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        tasks.addTask(taskDesc, false);
        storage.save(tasks);
        return tasks.returnNewestTaskAsString();
    }
}
