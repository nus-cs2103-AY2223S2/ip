import java.util.function.Supplier;

public class Parser {

    private enum CommandKeyword {

        // @formatter:off
        BYE("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), 
        EVENT("event"), MARK("mark"), UNMARK("unmark"), DELETE("delete"),
        UNKNOWN(null);
        // @formatter:on

        private final String keyword;

        CommandKeyword(String keyword) {
            this.keyword = keyword;
        }
    }

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
                String description = p.parseDescription(p::parseUntilEol);
                cmd = new AddTaskCommand(new TodoTask(description));
                break;
            }
            case DEADLINE: {
                String description = p.parseDescription(() -> p.parseUntil("/by"));
                String by = p.parseUntilEol();
                cmd = new AddTaskCommand(new DeadlineTask(description, by));
                break;
            }
            case EVENT: {
                String description = p.parseDescription(() -> p.parseUntil("/from"));
                String from = p.parseUntil("/to");
                String to = p.parseUntilEol();
                cmd = new AddTaskCommand(new EventTask(description, from, to));
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
                cmd = new RemoveTaskCommand(index);
                break;
            }
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means");
            }
            p.raiseIfTooManyArgument();
        } catch (ParserException ex) {
            throw new DukeException(kw.keyword + ": " + ex.getMessage());
        }
        return cmd;
    }

    private static class ParserException extends RuntimeException {
        ParserException(String message) {
            super(message);
        }
    }

    private String input;
    private int offset;
    private int length;

    /**
     * Internal implementation {@code null} will be returned if the parser fail.
     */
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
        for (CommandKeyword kw : CommandKeyword.values()) {
            if (keyword.equals(kw.keyword)) {
                return kw;
            }
        }
        return CommandKeyword.UNKNOWN;
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

    private String parseDescription(Supplier<String> parser) {
        String description = parser.get();
        if (description.isEmpty()) {
            throw new ParserException("description cannot be empty");
        }
        return description;
    }

    private int parseIntArgument() {
        skipWhitespaces();
        String intString = parseNonwhitespaces();
        return DukeUtils.convertStringToInt(intString)
                .orElseThrow(() -> new ParserException("expect an integer as argument"));

    }
}
