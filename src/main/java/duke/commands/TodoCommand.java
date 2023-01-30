package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a todo command.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    protected String desc;

    /**
     * A constructor to initialize a todo command.
     *
     * @param desc The description of the todo task.
     */
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
