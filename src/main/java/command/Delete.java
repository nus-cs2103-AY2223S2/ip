package command;

import io.Storage;
import io.Ui;
import task.TaskList;
import util.Parser;

public class Delete implements Command {
    private final int taskNum;

    private static final String ERROR = "Task not in list.";
    private static final String SUCCESS = "Task has been deleted: %d\nTasks remaining: %s";
    private static final String FORMAT = "delete 'task number'";

    private Delete(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Storage<TaskList> storage) {
        if (this.taskNum > taskList.size()) {
            Ui.showReply(ERROR);
        }
        taskList.deleteTask(this.taskNum);
        Ui.showReply(String.format(SUCCESS, this.taskNum, taskList.toString()));
    }

    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("delete"))
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.decimal())
                .<Command>map(x -> new Delete(x))
                .overrideMsg(FORMAT);
    }
}
