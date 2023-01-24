import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private HashMap<String, Command> commandMap;

    private boolean hasUserQuit = false;

    public CommandParser() {
        commandMap = new HashMap<>();

        Command todoCommand = new ToDoCommand();
        commandMap.put(todoCommand.getCommandName(), todoCommand);

        Command deadlineCommand = new DeadlineCommand();
        commandMap.put(deadlineCommand.getCommandName(), deadlineCommand);

        Command eventCommand = new EventCommand();
        commandMap.put(eventCommand.getCommandName(), eventCommand);

        Command deleteCommand = new DeleteCommand();
        commandMap.put(deleteCommand.getCommandName(), deleteCommand);

        Command markCommand = new MarkCommand();
        commandMap.put(markCommand.getCommandName(), markCommand);

        Command unmarkCommand = new UnmarkCommand();
        commandMap.put(unmarkCommand.getCommandName(), unmarkCommand);

        Command listCommand = new ListCommand();
        commandMap.put(listCommand.getCommandName(), listCommand);
    }
    public void parseInputAndExecuteCommand(String input, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        String commandName = input.split(" ")[0];

        if (commandName.equals("bye")) {
            ui.printHorizontal();
            ui.printText("Bye. Hope to see you again soon!");
            ui.printHorizontal();

            hasUserQuit = true;
            return;
        }

        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        Matcher commandPatternMatcher = Pattern.compile(command.getCommandRegexPattern()).matcher(input);
        if (!commandPatternMatcher.find()) {
            throw new DukeException(String.format(
                    "I'm sorry, but can you retype again in this format:\n     %s", command.getCommandPattern()));
        }

        String[] args = new String[commandPatternMatcher.groupCount()];
        for (int g = 1; g <= commandPatternMatcher.groupCount(); g++) {
            args[g - 1] = commandPatternMatcher.group(g);
        }

        command.run(args, ui, taskList, storage);
    }

    public boolean hasUserQuit() {
        return hasUserQuit;
    }
}
