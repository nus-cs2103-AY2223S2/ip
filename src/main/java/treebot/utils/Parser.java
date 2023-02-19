package treebot.utils;

import treebot.commands.*;
import treebot.exception.InvalidCommandException;
import treebot.exception.TaskFactoryException;
import treebot.exception.TreeBotException;
import treebot.props.DeadlineProps;
import treebot.props.EventProps;
import treebot.props.TodoProps;
import treebot.tasks.TaskFactory;

import java.time.LocalDateTime;

public class Parser {

    LocalDateTimeFormatter dateTimeFormatter = new LocalDateTimeFormatter();

    private enum CommandType {
        LIST,
        ADD_TODO,
        ADD_DEADLINE,
        ADD_EVENT,
        DELETE,
        MARK,
        UNMARK,
        FIND,
        UNDO,
        BYE,
        UNKNOWN
    }

    /**
     * Parses and returns a command according to the raw user input.
     * @param fullCommand
     * @return a Command according to the user input.
     * @throws InvalidCommandException
     * @throws TaskFactoryException
     */
    public Command parse(String fullCommand) throws TreeBotException {
        CommandType cType = getCommandType(fullCommand);

        switch (cType) {
        case LIST:
            return parseList();
        case ADD_TODO:
            return parseAddTodo(fullCommand);
        case ADD_DEADLINE:
            return parseAddDeadline(fullCommand);
        case ADD_EVENT:
            return parseAddEvent(fullCommand);
        case DELETE:
            return parseDelete(fullCommand);
        case MARK:
            return parseMark(fullCommand);
        case UNMARK:
            return parseUnmark(fullCommand);
        case FIND:
            return parseFind(fullCommand);
        case UNDO:
            return parseUndo();
        case BYE:
            return parseBye();
        default:
            throw new TreeBotException("Unknown Command!");
        }

    }

    private Command parseList() {
        return new ListCommand();
    }

    private Command parseDelete(String fullCommand) throws TreeBotException {
        return new DeleteCommand(getValidIndex(fullCommand));
    }

    private Command parseMark(String fullCommand) throws TreeBotException {
        return new ToggleMarkTaskCommand(getValidIndex(fullCommand), true);
    }

    private Command parseUnmark(String fullCommand) throws TreeBotException {
        return new ToggleMarkTaskCommand(getValidIndex(fullCommand), false);
    }

    private Command parseFind(String fullCommand) throws TreeBotException {
        return new FindCommand(getKeyword(fullCommand));
    }

    private Command parseUndo() {
        return new UndoCommand();
    }

    private Command parseBye() {
        return new ExitCommand();
    }

    private Command parseAddTodo(String fullCommand) throws TreeBotException {
        checkTodoParameters(fullCommand);
        TodoProps props = parseTodoProps(fullCommand);
        return new AddCommand(TaskFactory.makeTodo(props));
    }

    private Command parseAddDeadline(String fullCommand) throws TreeBotException {
        checkDeadlineParameters(fullCommand);
        DeadlineProps props = parseDeadlineProps(fullCommand);
        return new AddCommand(TaskFactory.makeDeadline(props));
    }

    private Command parseAddEvent(String fullCommand) throws TreeBotException {
        checkEventParameters(fullCommand);
        EventProps props = parseEventProps(fullCommand);
        return new AddCommand(TaskFactory.makeEvent(props));
    }


    private TodoProps parseTodoProps(String fullCommand) {
        assert fullCommand.split("\\s+", 2).length >= 2 : "Command is valid";
        String taskDescription = fullCommand.split("\\s+")[1];
        return new TodoProps(taskDescription);
    }

    private DeadlineProps parseDeadlineProps(String fullCommand) throws TreeBotException {
        String[] commandSplit = fullCommand.split("\\s+",2);
        assert commandSplit.length >= 2 : "Command is valid";
        String[] deadlineSplit = commandSplit[1].split(" /by ", 2);
        assert deadlineSplit.length >= 2: "Command is valid";
        String taskDescription = deadlineSplit[0];
        String deadlineString = deadlineSplit[1];

        LocalDateTime deadline = dateTimeFormatter.format(deadlineString);

        return new DeadlineProps(taskDescription, deadline);
    }

