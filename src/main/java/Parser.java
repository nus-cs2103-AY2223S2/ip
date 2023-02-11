/// Let parser throw exceptions for incorrect input
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
public class Parser {
    protected static String[] commands = {"bye", "list", "mark", "unmark", "delete", "todo", "deadline", "event"};

    public static boolean isValidCommand(String commandName) {
        return Arrays.asList(commands).contains(commandName);
    }

    public static Command parse(String fullcommand, Ui ui) throws DukeException {
        int firstWord = fullcommand.length() - 1;
        String commandName = "";
        try {
            firstWord = fullcommand.indexOf(' ');
            commandName = fullcommand.substring(0, firstWord);
        } catch (StringIndexOutOfBoundsException e) {
            commandName = fullcommand.trim();
        }

        if (!isValidCommand(commandName)) {
            throw new DukeException("Please enter a valid command");
        }

        String command = fullcommand.substring(firstWord + 1);

        switch (commandName) {
            case "bye":
            case "list": {
                return new Command(commandName);
            }
            case "mark":
            case "unmark":
            case "delete": {
                try {
                    int index = Integer.parseInt(command.trim());
                    return new UpdateCommand(commandName, index);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter the task number");
                }
                break;
            }
            case "todo": {
                String name = command.trim();
                if (name.isEmpty()) {
                    ui.todoFormatAlert();
                } else {
                    return new AddCommand("todo", name, null, null, null);
                }
                break;
            }
            case "deadline": {
                try {
                    int firstSlash = command.indexOf('/');
                    String name = command.substring(0, firstSlash);
                    String dateInfo = command.substring(firstSlash + 1);
                    if (name.isEmpty() || dateInfo.isEmpty()) {
                        throw new DukeException("the name or date information cannot be empty");
                    } else {
                        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("ddMMuuuu HHmm");
                        LocalDateTime by = LocalDateTime.parse(dateInfo, dateformatter);
                        return new AddCommand("deadline", name, by, null, null);
                    }
                } catch (DateTimeParseException | StringIndexOutOfBoundsException | DukeException e) {
                    ui.deadlineFormatAlert();
                }
            }
            case "event": {
                try {
                    int firstSlash = command.indexOf('/');
                    int lastSlash = command.lastIndexOf('/');
                    String name = command.substring(0, firstSlash);
                    String timeFrom = command.substring(firstSlash + 1, lastSlash - 1);
                    String timeTo = command.substring(lastSlash + 1);
                    if (name.isEmpty() || timeFrom.isEmpty() || timeTo.isEmpty()) {
                        throw new DukeException("the name or time information cannot be empty");
                    } else {
                        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("ddMMuuuu HHmm");
                        LocalDateTime from = LocalDateTime.parse(timeFrom, dateformatter);
                        LocalDateTime to = LocalDateTime.parse(timeTo, dateformatter);
                        return new AddCommand("event", name, null, from, to);
                    }
                } catch (DateTimeParseException | StringIndexOutOfBoundsException | DukeException e) {
                    ui.eventFormatAlert();
                }
                break;
            }
            default: {
                throw new DukeException("May I know what type of task this is?");
            }
        }
        throw new DukeException("Failed Command Generation");
    }
}
