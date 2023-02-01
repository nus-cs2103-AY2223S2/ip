package command;

import io.Storage;
import io.Ui;
import task.TaskList;
import util.Parser;

public class Mark implements Command {
    private final int taskNum;
    private static final String ERROR = "Task not in list.";
    private static final String SUCCESS = "Task has been marked.";
    private static final String FORMAT = "mark 'task number'";

    private Mark(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Storage<TaskList> storage) {
        if (this.taskNum < 1 || this.taskNum > taskList.size()) {
            Ui.showReply(ERROR);
            return;
        }
        taskList.markTask(this.taskNum);
        Ui.showReply(SUCCESS + taskList.get(this.taskNum));
    }

    public static Parser<Command> parser() {
        return Parser.strParserIgnoreCase("mark")
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.decimal())
                .<Command>map(x -> new Mark(x))
                .overrideMsg(FORMAT);
    }
}