    private EventProps<LocalDateTime> parseEventProps(String fullCommand) throws TreeBotException {
        String[] commandSplit = fullCommand.split("\\s+",2);
        assert commandSplit.length >= 2 : "Command is valid";
        String[] rangeSplit = commandSplit[1].split(" /from ", 2);
        assert rangeSplit.length >= 2 : "Command is valid";
        String[] startEndSplit = rangeSplit[1].split(" /to ", 2);
        assert startEndSplit.length >= 2 : "Command is valid";

        LocalDateTime start = dateTimeFormatter.format(startEndSplit[0]);
        LocalDateTime end = dateTimeFormatter.format(startEndSplit[1]);


        return new EventProps<LocalDateTime>(rangeSplit[0], start, end);
    }

    private void checkTodoParameters(String fullCommand) throws TreeBotException {
        if (!isTodoTaskDescriptionSpecified(fullCommand)) {
            throw new TreeBotException("Todo task description cannot be empty");
        }
    }
    private void checkDeadlineParameters(String fullCommand) throws TreeBotException {
        if (!isDeadlineTaskDescriptionSpecified(fullCommand)) {
            throw new TreeBotException("Deadline task description cannot be empty");
        }

        if (!isDeadlineSpecified(fullCommand)) {
            throw new TreeBotException("Deadline must be specified");
        }
    }

    private void checkEventParameters(String fullCommand) throws TreeBotException {
        if (!isEventTaskDescriptionSpecified(fullCommand)) {
            throw new TaskFactoryException("Event task description cannot be empty");
        }

        if (!isEventRangeValid(fullCommand)) {
            throw new TaskFactoryException("Event task datetime range is not valid");
        }

    }


    private boolean doesParameterExist(String fullCommand) {
        return fullCommand.split("\\s+", 2).length >= 2;
    }
    private boolean isTodoTaskDescriptionSpecified(String fullCommand) {
        return fullCommand.split("\\s+", 2).length >= 2
                && !fullCommand.split("\\s+", 2)[1].isEmpty();
    }

    private boolean isDeadlineTaskDescriptionSpecified(String fullCommand) {
        if (!doesParameterExist(fullCommand)) {
           return false;
        }
        String[] commandSplit = fullCommand.split("\\s+", 2);
        return !commandSplit[1].split(" /by ")[0].isEmpty();

    }

    private boolean isEventTaskDescriptionSpecified(String fullCommand) {
        if (!doesParameterExist(fullCommand)) {
            return false;
        }

        String[] commandSplit = fullCommand.split("\\s+", 2);

        return !commandSplit[1].split(" /from ", 2)[0].isEmpty();

    }


    private boolean isDeadlineSpecified(String fullCommand) {
        String[] commandSplit = fullCommand.split("\\s+", 2);
        assert commandSplit.length >= 2 : "Task description checked before this";

        return commandSplit[1].split(" /by ", 2).length >= 2;
    }

    private boolean isEventRangeValid(String fullCommand) {

        String[] commandSplit = fullCommand.split("\\s+", 2);

        assert commandSplit.length >= 2 : "Task description checked first";

        String[] rangeSplit = commandSplit[1].split(" /from ", 2);

        if (rangeSplit.length < 2) {
            return false;
        }

        String[] startEndSplit = rangeSplit[1].split(" /to ", 2);
        if (startEndSplit.length < 2) {
            return false;
        }

        return true;
    }

    private int getValidIndex(String fullCommand) throws TreeBotException {
        String[] commandSplit = fullCommand.split("\\s+", 2);
        if (!isIndexSpecified(fullCommand)) {
            throw new TreeBotException("Specify index!");
        }
        try {
            return Integer.parseInt(commandSplit[1]);
        } catch (NumberFormatException e) {
            throw new TreeBotException("Enter Valid index!");
        }
    }

    private boolean isIndexSpecified(String fullCommand) {
        return fullCommand.split("\\s+", 2).length >= 2;
    }

    private String getKeyword(String fullCommand) throws TreeBotException {
        String[] commandSplit = fullCommand.split("\\s+",2);
        if (commandSplit.length < 2) {
            throw new TreeBotException("Specify keyword for find!");
        }

        return commandSplit[1];
    }


    private CommandType getCommandType(String fullCommand) {
        assert !fullCommand.isEmpty() : "fullCommand assumed to be non empty string";
        String[] splitStr = fullCommand.split("\\s+");
        switch (splitStr[0]) {
        case "list":
            return CommandType.LIST;
        case "todo":
            return CommandType.ADD_TODO;
        case "deadline":
            return CommandType.ADD_DEADLINE;
        case "event":
            return CommandType.ADD_EVENT;
        case "delete":
            return CommandType.DELETE;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "find":
            return CommandType.FIND;
        case "undo":
            return CommandType.UNDO;
        case "bye":
            return CommandType.BYE;
        default:
            return CommandType.UNKNOWN;
        }



    }

}
