package duke.model;

import java.time.LocalDate;
import java.util.Arrays;

import duke.DukeUtils;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ListDeadlinesCommand;
import duke.command.MarkCommand;
import duke.command.RemoveCommand;
import duke.command.UniqueCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeRuntimeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

/**
 * A simple parser which provides utilities mostly by {@code static} methods.
 */
public class Parser {

    private enum CommandKeyword {

        // @formatter:off
        BYE("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), MARK("mark"),
        UNMARK("unmark"), DELETE("delete"), FIND("find"), UNIQUE("unique"), LIST_DEADLINES("list-deadlines"),
        EDIT("edit"), UNKNOWN("");
        // @formatter:on

        private final String keyword;

        CommandKeyword(String keyword) {
            this.keyword = keyword;
        }
    }

    /**
     * Parses an input string as a command.
     *
     * @param input the input string
     * @return a command corresponded to the input string
     */
    public static Command parseCommand(String input) {
        Parser p = new Parser(input);
        CommandKeyword kw = p.parseKeyword();
        Command cmd;
        try {
            switch (kw) {
            case BYE:
                cmd = new ExitCommand();
                break;
            case LIST:
                cmd = new ListCommand();
                break;
            case TODO: {
                String description = p.parseUntilEol();
                cmd = new AddCommand(new TodoTask(description));
                break;
            }
            case DEADLINE: {
                String description = p.parseUntil("--by");
                LocalDate by = p.parseDateOrDayArgument();
                cmd = new AddCommand(new DeadlineTask(description, by));
                break;
            }
            case EVENT: {
                String description = p.parseUntil("--from");
                LocalDate startTime = p.parseDateOrDayArgument();
                p.parseUntil("--to");
                LocalDate endTime = p.parseDateOrDayArgument();
                cmd = new AddCommand(new EventTask(description, startTime, endTime));
                break;
            }
            case MARK: {
                int index = p.parseIntArgument();
                cmd = new MarkCommand(index);
                break;
            }
            case UNMARK: {
                int index = p.parseIntArgument();
                cmd = new UnmarkCommand(index);
                break;
            }
            case DELETE: {
                int index = p.parseIntArgument();
                cmd = new RemoveCommand(index);
                break;
            }
            case FIND: {
                String keyword = p.parseWordArgument();
                cmd = new FindCommand(keyword);
                break;
            }
            case UNIQUE:
                cmd = new UniqueCommand();
                break;
            case LIST_DEADLINES:
                cmd = new ListDeadlinesCommand();
                break;
            case EDIT: {
                int index = p.parseIntArgument();
                String newDescription = p.parseUntilEol();
                cmd = new EditCommand(index, newDescription);
                break;
            }
            default:
                throw new DukeRuntimeException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means");
            }
            p.raiseIfTooManyArgument();
        } catch (ParserException ex) {
            throw new DukeRuntimeException(kw.keyword + ": " + ex.getMessage());
        }
        return cmd;
    }

    private static class ParserException extends RuntimeException {
        ParserException(String message) {
            super(message);
        }
    }

    /////////////////////////////
    // INTERNAL IMPLEMENTATION //
    /////////////////////////////

    private String input;
    private int offset;
    private int length;

    private Parser(String input) {
        this.input = input;
        this.offset = 0;
        this.length = input.length();
    }

    private void skipWhitespaces() {
        while (offset < length && Character.isWhitespace(input.charAt(offset))) {
            offset++;
        }
    }

    private void raiseIfTooManyArgument() {
        skipWhitespaces();
        if (offset < length) {
            throw new ParserException("too many arguments");
        }
    }

    private String parseNonwhitespaces() {
        skipWhitespaces();
        int oldOffset = offset;
        while (offset < length && !Character.isWhitespace(input.charAt(offset))) {
            offset++;
        }
        return input.substring(oldOffset, offset);
    }

    private CommandKeyword parseKeyword() {
        skipWhitespaces();
        String keyword = parseNonwhitespaces();
        return Arrays.stream(CommandKeyword.values()).filter(kw -> kw.keyword.equals(keyword))
                .findFirst().orElse(CommandKeyword.UNKNOWN);
    }

    private String parseUntilEol() {
        String view = input.substring(offset).strip();
        offset = length;
        return view;
    }

    private String parseUntil(String end) {
        int endIndex = input.indexOf(end, offset);
        if (endIndex < 0) {
            offset = length;
            throw new ParserException("missing " + end);
        }
        int oldOffset = offset;
        offset = endIndex + end.length();
        return input.substring(oldOffset, endIndex).strip();
    }

    private int parseIntArgument() {
        skipWhitespaces();
        String intString = parseNonwhitespaces();
        return DukeUtils.parseInt(intString)
                .orElseThrow(() -> new ParserException("expect an integer as argument"));
    }

    private LocalDate parseDateOrDayArgument() {
        skipWhitespaces();
        String dateOrDayString = parseNonwhitespaces();
        return DukeUtils
                .choice(DukeUtils.parseDate(dateOrDayString), DukeUtils.parseDay(dateOrDayString))
                .orElseThrow(() -> new ParserException(
                        "expect a date or day as argument - date format is yyyy-MM-dd, day format is EEE"));
    }

    private String parseWordArgument() {
        skipWhitespaces();
        String word = parseNonwhitespaces();
        if (word.isEmpty()) {
            throw new ParserException("expect as word as argument - word cannot be empty");
        }
        return word;
    }
}
