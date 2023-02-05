package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;


/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {
    private enum Commands {
        bye,
        list,
        mark,
        unmark,
        deadline,
        event,
        todo,
        delete,
        find
    }
    private static boolean isFine;
    /**
     * Creates new command according to the input of the user.
     *
     * @param inputCommand Input command from the user.
     * @return Command that corresponds to the user input.
     * @throws DukeException If the user input cannot be recognised.
     */
    public static Command parse(String inputCommand) throws DukeException {
        try {
            String[] splitString = inputCommand.split(" ", 2);
            Commands commands = Commands.valueOf(splitString[0].toLowerCase());
            switch (commands) {
            case bye:
                return new ByeCommand();
            case list:
                isFine = false;
                return new ListCommand();
            case mark:
                if (splitString.length == 1) {
                    throw new DukeException("\tPlease enter the task number to mark!");
                } else {
                    return new MarkCommand(Integer.parseInt(splitString[1].trim()) - 1);
                }
            case unmark:
                if (splitString.length == 1) {
                    throw new DukeException("\tPlease enter the task number to unmark!");
                } else {
                    return new UnmarkCommand(Integer.parseInt(splitString[1].trim()) - 1);
                }
            case deadline:
                if (splitString.length == 1) {
                    throw new DukeException("\tOOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String[] splitDescription = splitString[1].split(" /by ");
                    if (splitDescription.length == 1) {
                        throw new DukeException("\tOOPS!!! The date of a deadline cannot be empty.");
                    }
                    return new DeadlineCommand(splitDescription[0], splitDescription[1]);
                }
            case event:
                if (splitString.length == 1) {
                    throw new DukeException("\tOOPS!!! The description of an event cannot be empty.");
                } else {
                    String[] splitDescriptionArray = splitString[1].split(" /from ");
                    String[] timeSplitArray = splitDescriptionArray[1].split("/to");
                    if (timeSplitArray.length == 1) {
                        throw new DukeException("\tOOPS!!! The date of an event cannot be empty.");
                    }
                    return new EventCommand(splitDescriptionArray[0], timeSplitArray[0], timeSplitArray[1]);
                }
            case todo:
                if (splitString.length == 1) {
                    throw new DukeException("\tOOPS!!! The description of a todo cannot be empty.");
                } else {
                    return new ToDoCommand(splitString[1]);
                }
            case delete:
                if (splitString.length == 1) {
                    throw new DukeException("\tPlease enter the task number to delete!");
                } else {
                    return new DeleteCommand(Integer.parseInt(splitString[1].trim()) - 1, isFine);
                    }
            case find:
                if (splitString[1].trim().equals("")) {
                    throw new DukeException("\t☹ OOPS!!! The name of task u want to find cannot be empty!.");
                } else {
                    isFine = true;
                    return new FindCommand(splitString[1].split(" "));
                }
            default:
                throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t☹ OOPS!!! The name of task u want to find cannot be empty!");
        }
    }
}
