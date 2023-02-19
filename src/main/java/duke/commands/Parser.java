package duke.commands;

import duke.commands.tasks.Deadline;
import duke.commands.tasks.Event;
import duke.commands.tasks.ToDo;
import duke.dukeexception.DukeException;
import duke.dukeexception.EmptyDateException;
import duke.dukeexception.EmptyFieldException;
import duke.dukeexception.InvalidCommandException;

/**
 * This class interprets a user command.
 */
public class Parser {
    private static final String REGEX = " ";
    private static final String WHITESPACE = " ";
    private static final String DEADLINE_INDICATOR = "/by";
    private static final String EVENT_START_INDICATOR = "/from";
    private static final String EVENT_END_INDICATOR = "/to";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final int COMMAND_INDEX = 0;
    private static final int DESCRIPTION_INDEX = 1;
    private static final int FIND_KEYWORD_INDEX = 1;
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 1;
    private final String command;
    private final String[] commandArr;

    public Parser(String command) {
        this.command = command.trim();
        this.commandArr = this.command.split(Parser.REGEX);
    }

    private void checkLength(String str) throws DukeException {
        if (str.isEmpty()) {
            throw new EmptyFieldException();
        }
    }

    private void checkDate(String str) throws DukeException {
        if (str.isEmpty()) {
            throw new EmptyDateException();
        }
    }

    private String getTaskName() {
        String taskName = "";
        for (int i = Parser.DESCRIPTION_INDEX; i < commandArr.length
                && !commandArr[i].equals(Parser.DEADLINE_INDICATOR)
                && !commandArr[i].equals(Parser.EVENT_START_INDICATOR); i++) {
            taskName += commandArr[i] + Parser.WHITESPACE;
        }
        return taskName;
    }

    private int findIndex(String indicator) {
        int curInd = Parser.DESCRIPTION_INDEX;
        while (curInd < commandArr.length && !commandArr[curInd].equals(indicator)) {
            curInd += 1;
        }
        return curInd + 1;
    }

    private String getTime(int startIndex) {
        String result = "";
        int index = startIndex;
        while (index < commandArr.length) {
            result += commandArr[index] + Parser.WHITESPACE;
            index += 1;
        }
        return result.trim();
    }

    private String getTime(int startIndex, String stop) {
        String result = "";
        int index = startIndex;
        while (index < commandArr.length && !commandArr[index].equals(stop)) {
            result += commandArr[index] + Parser.WHITESPACE;
            index += 1;
        }
        return result.trim();
    }

    private String getByTime() {
        int byIndex = this.findIndex(Parser.DEADLINE_INDICATOR);
        String taskDeadline = getTime(byIndex);
        return taskDeadline;
    }

    private String[] getEventTimes() {
        int fromIndex = findIndex(Parser.EVENT_START_INDICATOR);
        String from = getTime(fromIndex, Parser.EVENT_END_INDICATOR);
        int endIndex = findIndex((Parser.EVENT_END_INDICATOR));
        String to = getTime(endIndex);
        return new String[]{from, to};
    }
    
    /**
     * Interprets the command stored internally within the class.
     *
     * @return The command to be executed in a Command class.
     * @throws DukeException when command is invalid.
     */
    public Command process() throws DukeException {
        int editIndex = Character.getNumericValue(command.charAt(command.length() - 1)) - 1;
        String description = this.getTaskName();
        switch (commandArr[Parser.COMMAND_INDEX]) {
        case Parser.COMMAND_FIND:
            return new Find(this.command, commandArr[Parser.FIND_KEYWORD_INDEX]);
        case Parser.COMMAND_LIST:
            return new ListTasks(this.command);
        case Parser.COMMAND_MARK:
            return new Mark(this.command, editIndex);
        case Parser.COMMAND_UNMARK:
            return new Unmark(this.command, editIndex);
        case Parser.COMMAND_TODO:
            try {
                checkLength(description);
            } catch (DukeException ex) {
                throw ex;
            }
            return new Add(this.command, new ToDo(description));
        case Parser.COMMAND_DEADLINE:
            String byTime = getByTime();
            try {
                checkLength(description);
                checkDate(byTime);
            } catch (DukeException ex) {
                throw ex;
            }
            return new Add(this.command, new Deadline(description, byTime));
        case Parser.COMMAND_EVENT:
            String[] eventTimes = getEventTimes();
            return new Add(this.command, new Event(description,
                    eventTimes[Parser.FROM_INDEX], eventTimes[Parser.TO_INDEX]));
        case Parser.COMMAND_DELETE:
            return new Delete(this.command, editIndex);
        case Parser.COMMAND_BYE:
            return new Exit(this.command);
        default:
            throw new InvalidCommandException();
        }
    }
}
