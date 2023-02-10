package duke.util.parser;

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
import duke.exception.ParserException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.util.container.Pair;

public class CommandParser {

    private enum Keyword {

        // @formatter:off
        BYE("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), MARK("mark"),
        UNMARK("unmark"), DELETE("delete"), FIND("find"), UNIQUE("unique"), LIST_DEADLINES("list-deadlines"),
        EDIT("edit"), UNKNOWN("");
        // @formatter:on

        final String value;

        Keyword(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }

        static Keyword findKeyword(String value) {
            return Arrays.stream(values())
                    .filter(keyword -> keyword.value.equals(value))
                    .findFirst()
                    .orElse(UNKNOWN);
        }
    }

    private static final ApplicativeParser<Void> THROW_IF_TOO_MANY_ARGUMENTS_PARSER =
            ApplicativeParser.skipWhitespaces()
                    .takeNext(ApplicativeParser.eof())
                    .throwIfFail("too many arguments");

    private static final ApplicativeParser<String> NEXT_NON_WHITESPACES_PARSER =
            ApplicativeParser.skipWhitespaces().takeNext(ApplicativeParser.parseNonWhitespaces());

    private static final ApplicativeParser<LocalDate> DATE_PARSER =
            NEXT_NON_WHITESPACES_PARSER.optionalMap(DukeUtils::parseDate);

    private static final ApplicativeParser<LocalDate> DAY_PARSER =
            NEXT_NON_WHITESPACES_PARSER.optionalMap(DukeUtils::parseDay);

    private static final ApplicativeParser<LocalDate> DATE_OR_DAY_PARSER =
            DATE_PARSER
                    .or(DAY_PARSER)
                    .throwIfFail("incorrect format for a date/day argument - \n"
                            + "date format is yyyy-MM-dd, day format is EEE");

    private static final ApplicativeParser<Integer> INT_PARSER =
            NEXT_NON_WHITESPACES_PARSER
                    .optionalMap(DukeUtils::parseInt)
                    .throwIfFail("incorrect format for an integer argument");

    private static final ApplicativeParser<Keyword> KEYWORD_PARSER =
            NEXT_NON_WHITESPACES_PARSER.map(Keyword::findKeyword);

    private static final ApplicativeParser<String> WORD_PARSER = NEXT_NON_WHITESPACES_PARSER;

    private static final ApplicativeParser<String> DESCRIPTION_UNTIL_EOF_PARSER =
            ApplicativeParser.parseUntilEof().map(String::strip);

    private static final ApplicativeParser<Task> TODO_TASK_PARSER =
            DESCRIPTION_UNTIL_EOF_PARSER.map(TodoTask::new);

    private static final ApplicativeParser<Task> DEADLINE_TASK_PARSER =
            ApplicativeParser
                    .parseUntil("--by")
                    .throwIfFail("expect '--by <deadline>' as argument")
                    .flatMap(description -> DATE_OR_DAY_PARSER
                            .flatMap(deadline -> ApplicativeParser
                                    .of(new DeadlineTask(description, deadline))));

    private static final ApplicativeParser<Task> EVENT_TASK_PARSER =
            ApplicativeParser
                    .parseUntil("--from")
                    .throwIfFail("expect '--from <date or day>' as argument")
                    .flatMap(description -> DATE_OR_DAY_PARSER
                            .flatMap(startTime -> ApplicativeParser
                                    .skipWhitespaces()
                                    .takeNext(ApplicativeParser.parseString("--to"))
                                    .throwIfFail("expect '--to <date or day>' as argument")
                                    .takeNext(DATE_OR_DAY_PARSER)
                                    .flatMap(endTime -> ApplicativeParser
                                            .of(new EventTask(description, startTime, endTime)))));

    private String input;

    private CommandParser(String input) {
        this.input = input;
    }

    public static CommandParser of(String input) {
        return new CommandParser(input);
    }

    private <T> T runParser(ApplicativeParser<T> parser) {
        Pair<String, T> result = parser.parse(input);
        input = result.getFirst();
        return result.getSecond();
    }

    /**
     * Parses the input string as a command.
     *
     * @return a command corresponded to the input string
     */
    public Command parse() {
        Keyword keyword = Keyword.UNKNOWN;
        Command cmd = null;
        try {
            keyword = runParser(KEYWORD_PARSER);
            switch (keyword) {
            case BYE:
                cmd = new ExitCommand();
                break;
            case LIST:
                cmd = new ListCommand();
                break;
            case TODO:
                cmd = new AddCommand(runParser(TODO_TASK_PARSER));
                break;
            case DEADLINE:
                cmd = new AddCommand(runParser(DEADLINE_TASK_PARSER));
                break;
            case EVENT:
                cmd = new AddCommand(runParser(EVENT_TASK_PARSER));
                break;
            case MARK:
                cmd = new MarkCommand(runParser(INT_PARSER));
                break;
            case UNMARK:
                cmd = new UnmarkCommand(runParser(INT_PARSER));
                break;
            case DELETE:
                cmd = new RemoveCommand(runParser(INT_PARSER));
                break;
            case FIND:
                cmd = new FindCommand(runParser(WORD_PARSER));
                break;
            case UNIQUE:
                cmd = new UniqueCommand();
                break;
            case LIST_DEADLINES:
                cmd = new ListDeadlinesCommand();
                break;
            case EDIT:
                cmd = new EditCommand(
                        runParser(INT_PARSER),
                        runParser(DESCRIPTION_UNTIL_EOF_PARSER));
                break;
            default:
                throw new DukeRuntimeException("unknown command");
            }
            runParser(THROW_IF_TOO_MANY_ARGUMENTS_PARSER);
        } catch (ParserException ex) {
            throw new DukeRuntimeException(keyword.getValue() + ": " + ex.getMessage());
        }
        return cmd;
    }
}
