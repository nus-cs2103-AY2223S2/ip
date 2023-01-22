import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
    public static Command parse(String input) throws DukeException {
        switch (input) {
            case "list":
                return new ListCommand();
            case "clear":
                return new ClearCommand();
            case "bye":
            case "exit":
                return new ExitCommand();
            default:
                // Have to do it at the starts with because what if "todo mark this as done"
                if (input.startsWith(Commands.MARK.cmd())) {
                    int taskNo = getNumbers(input) - 1;
                    return new MarkCommand(taskNo);
                } else if (input.startsWith(Commands.UNMARK.cmd())) {
                    int taskNo = getNumbers(input) - 1;
                    return new UnmarkCommand(taskNo);
                } else if (input.startsWith(Commands.TODO.cmd())) {
                    String title = input.substring(Commands.TODO.cmd().length());
                    return new TodoCommand(title);
                } else if (input.startsWith(Commands.DEADLINE.cmd())) {
                    if (input.indexOf(Commands.BY.cmd()) == -1) {
                        throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
                    }
                    String title = input.substring(Commands.DEADLINE.cmd().length(), input.indexOf(Commands.BY.cmd()));
                    String deadline = input.substring(input.indexOf(Commands.BY.cmd()));
                    return new DeadlineCommand(title, deadline);
                } else if (input.startsWith(Commands.EVENT.cmd())) {
                    if (input.indexOf(Commands.FROM.cmd()) == -1 || input.indexOf(Commands.TO.cmd()) == -1) {
                        throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
                    }
                    String title = input.substring(Commands.EVENT.cmd().length(), input.indexOf(Commands.FROM.cmd()));
                    String from = input.substring(input.indexOf(Commands.FROM.cmd()), input.indexOf(Commands.TO.cmd()));
                    String to = input.substring(input.indexOf(Commands.TO.cmd()));
                    return new EventCommand(title, from, to);
                } else if (input.startsWith(Commands.DEL.cmd())) {
                    int taskNo = getNumbers(input) - 1;
                    return new DeleteCommand(taskNo);
                } else {
                    throw new DukeException(Views.UNKNOWN_CMD_ERR_STRING.eng());
                }
        }
    }

    private static int getNumbers(String input) throws DukeException {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String numberString = matcher.group();
            int number = Integer.parseInt(numberString);
            return number;
        } else {
            throw new DukeException(Views.NO_INT_ERR_STRING.eng());
        }
    }
}
