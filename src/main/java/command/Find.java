package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages finding tasks via keywords
 */
public class Find implements Command {
    private static final String FORMAT = "find 'keywords'";
    public static final String HELP_MSG = "Finds tasks from list with matching keywords.\nFormat: " +
            FORMAT;
    private static final String SUCCESS = "Tasks found: %s";
    final List<String> keywords;

    private Find(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * @return Parser that parses find command input
     */
    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("find"))
                .ignoreThen(Parser.nextStr().many())
                .map(lst -> lst.stream().map(String::toLowerCase).collect(Collectors.toList()))
                .<Command>map(Find::new)
                .overrideMsg(FORMAT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        ui.showReply(String.format(SUCCESS, taskList.findByKeywords(this.keywords)));
    }
}
