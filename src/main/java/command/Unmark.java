package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Manages unmarking tasks in the taskList.
 */
public class Unmark implements Command {
    private static final String ERROR = "Task not in list.";
    private static final String SUCCESS = "Task has been unmarked.";
    private static final String FORMAT = "unmark 'task number'";
    public static final String HELP_MSG = "Unmark specific task.\nFormat: " + FORMAT;

    private final int taskNum;

    private Unmark(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * @return Parser that can parse the unmark command.
     * @see Parser
     */
    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("unmark"))
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.decimal())
                .<Command>map(Unmark::new)
                .overrideMsg(FORMAT);
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
        taskList.unmarkTask(this.taskNum);
        ui.showReply(SUCCESS + taskList.get(this.taskNum));
    }
}
