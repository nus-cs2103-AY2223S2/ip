package command;

import io.Storage;
import io.Ui;
import task.TaskList;
import util.Parser;

public class List implements Command {
    private static final String FORMAT = "list";

    public void execute(TaskList taskList, Storage<TaskList> storage) {
        Ui.showReply("Tasks: " + taskList.toString());
    }

    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("list"))
                .<Command>map(ok -> new List())
                .overrideMsg(FORMAT);
    }
}
