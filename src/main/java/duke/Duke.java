package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a Command Line Application which acts as a Task Manager.
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
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Opens up the Task Manager to taking in input and executing commands.
     *
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        Task chosen;
        Task created;
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = scanner.nextLine();
                Parser.parseUserResponse(userInput);
                switch(Parser.getCommand()) {
                case BYE: {
                    storage.saveToFile(tasks);
                    ui.showGoodbyeMessage();
                    isExit = true;
                    break;
                }
                case FIND: {
                    String keyword = Parser.getArgs()[1];
                    ArrayList<Task> matchingTasks = tasks.getMatchingTasks(keyword);
                    ui.showMatchingTasksMessage(matchingTasks);
                    break;
                }
                case LIST: {
                    ui.showTasksMessage(tasks);
                    break;
                }
                case MARK: {
                    int id = Parser.parseTask(Parser.getArgs());
                    chosen = tasks.getTask(id);
                    chosen.mark();
                    ui.markTaskMessage(chosen);
                    break;
                }
                case UNMARK: {
                    int id = Parser.parseTask(Parser.getArgs());
                    chosen = tasks.getTask(id);
                    chosen.unmark();
                    ui.unmarkTaskMessage(chosen);
                    break;
                }
                case DELETE: {
                    int id = Parser.parseTask(Parser.getArgs());
                    chosen = tasks.getTask(id);
                    tasks.deleteTask(chosen);
                    ui.deleteTaskMessage(chosen, tasks);
                    break;
                }
                case TODO: {
                    created = Parser.parseTodo(Parser.getArgs());
                    tasks.addTask(created);
                    ui.addedTaskMessage(created, tasks);
                    break;
                }
                case DEADLINE: {
                    created = Parser.parseDeadline(Parser.getArgs());
                    tasks.addTask(created);
                    ui.addedTaskMessage(created, tasks);
                    break;
                }
                case EVENT: {
                    created = Parser.parseEvent(Parser.getArgs());
                    tasks.addTask(created);
                    ui.addedTaskMessage(created, tasks);
                    break;
                }
                default:
                    // Switch will not reach here
                    break;
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Duke("/duke.txt").run();
    }
}
