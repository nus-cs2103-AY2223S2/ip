package duke.parser;

import java.util.Objects;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.EditCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exception.blankfieldexceptions.BlankFieldDeadlineException;
import duke.exception.blankfieldexceptions.BlankFieldEventException;
import duke.exception.blankfieldexceptions.BlankFieldTodoException;
import duke.exception.deletetaskexceptions.DeleteTaskNumberInvalidException;
import duke.exception.editcommandexceptions.EditTaskNumberInvalidException;
import duke.exception.editcommandexceptions.NoNewDetailsException;
import duke.exception.marktaskexceptions.MarkTaskNumberInvalidException;
import duke.exception.parserexceptions.NoCommandBodyException;
import duke.exception.parserexceptions.UnknownCommandError;


/**
 * Represents the wrapper for the parsing logic for commands for Duke.
 */
public class Parser {


    /**
     * Parses the command text given and returns the Command object associated with the command text.
     *
     * @param command the command text given.
     * @param lengthOfList the length of taskList currently
     * @return Command object associated with the command text.
     * @throws NoCommandBodyException thrown when there is no command body
     * @throws MarkTaskNumberInvalidException thrown when there is no task identifier found for mark or unmark
     *      commands.
     * @throws DeleteTaskNumberInvalidException thrown when there is no task identifier found for delete commands.
     * @throws EditTaskNumberInvalidException thrown when there is no task identifier found for edit commands.
     * @throws BlankFieldTodoException thrown when there is a blank field for the addToDoCommand.
     * @throws BlankFieldDeadlineException thrown when there is a blank field for the addDeadlineCommand.
     * @throws BlankFieldEventException thrown when there is a blank field for the addEventCommand.
     * @throws UnknownCommandError thrown when the command's keyword does not match any of the ones known.
     * @throws NoNewDetailsException Thrown when no new details are specified for an edit command.
     */
    public Command parse(String command, int lengthOfList) throws MarkTaskNumberInvalidException,
            BlankFieldTodoException, BlankFieldDeadlineException, BlankFieldEventException, UnknownCommandError,
            NoCommandBodyException, NoNewDetailsException, DeleteTaskNumberInvalidException,
            EditTaskNumberInvalidException {
        if (Objects.equals(command, "list")) {
            return new ListCommand();
        } else if (Objects.equals(command, "bye")) {
            return new ByeCommand();
        }


        String[] words = command.split(" ", 2);

        if (words.length <= 1 || words[1].stripLeading().isBlank()) {
            throw new NoCommandBodyException();
        }

        String keyWord = words[0];
        String commandBody = words[1];

        switch (keyWord) {
        case "mark":
            return parseMarkCommand(commandBody, lengthOfList);

        case "unmark":
            return parseUnmarkCommand(commandBody, lengthOfList);

        case "todo":
            return parseAddToDoCommand(commandBody);

        case "deadline":
            return parseAddDeadlineCommand(commandBody);

        case "event":
            return parseAddEventCommand(commandBody);

        case "delete":
            return parseDeleteCommand(commandBody, lengthOfList);

        case "find":
            return parseFindCommand(commandBody);
        case "edit":
            return parseEditCommand(commandBody, lengthOfList);
        default:
            throw new UnknownCommandError();
        }
    }

    private EditCommand parseEditCommand(String commandBody, int lengthOfList) throws NoNewDetailsException,
            EditTaskNumberInvalidException {
        try {
            String[] parameters = commandBody.split(" ", 2);
            if (parameters.length <= 1 || parameters[1].stripLeading().isBlank()) {
                throw new NoNewDetailsException();
            }
            int taskNumber = Integer.parseInt(parameters[0]);
            if (taskNumber > lengthOfList || taskNumber <= 0 || commandBody.trim().isEmpty()) {
                throw new EditTaskNumberInvalidException();
            }
            return new EditCommand(taskNumber, parameters[1]);
        } catch (NumberFormatException e) {
            throw new EditTaskNumberInvalidException();
        }
    }

    private FindCommand parseFindCommand(String commandBody) {
        return new FindCommand(commandBody);
    }

    private DeleteCommand parseDeleteCommand(String commandBody, int lengthOfList)
            throws DeleteTaskNumberInvalidException {
        try {
            int taskNumber = Integer.parseInt(commandBody);
            if (taskNumber > lengthOfList || taskNumber <= 0 || commandBody.trim().isEmpty()) {
                throw new DeleteTaskNumberInvalidException();
            }
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new DeleteTaskNumberInvalidException();
        }
    }

    private AddEventCommand parseAddEventCommand(String commandBody) throws BlankFieldEventException {
        if (commandBody.trim().isEmpty()) {
            throw new BlankFieldEventException();
        }
        return new AddEventCommand(commandBody);
    }

    private AddDeadlineCommand parseAddDeadlineCommand(String commandBody) throws BlankFieldDeadlineException {
        if (commandBody.trim().isEmpty()) {
            throw new BlankFieldDeadlineException();
        }
        return new AddDeadlineCommand(commandBody);
    }

    private AddToDoCommand parseAddToDoCommand(String commandBody) throws BlankFieldTodoException {
        if (commandBody.trim().isEmpty()) {
            throw new BlankFieldTodoException();
        }
        return new AddToDoCommand(commandBody);
    }

    private MarkCommand parseMarkCommand(String commandBody, int lengthOfList) throws MarkTaskNumberInvalidException {
        try {
            int taskNumber = Integer.parseInt(commandBody);
            if (taskNumber > lengthOfList || taskNumber <= 0 || commandBody.trim().isEmpty()) {
                throw new MarkTaskNumberInvalidException();
            }
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new MarkTaskNumberInvalidException();
        }
    }

    private UnmarkCommand parseUnmarkCommand(String commandBody, int lengthOfList)
            throws MarkTaskNumberInvalidException {
        try {
            int taskNumber = Integer.parseInt(commandBody);
            if (taskNumber > lengthOfList || taskNumber <= 0 || commandBody.trim().isEmpty()) {
                throw new MarkTaskNumberInvalidException();
            }
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new MarkTaskNumberInvalidException();
        }
    }
}
