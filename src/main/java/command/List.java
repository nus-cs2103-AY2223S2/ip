package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Manages listing of tasks.
 */
public class List implements Command {
    private static final String FORMAT = "list";

    /**
     * @return Parser that can parse the bye command.
     * @see Parser
     */
    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("list"))
                .<Command>map(ok -> new List())
                .overrideMsg(FORMAT);
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        ui.showReply("Tasks: " + taskList.toString());
    }
}
