package duke.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.DukeException;

public class Parser {
    protected static final String INPUT_DATE_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String LIST = "list";

    private Command parseAddCommand(String taskToAdd) {
        Command command;
        return taskToAdd;
    }

    private Command parseInput(String userInput) {
        Command command;
        String inputCommand = userInput.split(" ", 2)[0];

        switch (inputCommand) {
        case ByeCommand.COMMAND:
            command = new ByeCommand();
            break;
        case ListCommand.COMMAND:
            command = new ListCommand();
            break;
        case AddCommand.COMMAND:
            command = new AddCommand();
            break;
        case MarkCommand.COMMAND:
            command = new MarkCommand(userInput.split(" "));
            break;
        case UnmarkCommand.COMMAND:
            command = new UnmarkCommand(userInput.split(" "));;
        }
    }
}
