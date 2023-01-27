package duke.utility.ui;

import duke.tasklist.task_types.Task;
import duke.utility.parser.CommandMap;

public class UiMessage extends Ui {
    public final CommandMap TYPE;
    protected Task task;

    public UiMessage() {
        TYPE = CommandMap.todo;
    }

    public UiMessage(CommandMap type, Task task) {
        this.TYPE = type;
        this.task = task;
    }
}
