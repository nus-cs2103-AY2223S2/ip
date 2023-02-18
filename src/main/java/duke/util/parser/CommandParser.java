package duke.util.parser;

import java.time.LocalDate;
import java.util.Optional;

import duke.common.exception.DukeRuntimeException;
import duke.model.command.AddCommand;
import duke.model.command.ClearCommand;
import duke.model.command.Command;
import duke.model.command.ExitCommand;
import duke.model.command.FindCommand;
import duke.model.command.HelpCommand;
import duke.model.command.Keyword;
import duke.model.command.ListCommand;
import duke.model.command.MarkCommand;
import duke.model.command.RemoveCommand;
import duke.model.command.EditCommand;
import duke.model.command.SortCommand;
import duke.model.command.UnmarkCommand;
import duke.model.task.DeadlineTask;
import duke.model.task.EventTask;
import duke.model.task.Task;
import duke.model.task.TodoTask;
import duke.util.DukeUtils;
import duke.util.container.Pair;

/**
 * Parser that parses input string into {@code Command}.
 */
public class CommandParser {

    ///////////////////////////////////
    // PREDEFINED PARSER COMBINATORS //
    ///////////////////////////////////

    private static final ApplicativeParser<Void> THROW_IF_TOO_MANY_ARGUMENTS_PARSER =
            ApplicativeParser.skipWhitespaces()
                    .takeNext(ApplicativeParser.eof())
                    .throwIfFail("too many arguments");

    private static final ApplicativeParser<String> NEXT_NON_WHITESPACES_PARSER =
            ApplicativeParser.skipWhitespaces().takeNext(ApplicativeParser.parseNonWhitespaces());

    private static final ApplicativeParser<LocalDate> DATE_PARSER =
            NEXT_NON_WHITESPACES_PARSER.optionalMap(DukeUtils::parseDate);

    private static final ApplicativeParser<LocalDate> DAY_PARSER =
            NEXT_NON_WHITESPACES_PARSER
                    .map(input -> {
                        String head = input.substring(0, 1).toUpperCase();
                        String tail = input.substring(1).toLowerCase();
                        return head + tail;
                    })
                    .optionalMap(DukeUtils::parseDay);

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
            NEXT_NON_WHITESPACES_PARSER
                    .optionalMap(Keyword::findKeyword)
                    .throwIfFail("unknown command");

    private static final ApplicativeParser<Optional<Keyword>> OPTIONAL_KEYWORD_PARSER =
            KEYWORD_PARSER
                    .ignoreIfFail()
                    .map(Optional::of)
                    .or(ApplicativeParser
                            .parseUntilEof()
                            .filter(String::isBlank)
                            .takeNext(ApplicativeParser.of(Optional.empty())))
                    .throwIfFail("unknown command");

    private static final ApplicativeParser<String> WORD_PARSER = NEXT_NON_WHITESPACES_PARSER;

    private static final ApplicativeParser<String> DESCRIPTION_UNTIL_EOF_PARSER =
            ApplicativeParser.parseUntilEof().map(String::strip);

    private static final ApplicativeParser<Task> TODO_TASK_PARSER =
            DESCRIPTION_UNTIL_EOF_PARSER.map(TodoTask::new);

    private static final ApplicativeParser<Task> DEADLINE_TASK_PARSER =
            ApplicativeParser
                    .parseUntil("--by")
                    .map(String::strip)
                    .throwIfFail("expect '--by <deadline>' as argument")
                    .flatMap(description -> DATE_OR_DAY_PARSER
                            .flatMap(deadline -> ApplicativeParser
                                    .of(new DeadlineTask(description, deadline))));

    private static final ApplicativeParser<Task> EVENT_TASK_PARSER =
            ApplicativeParser
                    .parseUntil("--from")
                    .map(String::strip)
                    .throwIfFail("expect '--from <date or day>' as argument")
                    .flatMap(description -> DATE_OR_DAY_PARSER
                            .flatMap(startTime -> ApplicativeParser
                                    .skipWhitespaces()
                                    .takeNext(ApplicativeParser.parseString("--to"))
                                    .throwIfFail("expect '--to <date or day>' as argument")
                                    .takeNext(DATE_OR_DAY_PARSER)
                                    .flatMap(endTime -> ApplicativeParser
                                            .of(new EventTask(description, startTime, endTime)))));

    /////////////////////////
    // MAIN IMPLEMENTATION //
    /////////////////////////

    /**
     * The input string, to be parsed. This input string will be consumed during the parsing
     * process.
     */
    private String input;

    /**
     * A flag to indicate whether the parser has been used or not.
     */
    private boolean isUsed = false;

    private CommandParser(String input) {
        this.input = input;
    }

    /**
     * Creates a new {@code CommandParser} instance with the given input.
     *
     * @param input the given input string, to be parsed
     * @return a new {@code CommandParser} instance
     */
    public static CommandParser of(String input) {
        return new CommandParser(input);
    }

    /**
     * Runs a specific parser combinator and consumes the input string if the parser succeeds.
     *
     * @param <T> the type of the parser result
     * @param parser the parser to run
     * @return the parser result
     */
    private <T> T runParser(ApplicativeParser<T> parser) {
        Pair<String, T> result = parser.parse(input);
        input = result.getFirst();
        return result.getSecond();
    }

    /**
     * Parses the input string as a command. This method must be called at most once on each parser,
     * as the input string will be consumed during the parsing process. If this method is called on
     * an used parser, a {@code DukeRuntimeException} will be thrown.
     *
     * @return a command corresponded to the input string
     * @throws DukeRuntimeException if the parser is already used
     * @throws ParserException if the input string cannot be parsed
     */
    public Command parse() {
        if (isUsed) {
            throw new DukeRuntimeException("this parser is already used");
        }
        isUsed = true;
        Command cmd = null;
        Keyword keyword = runParser(KEYWORD_PARSER);
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
        case SORT:
            cmd = new SortCommand();
            break;
        case EDIT:
            cmd = new EditCommand(
                    runParser(INT_PARSER),
                    runParser(DESCRIPTION_UNTIL_EOF_PARSER));
            break;
        case CLEAR:
            cmd = new ClearCommand();
            break;
        case HELP:
            cmd = new HelpCommand(runParser(OPTIONAL_KEYWORD_PARSER));
            break;
        default:
            throw new RuntimeException("should not reach here");
        }
        runParser(THROW_IF_TOO_MANY_ARGUMENTS_PARSER);
        return cmd;
    }
}
