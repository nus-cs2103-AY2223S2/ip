package duke;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.SaveCommand;
import duke.commands.indexedcommand.DeleteCommand;
import duke.commands.indexedcommand.MarkCommand;
import duke.commands.indexedcommand.UnmarkCommand;
import duke.commands.taskcommand.DeadlineCommand;
import duke.commands.taskcommand.EventCommand;
import duke.commands.taskcommand.TodoCommand;
import duke.parser.Arguments;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

public class Duke {
    private final TaskList tasks;

    private final Map<String, Command> store;

    private PrintStream outputStream;

    public Duke(List<Task> tasks) {
        this.tasks = TaskList.fromIterable(tasks);
        this.store = Stream.<Supplier<Command>>of(
            DeleteCommand::new,
            MarkCommand::new,
            UnmarkCommand::new,

            DeadlineCommand::new,
            EventCommand::new,
            TodoCommand::new,

            ByeCommand::new,
            FindCommand::new,
            ListCommand::new,
            SaveCommand::new
        ).map(Supplier::get)
        .collect(Collectors.toUnmodifiableMap(
            cmd -> cmd.getLabel(), 
            cmd -> cmd
        ));
    }

    public Duke() {
        this(new ArrayList<>());
    }

    public final TaskList getTaskList() { 
        return tasks;
    }

    /**
     * Try to execute the command as specified by the input string
     * @param input Raw string as entered by the user
     * @param instance Instance of Duke to run the command with
     */
    public void executeCommand(String input) {
        String[] tokens = input.split(" ");
        Command cmd = this.store.getOrDefault(tokens[0].toLowerCase(), null);

        if (cmd != null) {
            Arguments parsed = Parser.parseIntoArguments(tokens);
            cmd.setOutputStream(outputStream);
            cmd.accept(parsed, this);
        } else {
            onUnknownCommand(input);
        }
    }

    /**
     * This method is called every time the user enters an invalid command string
     * @param input Raw string as entered by the user
     * @param instance Instance of Duke to run the command with
     */
    public void onUnknownCommand(String input) {
        output("Unknown command '%s' :(", input);
    }

    public void output(String string) {
        outputStream.format("Duke: %s\n", string);
    }

    public void output(String string, Object ...args) {
        output(String.format(string, args));
    }

    public void setOutputStream(PrintStream stream) {
        this.outputStream = stream;
    }
}