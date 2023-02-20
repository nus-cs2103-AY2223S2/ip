package voile.util.parser;

import java.time.LocalDate;
import java.util.Optional;

import voile.model.command.AddCommand;
import voile.model.command.ClearCommand;
import voile.model.command.Command;
import voile.model.command.EditCommand;
import voile.model.command.ExitCommand;
import voile.model.command.FindCommand;
import voile.model.command.HelpCommand;
import voile.model.command.Keyword;
import voile.model.command.ListCommand;
import voile.model.command.MarkCommand;
import voile.model.command.RemoveCommand;
import voile.model.command.SortCommand;
import voile.model.command.UnmarkCommand;
import voile.model.task.DeadlineTask;
import voile.model.task.EventTask;
import voile.model.task.Task;
import voile.model.task.TodoTask;
import voile.util.Conversions;
import voile.util.container.Pair;

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
                    .throwIfFail("Too many arguments - the library cannot handle them.");

    private static final ApplicativeParser<String> NEXT_NON_WHITESPACES_PARSER =
            ApplicativeParser.skipWhitespaces().takeNext(ApplicativeParser.parseNonWhitespaces());

    private static final ApplicativeParser<LocalDate> DATE_PARSER =
            NEXT_NON_WHITESPACES_PARSER.optionalMap(Conversions::parseDate);

    private static final ApplicativeParser<LocalDate> DAY_PARSER =
            NEXT_NON_WHITESPACES_PARSER
                    .map(input -> {
                        String head = input.substring(0, 1).toUpperCase();
                        String tail = input.substring(1).toLowerCase();
                        return head + tail;
                    })
                    .optionalMap(Conversions::parseDay);

    private static final ApplicativeParser<LocalDate> DATE_OR_DAY_PARSER =
            DATE_PARSER
                    .or(DAY_PARSER)
                    .throwIfFail("Incorrect format for a date/day - the library cannot store it.");

    private static final ApplicativeParser<Integer> INT_PARSER =
            NEXT_NON_WHITESPACES_PARSER
                    .optionalMap(Conversions::parseInt)
                    .throwIfFail("Incorrect format for an index - the library cannot handle it.");

    private static final ApplicativeParser<Keyword> KEYWORD_PARSER =
            NEXT_NON_WHITESPACES_PARSER
                    .optionalMap(Conversions::parseKeyword)
                    .throwIfFail("Unknown command - the library cannot handle it.");

    private static final ApplicativeParser<Optional<Keyword>> OPTIONAL_KEYWORD_PARSER =
            KEYWORD_PARSER
                    .ignoreIfFail()
                    .map(Optional::of)
                    .or(ApplicativeParser
                            .parseUntilEof()
                            .filter(String::isBlank)
                            .takeNext(ApplicativeParser.of(Optional.empty())))
                    .throwIfFail("Unknown command - there is no such entry for 'help'.");

    private static final ApplicativeParser<String> WORD_PARSER = NEXT_NON_WHITESPACES_PARSER;

    private static final ApplicativeParser<String> DESCRIPTION_UNTIL_EOF_PARSER =
            ApplicativeParser.parseUntilEof().map(String::strip);

    private static final ApplicativeParser<Task> TODO_TASK_PARSER =
            DESCRIPTION_UNTIL_EOF_PARSER.map(TodoTask::new);

    private static final String MISSING_BY_ARGUMENT =
            "Missing '--by <deadline>' - the library cannot create this deadline.";

    private static final ApplicativeParser<Task> DEADLINE_TASK_PARSER =
            ApplicativeParser
                    .parseUntil("--by")
                    .map(String::strip)
                    .throwIfFail(MISSING_BY_ARGUMENT)
                    .flatMap(description -> DATE_OR_DAY_PARSER
                            .flatMap(deadline -> ApplicativeParser
                                    .of(new DeadlineTask(description, deadline))));

    private static final String MISSING_FROM_ARGUMENT =
            "Missing '--from <date or day>' - the library cannot create this event.";

    private static final String MISSING_TO_ARGUMENT =
            "Missing '--to <date or day>' - the library cannot create this event.";

    private static final ApplicativeParser<Task> EVENT_TASK_PARSER =
            ApplicativeParser
                    .parseUntil("--from")
                    .map(String::strip)
                    .throwIfFail(MISSING_FROM_ARGUMENT)
                    .flatMap(description -> DATE_OR_DAY_PARSER
                            .flatMap(startTime -> ApplicativeParser
                                    .skipWhitespaces()
                                    .takeNext(ApplicativeParser.parseString("--to"))
                                    .throwIfFail(MISSING_TO_ARGUMENT)
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
     * @throws RuntimeException if the parser is already used
     * @throws ParserException if the input string cannot be parsed
     */
    public Command parse() {
        if (isUsed) {
            throw new RuntimeException("this parser is already used");
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
