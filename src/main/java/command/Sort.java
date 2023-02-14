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
    public static final String ERROR = "You have no tasks to sort.";
    public static final String SUCCESS = "Tasks sorted by date. Current tasks: %s";

    private Sort() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        if (taskList.isEmpty()) {
            ui.showError(ERROR);
        }
        taskList.sort();
        ui.showReply(String.format(SUCCESS, taskList.toString()));
    }

    /**
     * @return Parser that can parse sort command.
     */
    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("sort"))
                .<Command>map(str -> new Sort())
                .overrideMsg(FORMAT);
    }
}
