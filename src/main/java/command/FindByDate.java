package command;

import java.time.LocalDate;

import io.Storage;
import io.Ui;
import task.TaskList;
import util.Parser;

public class FindByDate implements Command {
    private final LocalDate date;
    private static final String FORMAT = "findbydate 'YYYY-MM-DD'";

    private FindByDate(LocalDate date) {
        this.date = date;
    }

    public void execute(TaskList taskList, Storage<TaskList> storage) {
        Ui.showReply(taskList.findByDate(this.date));
    }

    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("findbydate"))
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.dateParser())
                .<Command>map(d -> new FindByDate(d))
                .overrideMsg(FORMAT);
    }
}
