package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    protected String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Got it, I've added this task:");
        taskList.addTodo(desc);
        storage.save(taskList);
    }

}
