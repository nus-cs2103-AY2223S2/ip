package command;

import java.time.LocalDate;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Manages finding tasks by date
 */
public class FindByDate implements Command {
    private static final String FORMAT = "findbydate 'YYYY-MM-DD'";
    public static final String HELP_MSG = "Finds tasks from list with matching date.\nFormat: "
            + FORMAT;

    private final LocalDate date;

    private FindByDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return Parser that can parse the findbydate command.
     * @see Parser
     */
    public static Parser<Command> parser() {
        return Parser.nextStrIgnoreCase("findbydate")
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.dateParser())
                .<Command>map(FindByDate::new)
                .overrideMsg(FORMAT);
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        ui.showReply(taskList.findByDate(this.date));
    }
}
