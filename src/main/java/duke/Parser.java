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
     * Parses user inputs and reacts according to different keywords
     *
     * @param command Users' inputs
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
                response = tasks.printList();
                break;
            case MARK:
                Task t1 = tasks.getTask(Integer.parseInt(commandArr[1]) - 1);
                response = t1.markDone();
                break;
            case UNMARK:
                Task t2 = tasks.getTask(Integer.parseInt(commandArr[1]) - 1);
                response = t2.markNotDone();
                break;
            case TODO:
                if (commandArr.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Todo todo = new Todo(command.substring(5));
                response = tasks.addTask(todo, false);
                break;
            case DEADLINE:
                if (commandArr.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                String[] deadlineInfo = command.substring(9).split(" /by ");
                if (deadlineInfo.length < 2) {
                    throw new DukeException("Deadline cannot be empty.");
                }
                Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                response = tasks.addTask(deadline, false);
                break;
            case EVENT:
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
                response = tasks.addTask(event, false);
                break;
            case DELETE:
                if (commandArr.length < 2) {
                    throw new DukeException("You must choose a task to delete");
                }
                int taskNumber = Integer.parseInt(commandArr[1]);
                if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                    throw new DukeException("No such task found");
                }
                response = tasks.deleteTask(taskNumber - 1);
                break;
            case FIND:
                if (commandArr.length < 2) {
                    throw new DukeException("You must enter a keyword to find");
                }
                TaskList filtered = new TaskList(tasks.findMatchingTasks(commandArr[1]));
                response = filtered.printList();
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

    enum Actions { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND }

}
