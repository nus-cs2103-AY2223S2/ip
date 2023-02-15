package elise.commands;

import java.io.IOException;

import elise.internal.Storage;
import elise.internal.TaskList;
import elise.internal.Ui;
import elise.tasks.Task;

/**
 * Command which creates a task
 */
public class CreateCommand implements Command {

    private final int code;
    private final String[] content;

    /**
     * Constructor for Command of specified code and message.
     *
     * @param code unique code for type of command.
     * @param content String array of content for command.
     */
    public CreateCommand(int code, String[] content) {
        this.code = code;
        this.content = content;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws IOException {
        Task t = taskList.addTask(code, content);
        storage.write();
        return ui.addMessage(t, taskList);
    }

}
