package command;

import java.util.List;
import java.util.stream.Collectors;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Manages finding tasks via keywords
 */
public class Find implements Command {
    private static final String FORMAT = "find 'keywords'";
    private static final String SUCCESS = "Tasks found: %s";

    final List<String> keywords;

    private Find(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        ui.showReply(String.format(SUCCESS, taskList.findByKeywords(this.keywords)));
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
}
