import java.util.NoSuchElementException;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private enum Action {
        todo,
        deadline,
        event,
        list,
        mark,
        unmark,
        delete,
        bye
    }

    private enum TaskType {
        T,
        D,
        E
    }

    /**
     * Parses input data entered by user.
     *
     * @param   input   User input for the program menu.
     * @return  Status whether the program should exit or not.
     */
    public static Command parse(String input) throws DukeException, NoSuchElementException {
        String[] splitInput = input.split(" ");

        Command c;

        try {
            Action action = Action.valueOf(splitInput[0]);

            switch (action) {
                case todo:
                    if (splitInput.length < 2) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    Todo todo = addTodo(input.split(" ", 2)[1]);
                    c = new AddCommand(todo);
                    break;
                case deadline:
                    if (splitInput.length < 2) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    Deadline deadline = addDeadline(input.split(" ", 2)[1]);
                    c = new AddCommand(deadline);
                    break;
                case event:
                    if (splitInput.length < 2) {
                        throw new DukeException("The description of a event cannot be empty.");
                    }
                    Event event = addEvent(input.split(" ", 2)[1]);
                    c = new AddCommand(event);
                    break;
                case list:
                    c = new ListCommand();
                    break;
                case mark:
                    if (splitInput.length < 2) {
                        throw new DukeException("The task index cannot be empty.");
                    }
                    int markIdx = Integer.parseInt(splitInput[1]);
                    c = new MarkCommand(markIdx);
                    break;
                case unmark:
                    if (splitInput.length < 2) {
                        throw new DukeException("The task index cannot be empty.");
                    }
                    int unmarkIdx = Integer.parseInt(splitInput[1]);
                    c = new UnmarkCommand(unmarkIdx);
                    break;
                case delete:
                    if (splitInput.length < 2) {
                        throw new DukeException("The task index cannot be empty.");
                    }
                    c = new DeleteCommand(Integer.parseInt(splitInput[1]));
                    break;
                case bye:
                    c = new ExitCommand();
                    break;
                default:
                    c = new Command();
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }

        return c;
    }

    /**
     * Processes tasks from the list of tasks in file in the hard disk.
     *
     * @param input One task.
     * @throws DukeException
     * @throws NoSuchElementException
     */
    public Task processTask(String input, int idx) throws DukeException, NoSuchElementException {
        String[] splitInput = input.split(" ~ ");

        try {
            TaskType taskType = TaskType.valueOf(splitInput[0]);

            switch (taskType) {
                case T:
                    if (splitInput.length != 3) {
                        throw new DukeException("Todo task is of invalid format in the file.");
                    }
                    Todo todo = addTodo(splitInput[2]);
                    return todo;
                case D:
                    if (splitInput.length != 4) {
                        throw new DukeException("Deadline task is of invalid format in the file.");
                    }
                    Deadline deadline = addDeadline(String.format("%s /by %s"
                            , splitInput[2]
                            , splitInput[3]));
                    return deadline;
                case E:
                    if (splitInput.length != 5) {
                        throw new DukeException("Event task is of invalid format in the file.");
                    }
                    Event event = addEvent(String.format("%s /from %s /to %s"
                            , splitInput[2]
                            , splitInput[3]
                            , splitInput[4]));
                    return event;
            }
            if (splitInput[1].equals("1")) {
                new MarkCommand(idx);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }
        return null;
    }

    /**
     * Handles the adding of todo tasks.
     *
     * @param   taskDesc    Description of task.
     */
    private static Todo addTodo(String taskDesc) {
        Todo todo = new Todo(taskDesc);
        return todo;
    }

    /**
     * Handles the adding of deadline tasks.
     *
     * @param   taskDesc    Description of task.
     */
    private static Deadline addDeadline(String taskDesc) throws DukeException {
        Deadline deadline = new Deadline(taskDesc);
        return deadline;
    }

    /**
     * Handles the adding of event tasks.
     *
     * @param   taskDesc    Description of task.
     */
    private static Event addEvent(String taskDesc) throws DukeException {
        Event event = new Event(taskDesc);
        return event;
    }
}
