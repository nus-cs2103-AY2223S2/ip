package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import util.Util;

/**
 * Manages the adding of tasks into the taskList
 */
public class Add implements Command {
    private static final String SUCCESS = "Task added: %s";
    private static final String TODO_FORMAT = "todo 'description'";
    private static final String DEADLINE_FORMAT = "deadline 'description' /by 'YYYY-MM-DD'";
    private static final String EVENT_FORMAT = "event 'description' /from 'YYYY-MM-DD' /to 'YYYY-MM-DD'";

    private final Task task;

    private Add(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        taskList.addTask(this.task);
        ui.showReply(String.format(SUCCESS, taskList.getLast()));
    }

    /**
     * @return Parser that can parse todo tasks.
     * @see Parser
     * @see Todo
     */
    private static Parser<Task> todoParser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("todo"))
                .thenIgnore(Parser.skipSpace())
                .ignoreThen(Parser.nextLine())
                .map(Util::cleanup)
                .<Task>bind(s -> Parser.retn(new Todo(s)))
                .overrideMsg(TODO_FORMAT);
    }

    /**
     * @return Parser that can parse deadline tasks.
     * @see Parser
     * @see Deadline
     */
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

    /**
     * @return Parser that can parse event tasks.
     * @see Parser
     * @see Event
     */
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

    /**
     * @return Parser that can parse the add command.
     * @see Parser
     * @see Add
     */
    public static Parser<Command> parser() {
        return todoParser()
                .or(deadlineParser())
                .or(eventParser())
                .map(Add::new);
    }
}
