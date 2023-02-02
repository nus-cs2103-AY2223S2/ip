package duke.command;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

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

        Command findCommand = new FindCommand();
        commandMap.put(findCommand.getCommandName(), findCommand);
    }

    /**
     * Parses input and execute the respective command with arguments from input.
     *
     * @param input Raw input.
     * @param ui User interface.
     * @param taskList Task list.
     * @param storage Storage.
     * @throws DukeException If command is unknown or does not match the command syntax.
     */
    public void parseInputAndExecuteCommand(String input, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        String commandName = input.split(" ")[0];

        if (commandName.equals("bye")) {
            ui.showLine();
            ui.showText("Bye. Hope to see you again soon!");
            ui.showLine();

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

        command.run(ui, taskList, storage, args);
    }

    /**
     * Returns whether the user has quit.
     *
     * @return Whether the user has quit.
     */
    public boolean hasUserQuit() {
        return hasUserQuit;
    }
}
