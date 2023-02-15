package command;

import java.time.LocalDate;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Manages finding taks by date
 */
public class FindByDate implements Command {
    private static final String FORMAT = "findbydate 'YYYY-MM-DD'";
    
    private final LocalDate date;

    private FindByDate(LocalDate date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        ui.showReply(taskList.findByDate(this.date));
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
                .<Command>map(d -> new FindByDate(d))
                .overrideMsg(FORMAT);
    }
}
