package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

import java.time.LocalDate;

/**
 * Manages finding tasks by date
 */
public class FindByDate implements Command {
    private static final String FORMAT = "findbydate 'YYYY-MM-DD'";

    private final LocalDate date;

    private FindByDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return Parser that can parse the findbydate command.
     * @see Parser
     */
    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("findbydate"))
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
