public class Parser {
    public Command parseCommand(String command) throws DukeException {
        String[] tokens = command.split(" ", 2);
        if (tokens.length == 1) {
            return handleSingleInput(tokens[0]);
        } else {
            return handleMultiInput(tokens[0], tokens[1]);
        }
    }

    public Command handleSingleInput(String command) throws InvalidInputException,
            IncorrectArgumentsException {
        switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
            case "unmark":
            case "delete":
            case "todo":
                throw new IncorrectArgumentsException(command, 1, 0);
            case "deadline":
                throw new IncorrectArgumentsException(command, 2, 0);
            case "event":
                throw new IncorrectArgumentsException(command, 3, 0);
            default:
                throw new InvalidInputException(command);
        }
    }

    public Command handleMultiInput(String command, String details) throws IncorrectArgumentsException,
            InvalidInputException {
        switch (command) {
            case "bye":
            case "list":
                throw new IncorrectArgumentsException(command, 0, 1);
            case "mark":
                int numToMark = Integer.parseInt(details);
                return new MarkCommand(numToMark);
            case "unmark":
                int numToUnmark = Integer.parseInt(details);
                return new UnmarkCommand(numToUnmark);
            case "delete":
                int numToDelete = Integer.parseInt(details);
                return new DeleteCommand(numToDelete);
            case "todo":
                return new ToDoCommand(details);
            case "deadline":
                String[] deadlineTokens = details.split("/");
                if (deadlineTokens.length != 2) {
                    throw new IncorrectArgumentsException("deadline", 2, deadlineTokens.length);
                }
                String DLtaskName = deadlineTokens[0];
                String DLdueTime = deadlineTokens[1];
                return new DeadlineCommand(DLtaskName, DLdueTime);
            case "event":
                String[] eventTokens = details.split("/");
                if (eventTokens.length != 3) {
                    throw new IncorrectArgumentsException("event", 3, eventTokens.length);
                }
                String EtaskName = eventTokens[0];
                String EstartTime = eventTokens[1];
                String EendTime = eventTokens[2];
                return new EventCommand(EtaskName, EstartTime, EendTime);
            default:
                throw new InvalidInputException(command);
        }
    }
}
