package command;

import io.Storage;
import io.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import util.Parser;
import util.Util;

public class Add implements Command {
    private final Task task;

    private static final String TODO_FORMAT = "todo 'description'";

    private static final String DEADLINE_FORMAT = "deadline 'description' /by 'YYYY-MM-DD'";

    private static final String EVENT_FORMAT = "event 'description' /from 'YYYY-MM-DD' /to 'YYYY-MM-DD'";

    private Add(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage<TaskList> storage) {
        taskList.addTask(this.task);
        Ui.showReply(taskList.getLast());
    }

    private static Parser<Task> todoParser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("todo"))
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.nextLine())
                .map(s -> Util.cleanup(s))
                .<Task>bind(s -> Parser.retn(new Todo(s)))
                .overrideMsg(TODO_FORMAT);
    }

    private static Parser<Task> deadlineParser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("deadline"))
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.strUntil(Parser.strParserIgnoreCase("/by")))
                .thenIgnore(Parser.skipSpace())
                .<Task>bind(s -> Parser.dateParser().bind(
                        d -> Parser.retn(new Deadline(s, d))))
                .overrideMsg(DEADLINE_FORMAT);
    }

    private static Parser<Task> eventParser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("event"))
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.strUntil(Parser.strParserIgnoreCase("/from")))
                .thenIgnore(Parser.skipSpace())
                .<Task>bind(s -> Parser.dateParser().thenIgnore(Parser.skipSpace())
                        .thenIgnore(Parser.strParserIgnoreCase("/to"))
                        .thenIgnore(Parser.skipSpace()).bind(
                                df -> Parser.dateParser().bind(
                                        dt -> Parser.retn(new Event(s, df, dt)))))
                .overrideMsg(EVENT_FORMAT);
    }

    public static Parser<Command> parser() {
        return todoParser()
                .or(deadlineParser())
                .or(eventParser())
                .map(t -> new Add(t));
    }
}
