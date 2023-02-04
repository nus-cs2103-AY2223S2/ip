package duke.utils;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnmarkCommand;
import duke.enums.CommandType;
import duke.exceptions.DukeException;

/**
 * Parse user input into commands for duke.Duke.
 */

public class Parser {

    /**
     * Parses user input into a command.
     * @param input User input
     * @return Command corresponding to user input
     * @throws DukeException If input is invalid for any command
     */
    public static Command parse(String input) throws DukeException {
        try {
            String[] inputList = input.split(" ");
            CommandType commandType = CommandType.valueOf(inputList[0].toUpperCase().strip());

            boolean tooFewArgs = inputList.length <= 1;
            switch (commandType) {
            case LIST: {
                return new ListCommand();
            }
            case DEADLINE: {
                if (tooFewArgs) {
                    throw new DukeException("Please give a name for your deadline!");
                } else if (!input.contains(" /by ")) {
                    throw new DukeException("Please give a date/time for your deadline!");
                }
                String[] parseCommand = input.split("/by");
                String name = parseCommand[0].replaceFirst("deadline ", "");
                String deadline = parseCommand[1].strip();
                return new DeadlineCommand(name, deadline);
            }
            case TODO: {
                if (tooFewArgs) {
                    throw new DukeException("Please give a name for your ToDo task!");
                }
                String name = input.replaceFirst("todo ", "");
                return new ToDoCommand(name);
            }
            case EVENT: {
                if (tooFewArgs) {
                    throw new DukeException("Please give a name for your event!");
                } else if (!input.contains(" /from ")) {
                    throw new DukeException("Please give a starting date/time for your event!");
                } else if (!input.contains(" /to ")) {
                    throw new DukeException("Please give an ending date/time for your event!");
                }
                String[] parseCommand = input.split("/from");
                String name = parseCommand[0].replaceFirst("event ", "");
                parseCommand = parseCommand[1].split("/to");
                String from = parseCommand[0].strip();
                String by = parseCommand[1].strip();
                return new EventCommand(name, from, by);
            }
            case MARK: {
                if (tooFewArgs) {
                    throw new DukeException("Please provide the index of the task!");
                }
                int index = Integer.parseInt(inputList[1]);
                return new MarkCommand(index);
            }
            case UNMARK: {
                if (tooFewArgs) {
                    throw new DukeException("Please provide the index of the task!");
                }
                int index = Integer.parseInt(inputList[1]);
                return new UnmarkCommand(index);
            }
            case DELETE: {
                if (tooFewArgs) {
                    throw new DukeException("Please provide the index of the task!");
                }
                int index = Integer.parseInt(inputList[1]);
                return new DeleteCommand(index);
            }
            case FIND: {
                if (tooFewArgs) {
                    throw new DukeException("Please specify a search term!");
                }
                String searchTerm = input.replaceFirst("find ", "");
                return new FindCommand(searchTerm);
            }
            case BYE: {
                return new ByeCommand();
            }
            default: {
                throw new DukeException("Sorry, that command is not recognised. \n"
                        + "P.S. Maybe you could contact @dsja612 on github to request for more types of commands :)");
            }
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry, that command is not recognised. \n"
                    + "P.S. Maybe you could contact @dsja612 on github to request for more types of commands :)");
        }
    }
}
