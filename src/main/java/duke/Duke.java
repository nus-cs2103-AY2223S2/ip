package duke;

import exceptions.DukeException;
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
     * Respond to a given input message.
     * @param input The input message.
     * @return An appropriate response.
     */
    public String getResponse(String input) {
        Task task;
        int taskNumber;
        try {
            String[] parsedCommand = Parser.parseCommand(input);
            String command = parsedCommand[0];
            switch (command) {
            case "bye":
                return ui.getGoodbyeMessage();
            case "list":
                return tasks.toString();
            case "mark":
                taskNumber = Integer.parseInt(parsedCommand[1]);
                tasks.setDone(taskNumber, true);
                task = tasks.getTask(taskNumber);
                return ui.getMarkTaskMessage(task);
            case "unmark":
                taskNumber = Integer.parseInt(parsedCommand[1]);
                tasks.setDone(taskNumber, false);
                task = tasks.getTask(taskNumber);
                return ui.getUnmarkTaskMessage(task);
            case "delete":
                taskNumber = Integer.parseInt(parsedCommand[1]);
                task = tasks.getTask(taskNumber);
                tasks.deleteTask(taskNumber);
                return ui.getDeleteTaskMessage(task, tasks);
            case "find":
                return ui.getFindTaskMessage(tasks.findTasks(parsedCommand[1]));
            case "todo":
                task = new ToDo(parsedCommand[1]);
                tasks.addTask(task);
                return ui.getAddTaskMessage(task, tasks);
            case "deadline":
                task = new Deadline(parsedCommand[1], Parser.parseDate(parsedCommand[2], false));
                tasks.addTask(task);
                return ui.getAddTaskMessage(task, tasks);
            case "event":
                task = new Event(parsedCommand[1], Parser.parseDate(parsedCommand[2], false),
                    Parser.parseDate(parsedCommand[3], false));
                tasks.addTask(task);
                return ui.getAddTaskMessage(task, tasks);
            default:
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (Exception e) {
            return ui.getErrorMessage(e.toString());
        } finally {
            try {
                // After each command, save the current task list to the file
                storage.saveTasks(tasks);
            } catch (Exception e) {
                System.out.println(ui.getErrorMessage(e.toString()));
            }
        }
    }
}
