package duke.parser;

import duke.exception.DukeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.NoSuchElementException;

/**
 * Makes sense of the user command entered into Duke.
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
        find,
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
     * @param input User input for the program menu.
     * @return Command that user entered.
     * @throws DukeException
     * @throws NoSuchElementException
     */
    public static Command parse(String input) throws DukeException, NoSuchElementException {
        String[] splitInputs = input.split(" ");
        Command c;

        try {
            Action action = Action.valueOf(splitInputs[0]);

            switch (action) {
            case todo:
                if (splitInputs.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Todo todo = addTodo(input.split(" ", 2)[1]);
                c = new AddCommand(todo);
                break;
            case deadline:
                if (splitInputs.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                Deadline deadline = addDeadline(input.split(" ", 2)[1]);
                c = new AddCommand(deadline);
                break;
            case event:
                if (splitInputs.length < 2) {
                    throw new DukeException("The description of a event cannot be empty.");
                }
                Event event = addEvent(input.split(" ", 2)[1]);
                c = new AddCommand(event);
                break;
            case list:
                c = new ListCommand();
                break;
            case mark:
                if (splitInputs.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                int markIndex = Integer.parseInt(splitInputs[1]);
                c = new MarkCommand(markIndex);
                break;
            case unmark:
                if (splitInputs.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                int unmarkIndex = Integer.parseInt(splitInputs[1]);
                c = new UnmarkCommand(unmarkIndex);
                break;
            case delete:
                if (splitInputs.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                c = new DeleteCommand(Integer.parseInt(splitInputs[1]));
                break;
            case bye:
                c = new ExitCommand();
                break;
            case find:
                if (splitInputs.length < 2) {
                    throw new DukeException("You must include the keyword you wish to search.");
                }
                c = new FindCommand(input.split(" ", 2)[1]);
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
     * Processes tasks from the list of tasks in file in the local storage.
     *
     * @param input One task to be processed.
     * @param taskIndex Index of task.
     * @throws DukeException
     * @throws NoSuchElementException
     */
    public Task processTask(String input, int taskIndex) throws DukeException, NoSuchElementException {
        String[] splitInputs = input.split(" ~ ");

        try {
            TaskType taskType = TaskType.valueOf(splitInputs[0]);

            switch (taskType) {
                case T:
                    if (splitInputs.length != 3) {
                        throw new DukeException("duke.Todo task is of invalid format in the file.");
                    }
                    Todo todo = addTodo(splitInputs[2]);
                    return todo;
                case D:
                    if (splitInputs.length != 4) {
                        throw new DukeException("duke.Deadline task is of invalid format in the file.");
                    }
                    Deadline deadline = addDeadline(String.format("%s /by %s"
                            , splitInputs[2]
                            , splitInputs[3]));
                    return deadline;
                case E:
                    if (splitInputs.length != 5) {
                        throw new DukeException("duke.Event task is of invalid format in the file.");
                    }
                    Event event = addEvent(String.format("%s /from %s /to %s"
                            , splitInputs[2]
                            , splitInputs[3]
                            , splitInputs[4]));
                    return event;
            }
            if (splitInputs[1].equals("1")) {
                new MarkCommand(taskIndex);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }
        return null;
    }

    /**
     * Handles the adding of Todo tasks.
     *
     * @param taskDescription Description of task.
     * @return Todo that has been added to task list.
     */
    private static Todo addTodo(String taskDescription) {
        Todo todo = new Todo(taskDescription);
        return todo;
    }

    /**
     * Handles the adding of Deadline tasks.
     *
     * @param taskDescription Description of deadline.
     * @return Deadline that has been added to task list.
     */
    private static Deadline addDeadline(String taskDescription) throws DukeException {
        Deadline deadline = new Deadline(taskDescription);
        return deadline;
    }

    /**
     * Handles the adding of Event tasks.
     *
     * @param taskDescription Description of Event.
     * @return Event that has been added to task list.
     */
    private static Event addEvent(String taskDescription) throws DukeException {
        Event event = new Event(taskDescription);
        return event;
    }
}
