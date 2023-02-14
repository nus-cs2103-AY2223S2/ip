package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Manages deletion of tasks from tasks.
 */
public class Delete implements Command {
    private static final String ERROR = "Task not in list.";
    private static final String SUCCESS = "Task %d has been deleted.\nTasks remaining: %s";
    private static final String FORMAT = "delete 'task number'";

    private final int taskNum;

    private Delete(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        if (this.taskNum < 1 || this.taskNum > taskList.size()) {
            ui.showError(ERROR);
            return;
        }
        taskList.deleteTask(this.taskNum);
        ui.showReply(String.format(SUCCESS, this.taskNum, taskList.toString()));
    }

    /**
     * @return Parser that can parse the delete command.
     * @see Parser
     */
    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("delete"))
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.decimal())
                .<Command>map(x -> new Delete(x))
                .overrideMsg(FORMAT);
    }
}
