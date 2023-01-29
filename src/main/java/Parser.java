public class Parser {
    private static final int TODO_LENGTH = 4;
    Parser() {}

    public Command parse(String input) throws DukeException {
        int startIndex;
        Task newTask;
        String description;
        input = input.trim();
        if (input.equals("bye")) {
            return new ByeCommand();
        }
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

        if (header.equals("todo")) {
            startIndex = TODO_LENGTH + 1;
            description = input.substring(startIndex);
            newTask = new ToDo(description);
            return new ToDoCommand(newTask);
        }
        if (header.equals("deadline")) {
            newTask = Deadline.createDeadline(input);
            return new DeadlineCommand(newTask);
        }
        if (header.equals("Event")) {
            newTask = Event.createEvent(input);
            return new EventCommand(newTask);
        }
        if (header.equals("delete")) {
            int index;
            try {
                index = Integer.parseInt(input.substring(7));
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.TASK_NOT_EXIST);
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.NO_NUMBER);
            }
        }
        if (header.equals("mark")) {
            try {
                int index = Integer.parseInt(input.substring(5));
                return new MarkCommand(index);
            } catch (IndexOutOfBoundsException IOBE) {
                throw new DukeException(ExceptionType.TASK_NOT_EXIST);
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.NO_NUMBER);
            }
        }
        if (header.equals("unmark")) {
            try {
                int index = Integer.parseInt(input.substring(7));
                return new MarkCommand(index);
            } catch (IndexOutOfBoundsException IOBE) {
                throw new DukeException(ExceptionType.TASK_NOT_EXIST);
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.NO_NUMBER);
            }
        }
    }
}
