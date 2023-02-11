package duke;
import java.io.IOException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import exception.DukeException;



/**
 * Represents parser for Duke, parses user inputs and reacts accordingly
 */
public class Parser {
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Creates new Parser object
     *
     * @param tasks TaskList of current user
     * @param storage Storage of current user
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Parses user inputs and reacts according to different keywords.
     *
     * @param command Users' inputs
     * @return Duke's response
     */
    public String parse(String command) {
        String[] commandArr;
        Actions selection;
        String response;
        try {
            commandArr = command.split(" ");
            selection = Actions.valueOf(commandArr[0].toUpperCase());
            switch (selection) {
            case LIST:
                response = parsePrint();
                break;
            case MARK:
            case UNMARK:
                response = parseMark(command);
                break;
            case TODO:
                response = parseTodo(command);
                break;
            case DEADLINE:
                response = parseDeadline(command);
                break;
            case EVENT:
                response = parseEvent(command);
                break;
            case DELETE:
                response = parseDelete(command);
                break;
            case FIND:
                response = parseFind(command);
                break;
            case BYE:
                response = parseBye();
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            storage.saveTasks();
        } catch (DukeException | IOException e) {
            return String.valueOf(e);
        } catch (IllegalArgumentException e) {
            return "Please enter a valid action!";
        }
        return response;
    }

    private String parsePrint() {
        return tasks.printList();
    }

    private String parseMark(String command) {
        String[] commandArr = command.split(" ");
        Task task = tasks.getTask(Integer.parseInt(commandArr[1]) - 1);
        if (Actions.valueOf(commandArr[0].toUpperCase()) == Actions.MARK) {
            return task.markDone();
        } else if (Actions.valueOf(commandArr[0].toUpperCase()) == Actions.UNMARK) {
            return task.markNotDone();
        } else {
            return "Invalid action!";
        }
    }

    private String parseTodo(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        if (commandArr.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(command.substring(5));
        return tasks.addTask(todo, false);
    }

    private String parseDeadline(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        if (commandArr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] deadlineInfo = command.substring(9).split(" /by ");
        if (deadlineInfo.length < 2) {
            throw new DukeException("Deadline cannot be empty.");
        }
        Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
        return tasks.addTask(deadline, false);
    }

    private String parseEvent(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        if (commandArr.length < 2) {
            throw new DukeException("The description of a event cannot be empty.");
        }

        String[] eventInfo = command.substring(6).split(" /from ");
        if (eventInfo.length < 2) {
            throw new DukeException("Event start time and end time are required.");
        }

        String[] eventTime = eventInfo[1].split(" /to ");
        if (eventTime.length < 2) {
            throw new DukeException("Event start time and end time are required.");
        }

        Event event = new Event(eventInfo[0], eventTime[0], eventTime[1]);
        return tasks.addTask(event, false);
    }

    private String parseDelete(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        if (commandArr.length < 2) {
            throw new DukeException("You must choose a task to delete");
        }
        int taskNumber = Integer.parseInt(commandArr[1]);
        if (taskNumber > tasks.getSize() || taskNumber <= 0) {
            throw new DukeException("No such task found");
        }
        return tasks.deleteTask(taskNumber - 1);
    }

    private String parseFind(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        if (commandArr.length < 2) {
            throw new DukeException("You must enter a keyword to find");
        }
        command = command.substring(5);
        String[] keywords = command.split(" ");
        return tasks.findMatchingTasks(keywords);
    }

    private String parseBye() {
        return "Bye bye!";
    }

    enum Actions { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, BYE }

}
