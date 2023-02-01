package command;

import io.Storage;
import io.Ui;
import task.TaskList;

public class Error implements Command {
    private final String msg;

    private Error(String msg) {
        this.msg = msg;
    }

    public static Error of(String msg) {
        return new Error(msg);
    }

    public void execute(TaskList taskList, Storage<TaskList> storage) {
        Ui.showReply(msg);
    }
}
