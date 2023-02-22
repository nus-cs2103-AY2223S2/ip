package duke.parsers;

import duke.commands.*;
import duke.dukeexceptions.DukeExceptions;
import duke.dukeexceptions.MissingArgumentException;
import duke.storage.Storage;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses a request to generate command.
     *
     * @param fullCommand content to be parsed
     * @return A corresponding command.
     */
    public static Command parse(String fullCommand, Storage storage) throws DukeExceptions {
        String[] splitStr = fullCommand.split(" ");
        String requestContent = fullCommand.split(" ", 2).length == 2
                ? fullCommand.split(" ", 2)[1]
                : "";

        //END
        if (splitStr[0].equals("bye")) {
            return new EndCommand();
        }

        switch(splitStr[0]) {
        case "list":
            return new ListCommand();

        case "mark":
            if (requestContent.trim() == "") {
                throw new MissingArgumentException("The index cannot be empty.");
            }
            int markIndex = Integer.parseInt(splitStr[1]) - 1;
            return new MarkCommand(markIndex);

        case "unmark":
            if (requestContent.trim() == "") {
                throw new MissingArgumentException("The index cannot be empty.");
            }
            int unmarkIndex = Integer.parseInt(splitStr[1]) - 1;
            return new UnmarkCommand(unmarkIndex);

        case "todo":
            return new ToDoCommand(requestContent);

        case "deadline":
            return new DeadlineCommand(requestContent);

        case "event":
            return new EventCommand(requestContent);

        case "delete":
            if (requestContent.trim() == "") {
                throw new MissingArgumentException("The index cannot be empty.");
            }
            int index = Integer.parseInt(requestContent.trim()) - 1;
            return new DeleteCommand(index);

        case "find":
            return new FindCommand(requestContent);

        case "change_file_location":
            return new ChangeFileLocationCommand(requestContent, storage);

        default:
            return new UnknownCommand();
        }
    }
}
