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
        Task chosen;
        Task created;
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
                int id = Parser.parseTask(Parser.getArgs());
                chosen = tasks.getTask(id);
                chosen.mark();
                response = ui.markTaskMessage(chosen);
                break;
            }
            case UNMARK: {
                int id = Parser.parseTask(Parser.getArgs());
                chosen = tasks.getTask(id);
                chosen.unmark();
                response = ui.unmarkTaskMessage(chosen);
                break;
            }
            case DELETE: {
                int id = Parser.parseTask(Parser.getArgs());
                chosen = tasks.getTask(id);
                tasks.deleteTask(chosen);
                response = ui.deleteTaskMessage(chosen, tasks);
                break;
            }
            case TODO: {
                created = Parser.parseTodo(Parser.getArgs());
                tasks.addTask(created);
                response = ui.addedTaskMessage(created, tasks);
                break;
            }
            case DEADLINE: {
                created = Parser.parseDeadline(Parser.getArgs());
                tasks.addTask(created);
                response = ui.addedTaskMessage(created, tasks);
                break;
            }
            case EVENT: {
                created = Parser.parseEvent(Parser.getArgs());
                tasks.addTask(created);
                response = ui.addedTaskMessage(created, tasks);
                break;
            }
            default:
                assert false;
                break;
            }
        } catch (DukeException e) {
            response = ui.showErrorMessage(e);
        }
        return response;
    }
}
