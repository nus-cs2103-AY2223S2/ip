package duke.parser;

import duke.commands.*;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static Command parseCommand(String userInput) {
        assert userInput.length() != 0 : "string should not be empty";
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            // incorrect command
            return new InvalidCommand();
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case ListCommand.COMMAND_WORD:
            return prepareList(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case PrioritizeCommand.COMMAND_WORD:
            return preparePrioritize(arguments);

        case TagCommand.COMMAND_WORD:
            return prepareTag(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            return new InvalidCommand();
        }
    }

    private static Command preparePrioritize(String args) {
        try {
            String[] taskNumPriority = args.substring(1).split("\\s+");
            final int targetIndex = parseArgsAsDisplayedIndex(taskNumPriority[0]);
            final String priority = taskNumPriority[1];
            if (priority.equals("high") || priority.equals("medium") || priority.equals("low")) {
                return new PrioritizeCommand(targetIndex, priority);
            } else {
                return new InvalidCommand();
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand("Please specify the task number and level u would like me to " +
                    "prioritize it!");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand("Please specify the level u would like me to " +
                    "prioritize it!");
        }
    }

    private static Command prepareTag(String args) {
        try {
            String[] taskNumTagName = args.substring(1).split("\\s+");
            final int targetIndex = parseArgsAsDisplayedIndex(taskNumTagName[0]);
            final String tagName = taskNumTagName[1];
            return new TagCommand(targetIndex, tagName);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        } catch (StringIndexOutOfBoundsException s) {
            return new InvalidCommand("Please specify the task number and level u would like me to " +
                    "tag!");
        } catch (ArrayIndexOutOfBoundsException a) {
            return new InvalidCommand("Please specify the level u would like me to " +
                    "tag!");
        }
    }

    private static Command prepareDelete(String args) {
        System.out.println(args);
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private static Command prepareMark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new MarkCommand(targetIndex);
        } catch (DukeException pe) {
            return new InvalidCommand(pe.getMessage());
        }
    }

    private static Command prepareUnmark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new UnmarkCommand(targetIndex);
        } catch (DukeException pe) {
            return new InvalidCommand(pe.getMessage());
        }
    }

    private static Command prepareEvent(String args) {
        try {
            Object[] descStartEnd = parseEvent(args);
            String desc = String.valueOf(descStartEnd[0]);
            LocalDate start = LocalDate.parse(descStartEnd[1].toString());
            LocalDate end = LocalDate.parse(descStartEnd[2].toString());
            return new EventCommand(desc, start, end);

        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private static Command prepareDeadline(String args) {
        try {
            Object[] descBy = parseDeadline(args);
            String desc = String.valueOf(descBy[0]);
            LocalDate by = LocalDate.parse(descBy[1].toString());
            return new DeadlineCommand(desc, by);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private static Command prepareTodo(String args) {
        try {
            String desc = parseTodo(args);
            return new TodoCommand(desc);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private static Command prepareFind(String args) {
        try {
            String keyword = parseFind(args);
            return new FindCommand(keyword);
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private static Command prepareList(String args) {
        if (args.length() != 0) {
            return new InvalidCommand();
        } else {
            return new ListCommand();
        }
    }

    private static String parseFind(String args) throws DukeException {
        try {
            String keyword = parseDescription(args, "find");
            return keyword;
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static String parseTodo(String args) throws DukeException {
        try {
            String desc = parseDescription(args, "todo");
            return desc;
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static Object[] parseDeadline(String args) throws DukeException {
        if (!args.contains("/by")) {
            throw new DukeException("Please specify the deadline.");
        } else {
            try {
                String[] segments = args.split(" /by ", 2);
                String desc = parseDescription(args, "deadline");
                LocalDate by = parseArgsAsLocalDate(segments[1]);
                Object[] returnArr = {desc, by};
                return returnArr;
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    private static Object[] parseEvent(String args) throws DukeException {
        if (!args.contains("/from") && !args.contains("/to")) {
            throw new DukeException("Please specify both the start and end times/dates.");
        } else {
            try {
                String[] segments = args.split(" /from ", 2);
                String desc = parseDescription(args, "event");
                LocalDate start = parseArgsAsLocalDate(segments[1].split(" /to")[0]);
                LocalDate end = parseArgsAsLocalDate(segments[1].split("/to ")[1]);
                Object[] returnArr = {desc, start, end};
                return returnArr;
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    private static String parseDescription(String args, String task) throws DukeException {
        try {
            String desc = "";
            if (task == "event") {
                String[] segments = args.split(" /from ", 2);
                desc = segments[0].substring(1);
            } else if (task == "deadline") {
                String[] segments = args.split(" /by ", 2);
                desc = segments[0].substring(1);

            } else if (task == "todo" || task == "find") {
                desc = args.substring(1);
            }
            return desc;
        } catch (StringIndexOutOfBoundsException e) {
            if (task == "keyword") {
                throw new DukeException("You need to provide a keyword.");
            } else {
                throw new DukeException("You need to provide a short description or title of the task.");
            }
        }
    }

    private static int parseArgsAsDisplayedIndex(String args) throws DukeException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException("Could not find index number to parse.");
        }
        try {
            int targetIndex = Integer.parseInt(matcher.group("targetIndex"));
            return targetIndex;
        } catch (NumberFormatException ne) {
            throw new DukeException("You have not specified the task number.");
        }
    }

    private static LocalDate parseArgsAsLocalDate(String time) throws DukeException {
        try {
            LocalDate localDate = LocalDate.parse(time);
            return localDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("Can't parse date/time!");
        }
    }
}
