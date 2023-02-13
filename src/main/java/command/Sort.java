package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Manages sorting of tasks
 */
public class Sort implements Command {
    public static final String FORMAT = "sort";

    private Sort() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        taskList.sort();
        ui.showReply(String.format("Tasks sorted by date. Current tasks: %s", taskList.toString()));
    }

    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("sort"))
                .<Command>map(str -> new Sort())
                .overrideMsg(FORMAT);
    }
}
