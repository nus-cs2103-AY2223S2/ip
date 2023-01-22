import java.util.Arrays;

public class CommandParser extends Parser {

    final static String BANNER = "____________________________________________________________";

    public Command parse(String response) throws UnknownCommandException {
        String[] commands = response.split(" ");
        System.out.println(BANNER);
        try {
            if (commands.length < 2 && isIndexRequiredCommand(commands[0])) {
                throw new IncompleteCommandException(String.format("Hrrmmm. Not enough arguments, " +
                        "%s has. Hmm", commands[0]), null);
            }
            Commands command = Commands.valueOf(commands[0].toUpperCase());
            switch (command) {
                case LIST:
                    return new ListCommand();
                case MARK:
                    return new MarkTaskCommand(commands[1]);
                case UNMARK:
                    return new UnmarkTaskCommand(commands[1]);
                case DELETE:
                    return new DeleteTaskCommand(commands[1]);
                case BYE:
                    return new ExitCommand();
                case TODO:
                case DEADLINE:
                case EVENT:
                    return new AddTaskCommand(response);
                default :
                    throw new UnknownCommandException("Fall to the Dark Side, you must not. Invalid Command!", null);
                }
        } catch (DukeException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Fall to the Dark Side, you must not. Invalid Command!", null);
        }
    }

    public static boolean isIndexRequiredCommand(String command) {
        return command.equals("mark")
                || command.equals("unmark")
                || command.equals("delete");
    }
}
