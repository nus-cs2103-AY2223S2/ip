package duke;

import duke.Command.ByeCommand;
import duke.Command.Command;
import duke.Command.DeadlineCommand;
import duke.Command.DeleteCommand;
import duke.Command.EventCommand;
import duke.Command.ListCommand;
import duke.Command.MarkCommand;
import duke.Command.ToDoCommand;
import duke.Command.UnmarkCommand;

import duke.Exception.DukeException;
import duke.Exception.IncorrectArgumentsException;
import duke.Exception.InvalidArgumentsException;
import duke.Exception.InvalidInputException;

public class Parser {

    public Command parseCommand(String command) throws DukeException {
        String[] tokens = command.split(" ", 2);

        if (tokens.length == 1) {
            return handleSingleInput(tokens[0]);
        } else {
            return handleMultiInput(tokens[0], tokens[1]);
        }
    }

    public Command handleSingleInput(String command) throws InvalidInputException,
            IncorrectArgumentsException {
        switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
            case "unmark":
            case "delete":
            case "todo":
                throw new IncorrectArgumentsException(command, 1, 0);
            case "deadline":
                throw new IncorrectArgumentsException(command, 2, 0);
            case "event":
                throw new IncorrectArgumentsException(command, 3, 0);
            default:
                throw new InvalidInputException(command);
        }
    }

    public Command handleMultiInput(String command, String details) throws IncorrectArgumentsException,
            InvalidInputException, InvalidArgumentsException {
        switch (command) {
            case "bye":
            case "list":
                throw new IncorrectArgumentsException(command, 0, 1);
            case "mark":
                int numToMark = Integer.parseInt(details);
                return new MarkCommand(numToMark);
            case "unmark":
                int numToUnmark = Integer.parseInt(details);
                return new UnmarkCommand(numToUnmark);
            case "delete":
                int numToDelete = Integer.parseInt(details);
                return new DeleteCommand(numToDelete);
            case "todo":
                return new ToDoCommand(details);
            case "deadline":
                String[] deadlineTokens = details.split("/by");

                if (deadlineTokens.length != 2) {
                    throw new IncorrectArgumentsException("deadline", 2, deadlineTokens.length);
                }

                String DlTaskName = deadlineTokens[0];
                String DlDueTime = deadlineTokens[1];

                return new DeadlineCommand(DlTaskName, DlDueTime);
            case "event":
                String[] eventTokens = details.split("/from");

                if (eventTokens.length != 2) {
                    throw new IncorrectArgumentsException("event", 3, eventTokens.length + 1);
                }

                String[] eventTokens1 = eventTokens[1].split("/to");

                if (eventTokens1.length != 2) {
                    throw new IncorrectArgumentsException("event", 3, eventTokens.length + eventTokens1.length - 1);
                }

                String eTaskName = eventTokens[0];
                String eStartTime = eventTokens1[0];
                String eEndTime = eventTokens1[1];
                return new EventCommand(eTaskName, eStartTime, eEndTime);
            default:
                throw new InvalidInputException(command);
        }
    }
}
