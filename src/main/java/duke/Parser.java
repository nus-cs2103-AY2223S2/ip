package duke;

import duke.command.TodoCommand;
import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.HelpCommand;

/**
 * The Parser class helps to translate the user input to various commands
 */
public class Parser {

    /**
     * Returns a Command class that represent the command to be executed
     * @param command A string representation of the command by user input
     * @return A specific command
     * @throws DukeException if input is invalid
     */
    public static Command parse(String command) throws DukeException {
        String[] commandArray = command.split(" ", 2);
        switch (commandArray[0].toUpperCase()) {
            case "TODO": {
                if (commandArray.length == 1) {
                    throw new DukeException("You have to tell me what you want to do >.<");
                }
                String taskDetails = commandArray[1].strip();
                return new TodoCommand(taskDetails);
            }
            case "LIST": {
                return new ListCommand();
            }
            case "MARK": {
                if (commandArray.length == 1) {
                    throw new DukeException("You have to tell me which task you want to mark as done!");
                }
                String taskNum = commandArray[1].strip();
                return new MarkCommand(taskNum);
            }
            case "UNMARK": {
                if (commandArray.length == 1) {
                    throw new DukeException("You have to tell me which task you want to mark as undone!");
                }
                String taskNum = commandArray[1].strip();
                return new UnmarkCommand(taskNum);
            }
            case "EVENT": {
                String taskDetails = commandArray[1].strip();
                return new EventCommand(taskDetails);
            }
            case "DEADLINE": {
                String taskDetails = commandArray[1].strip();
                return new DeadlineCommand(taskDetails);
            }
            case "DELETE": {
                String taskNum = commandArray[1].strip();
                return new DeleteCommand(taskNum);
            }
            case "BYE": {
                return new ByeCommand();
            }
            case "FIND": {
                if (commandArray.length == 1) {
                    throw new DukeException("You can use the help of some key words to find your task :)");
                }
                String details = commandArray[1].strip();
                return new FindCommand(details);
            }
            case "HELP": {
                return new HelpCommand();
            }
            default: {
                throw new DukeException("It's an INVALID COMMAND, follow the instruction and try again :-)");
            }
        }
    }
}
