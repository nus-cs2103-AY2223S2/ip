package duke.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.AddCommand;
import duke.command.ListCommand;
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
        return userInput;
    }
}
