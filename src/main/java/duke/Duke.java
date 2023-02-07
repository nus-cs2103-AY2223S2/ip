package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.commands.Parser;
import duke.commands.SaveCommand;
import duke.commands.indexedCommand.DeleteCommand;
import duke.commands.indexedCommand.MarkCommand;
import duke.commands.indexedCommand.UnmarkCommand;
import duke.commands.taskCommand.DeadlineCommand;
import duke.commands.taskCommand.EventCommand;
import duke.commands.taskCommand.TodoCommand;
import duke.task.Task;
import duke.task.TaskList;

public class Duke {
    private final TaskList tasks;

    private final Parser commands;

    public Duke(List<Task> tasks) {
        this.tasks = TaskList.fromIterable(tasks);
        this.commands = new Parser(Stream.<Supplier<Command>>of(
            ListCommand::new,
            MarkCommand::new,
            UnmarkCommand::new,
            TodoCommand::new,
            DeadlineCommand::new,
            EventCommand::new,
            DeleteCommand::new,
            SaveCommand::new
        ).map(Supplier::get));
    }

    public Duke() {
        this(new ArrayList<>());
    }

    public final TaskList getTaskList() { 
        return tasks;
    }
}