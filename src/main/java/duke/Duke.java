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

    /**
     * Responds to a given input message.
     *
     * @param input The input message.
     * @return An appropriate response.
     */
    public String getResponse(String input) {
        Task task;
        try {
            String[] parsedCommand = Parser.parseCommand(input);
            String command = parsedCommand[0];
            String response;
            switch (command) {
            case "bye":
                response = ui.getGoodbyeMessage();
                break;
            case "list":
                response = tasks.toString();
                break;
            case "mark":
                int taskNumber = Integer.parseInt(parsedCommand[1]);
                tasks.setDone(taskNumber, true);
                task = tasks.getTask(taskNumber);
                response = ui.getMarkTaskMessage(task);
                break;
            case "unmark":
                taskNumber = Integer.parseInt(parsedCommand[1]);
                tasks.setDone(taskNumber, false);
                task = tasks.getTask(taskNumber);
                response = ui.getUnmarkTaskMessage(task);
                break;
            case "delete":
                taskNumber = Integer.parseInt(parsedCommand[1]);
                task = tasks.getTask(taskNumber);
                tasks.deleteTask(taskNumber);
                response = ui.getDeleteTaskMessage(task, tasks);
                break;
            case "find":
                response = ui.getFindTaskMessage(tasks.findTasks(parsedCommand[1]));
                break;
            case "todo":
                int priority = Integer.parseInt(parsedCommand[2]);
                if (priority <= 0) {
                    throw new InvalidCommandException("Priority must be a positive integer.");
                }
                task = new ToDo(parsedCommand[1], priority);
                tasks.addTask(task);
                response = ui.getAddTaskMessage(task, tasks);
                break;
            case "deadline":
                priority = Integer.parseInt(parsedCommand[2]);
                if (priority <= 0) {
                    throw new InvalidCommandException("Priority must be a positive integer.");
                }
                task = new Deadline(parsedCommand[1], priority, Parser.parseDate(parsedCommand[3], false));
                tasks.addTask(task);
                response = ui.getAddTaskMessage(task, tasks);
                break;
            case "event":
                priority = Integer.parseInt(parsedCommand[2]);
                if (priority <= 0) {
                    throw new InvalidCommandException("Priority must be a positive integer.");
                }
                task = new Event(parsedCommand[1], priority, Parser.parseDate(parsedCommand[3], false),
                    Parser.parseDate(parsedCommand[4], false));
                tasks.addTask(task);
                response = ui.getAddTaskMessage(task, tasks);
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
