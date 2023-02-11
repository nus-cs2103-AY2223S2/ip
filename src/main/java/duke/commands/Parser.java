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
    private static final String DEADLINE_TIME = "/by";
    private static final String EVENT_START = "/from";
    private static final String EVENT_END = "/to";
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
        if (str.length() == 0) {
            throw new EmptyFieldException();
        }
    }

    private void checkDate(String str) throws DukeException {
        if (str.length() == 0) {
            throw new EmptyDateException();
        }
    }

    private String getTaskName() {
        String taskName = "";
        for (int i = 1; i < commandArr.length
                && !commandArr[i].equals(Parser.DEADLINE_TIME)
                && !commandArr[i].equals(Parser.EVENT_START); i++) {
            taskName += commandArr[i] + Parser.WHITESPACE;
        }
        return taskName;
    }

    private String getByTime() {
        // todo: SLAP?
        int curInd = 1;
        while (curInd < commandArr.length && !commandArr[curInd].equals(Parser.DEADLINE_TIME)) {
            curInd += 1;
        }
        curInd += 1; // skips "/by"
        String taskDeadline = "";
        while (curInd < commandArr.length) {
            taskDeadline += commandArr[curInd] + Parser.WHITESPACE;
            curInd += 1;
        }
        taskDeadline = taskDeadline.trim();
        return taskDeadline;
    }

    private String[] getEventTimes() {
        // todo: SLAP?
        int curInd = 1;
        while (curInd < commandArr.length && !commandArr[curInd].equals(Parser.EVENT_START)) {
            curInd += 1;
        }
        curInd += 1;
        String from = "";
        while (curInd < commandArr.length && !commandArr[curInd].equals(Parser.EVENT_END)) {
            from += commandArr[curInd] + Parser.WHITESPACE;
            curInd += 1;
        }
        from = from.trim();
        curInd += 1;
        String to = "";
        while (curInd < commandArr.length) {
            to += commandArr[curInd] + Parser.WHITESPACE;
            curInd += 1;
        }
        to = to.trim();
        return new String[]{from, to};
    }

    /**
     * Interprets the command stored internally within the class.
     *
     * @return The command to be executed in a Command class.
     * @throws DukeException when command is invalid.
     */
    public Command process() throws DukeException {
        try {
            checkLength(this.command);
        } catch (DukeException ex) {
            throw ex;
        }
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
            // todo: SLAP?
            try {
                checkLength(description);
            } catch (DukeException ex) {
                throw ex;
            }
            String byTime = getByTime();
            try {
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
