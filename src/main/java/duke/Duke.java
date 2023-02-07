package duke;

/**
 * Encapsulates a GUI Application which acts as a Task Manager.
 *
 * @author Sean Chin Jun Kai
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor to initialise the Task Manager.
     *
     * @param filePath the path of the file where we want to store the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the result string to be shown to user in GUI
     * based on user's input.
     *
     * @param input text that user enters into GUI.
     * @return string to be displayed to user.
     */

    public String getResponse(String input) {
        Task selected;
        String response = null;

        try {
            Parser.parseUserResponse(input);
            switch (Parser.getCommand()) {
            case BYE: {
                storage.saveToFile(tasks);
                response = ui.showGoodbyeMessage();
                break;
            }
            case FIND: {
                String keyword = Parser.getArgs()[1];
                response = tasks.getMatchingTasksString(keyword);
                break;
            }
            case LIST: {
                response = ui.showTasksMessage(tasks);
                break;
            }
            case MARK: {
                int id = Parser.parseTask();
                selected = tasks.getTask(id);
                selected.mark();
                response = ui.markTaskMessage(selected);
                break;
            }
            case UNMARK: {
                int id = Parser.parseTask();
                selected = tasks.getTask(id);
                selected.unmark();
                response = ui.unmarkTaskMessage(selected);
                break;
            }
            case DELETE: {
                int id = Parser.parseTask();
                selected = tasks.getTask(id);
                tasks.deleteTask(selected);
                response = ui.deleteTaskMessage(selected, tasks);
                break;
            }
            case TODO: {
                selected = Parser.parseTodo();
                tasks.addTask(selected);
                response = ui.addedTaskMessage(selected, tasks);
                break;
            }
            case DEADLINE: {
                selected = Parser.parseDeadline();
                tasks.addTask(selected);
                response = ui.addedTaskMessage(selected, tasks);
                break;
            }
            case EVENT: {
                selected = Parser.parseEvent();
                tasks.addTask(selected);
                response = ui.addedTaskMessage(selected, tasks);
                break;
            }
            default:
                break;
            }
        } catch (DukeException e) {
            response = ui.showErrorMessage(e);
        }
        return response;
    }
}
