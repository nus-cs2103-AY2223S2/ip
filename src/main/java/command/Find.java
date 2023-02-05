package command;

import java.util.List;
import java.util.stream.Collectors;

import io.Storage;
import io.Ui;
import task.TaskList;
import util.Parser;

public class Find implements Command {
    private static final String FORMAT = "find 'keywords'";
    private static final String SUCCESS = "Tasks found: %s";

    final List<String> keywords;

    private Find(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList taskList, Storage<TaskList> storage) {
        Ui.showReply(String.format(SUCCESS, taskList.findByKeywords(this.keywords)));
    }

    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("find"))
                .ignoreThen(Parser.nextStr().many())
                .map(lst -> lst.stream().map(s -> s.toLowerCase()).collect(Collectors.toList()))
                .<Command>map(lst -> new Find(lst))
                .overrideMsg(FORMAT);
    }
}
