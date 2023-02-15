package duke;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import exceptions.UnknownCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * The main class, includes logic to run the chatbot.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new chatbot.
     *
     * @param dataFilePath Path to store the task list text file.
     */
    public Duke(String dataFilePath) {
        ui = new Ui();
        assert dataFilePath != null && !dataFilePath.equals("") : "dataFilePath is null or empty";
        storage = new Storage(dataFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(ui.getLoadingError());
            tasks = new TaskList();
        }
    }

    private String handleByeCommand() {
        return ui.getGoodbyeMessage();
    }

    private String handleListCommand() {
        return tasks.toString();
    }

    private String handleMarkCommand(String taskIndex) throws DukeException {
        int taskNumber = Integer.parseInt(taskIndex);
        tasks.setDone(taskNumber, true);
        Task task = tasks.getTask(taskNumber);
        return ui.getMarkTaskMessage(task);
    }

    private String handleUnmarkCommand(String taskIndex) throws DukeException {
        int taskNumber = Integer.parseInt(taskIndex);
        tasks.setDone(taskNumber, false);
        Task task = tasks.getTask(taskNumber);
        return ui.getUnmarkTaskMessage(task);
    }

    private String handleDeleteCommand(String taskIndex) throws DukeException {
        int taskNumber = Integer.parseInt(taskIndex);
        Task task = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        return ui.getDeleteTaskMessage(task, tasks);
    }

    private String handleFindCommand(String searchString) {
        return ui.getFindTaskMessage(tasks.findTasks(searchString));
    }

    private String handleTodoCommand(String priorityStr, String description) throws DukeException {
        int priority = Integer.parseInt(priorityStr);
        if (priority <= 0) {
            throw new InvalidCommandException("Priority must be a positive integer.");
        }
        Task task = new ToDo(description, priority);
        tasks.addTask(task);
        return ui.getAddTaskMessage(task, tasks);
    }

    private String handleDeadlineCommand(String priorityStr, String description, String deadline) throws DukeException {
        int priority = Integer.parseInt(priorityStr);
        if (priority <= 0) {
            throw new InvalidCommandException("Priority must be a positive integer.");
        }
        Task task = new Deadline(description, priority, Parser.parseDate(deadline, false));
        tasks.addTask(task);
        return ui.getAddTaskMessage(task, tasks);
    }

    private String handleEventCommand(String priorityStr, String description, String startTime, String endTime)
            throws DukeException {
        int priority = Integer.parseInt(priorityStr);
        if (priority <= 0) {
            throw new InvalidCommandException("Priority must be a positive integer.");
        }
        Task task = new Event(description, priority, Parser.parseDate(startTime, false),
            Parser.parseDate(endTime, false));
        tasks.addTask(task);
        return ui.getAddTaskMessage(task, tasks);
    }

    /**
     * Responds to a given input message.
     *
     * @param input The input message.
     * @return An appropriate response.
     */
    public String getResponse(String input) {
        try {
            String[] parsedCommand = Parser.parseCommand(input);
            String command = parsedCommand[0];
            String response;
            switch (command) {
            case "bye":
                response = handleByeCommand();
                break;
            case "list":
                response = handleListCommand();
                break;
            case "mark":
                response = handleMarkCommand(parsedCommand[1]);
                break;
            case "unmark":
                response = handleUnmarkCommand(parsedCommand[1]);
                break;
            case "delete":
                response = handleDeleteCommand(parsedCommand[1]);
                break;
            case "find":
                response = handleFindCommand(parsedCommand[1]);
                break;
            case "todo":
                response = handleTodoCommand(parsedCommand[2], parsedCommand[1]);
                break;
            case "deadline":
                response = handleDeadlineCommand(parsedCommand[2], parsedCommand[1], parsedCommand[3]);
                break;
            case "event":
                response = handleEventCommand(parsedCommand[2], parsedCommand[1], parsedCommand[3], parsedCommand[4]);
                break;
            default:
                throw new UnknownCommandException();
            }

            // After each command, save the current task list to the file
            storage.saveTasks(tasks);
            return response;
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.getErrorMessage(e.toString());
        }
    }
}
