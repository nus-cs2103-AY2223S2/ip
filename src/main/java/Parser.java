import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Parser {
    public static Command parse(String command) throws IncompleteInputException,
            InvalidFormatException, InvalidInputException {
        try {
            String[] inputs = command.split(" ", 2);
            switch (inputs[0]) {
            case "bye" :
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(inputs[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(inputs[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(inputs[1]));
            case "todo":
                return new AddCommand(new ToDo(inputs[1]));
            case "deadline":
                String[] deadlineDetails = inputs[1].split("/by ", 2);
                return new AddCommand(new Deadline(deadlineDetails[0],
                        parseDateTime(deadlineDetails[1])));
            case "event":
                String[] eventDetails = inputs[1].split("/from | /to ", 3);
                return new AddCommand(new Event(eventDetails[0], parseDateTime(eventDetails[1]),
                        parseDateTime(eventDetails[2])));
            default:
                throw new InvalidInputException("Sorry, this command is not in Book's dictionary.");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IncompleteInputException("This command is missing details");
        } catch (DateTimeParseException exception) {
            throw new InvalidFormatException("Please use the format dd/MM/yy-HHmm");
        }
    }
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        return LocalDateTime.parse(dateTime, format);
    }
}
