package duke;

/**
 * Object class for Parser
 * Parser acts like a compiler which translates user input into command
 */
public class Parser {
    private static final int TODO_LENGTH = 4;
    Parser() {}

    /**
     * Parse the input into commands which system understand
     * @param input -> Raw input from user
     * @return command object
     * @throws DukeException -> Input doesn't match with any known commands.
     */
    public Command parse(String input) throws DukeException {
        int startIndex;
        Task newTask;
        String description;
        input = input.trim();
        // command bye
        if (input.equals("bye")) {
            return new ByeCommand();
        }
        // command list
        if (input.equals("list")) {
            return new ListCommand();
        }
        // Get the substring of command and store in header
        int firstBlank = input.indexOf(" ");
        // No description of task
        if (firstBlank == -1) {
            throw new DukeException(ExceptionType.DESCRIPTION_EMPTY);
        }
        String header = input.substring(0, firstBlank);
        // command todo
        if (header.equals("todo")) {
            startIndex = TODO_LENGTH + 1;
            description = input.substring(startIndex);
            newTask = new ToDo(description);
            return new ToDoCommand(newTask);
        }
        // command deadline
        if (header.equals("deadline")) {
            newTask = Deadline.createDeadline(input);
            return new DeadlineCommand(newTask);
        }
        // command event
        if (header.equals("event")) {
            newTask = Event.createEvent(input);
            return new EventCommand(newTask);
        }
        // command delete
        if (header.equals("delete")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.NO_NUMBER);
            }
        }
        // command mark
        if (header.equals("mark")) {
            try {
                int index = Integer.parseInt(input.substring(5)) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.NO_NUMBER);
            }
        }
        // command unmark
        if (header.equals("unmark")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.NO_NUMBER);
            }
        }
        if (header.equals("find")) {
            String keywords = input.substring(5);
            return new FindCommand(keywords);
        }
        throw new DukeException(ExceptionType.UNCLEAR_COMMAND);
    }
}
