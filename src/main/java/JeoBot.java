import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

import jeo.database.Storage;
import jeo.database.TaskList;
import jeo.exception.JeoException;
import jeo.parser.Parser;
import jeo.task.Deadline;
import jeo.task.Event;
import jeo.task.Task;
import jeo.task.ToDo;
import jeo.ui.Ui;

/**
 * Represents the bot which the user may run.
 * @author Goh Jun How
 * @version 0.1
 */
public class JeoBot {
    protected static final String DATE_PARSE = "yyyy-MM-dd";
    protected Ui ui;
    protected Storage store;
    protected TaskList taskList;

    /**
     * Represents the list of commands recognised by the bot.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, DUE, FIND
    }

    /**
     * Creates the bot with the specified path to load tasks.
     * @param path String representing the file path.
     */
    public JeoBot(String path) {
        ui = new Ui();
        store = new Storage(path);
        try {
            taskList = new TaskList(store.load());
        } catch (FileNotFoundException | IllegalStateException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Executes commands which the user inputs and prints output message accordingly.
     */
    public void run() {
        ui.showGreetingMessage();
        boolean hasInput = true;
        Scanner sc = new Scanner(System.in);
        while (hasInput) {
            String s = sc.nextLine();
            try {
                ui.showBodyDivider();
                HashMap<String, String> hm = Parser.parseString(s);
                Command command = Command.valueOf(hm.get("command").toUpperCase());
                switch (command) {
                case BYE:
                    hasInput = false;
                    ui.showExitMessage();
                    break;
                case LIST:
                    ui.showAllTasks(taskList);
                    break;
                case MARK:
                    int index = Integer.parseInt(hm.get("index"));
                    Task task = taskList.getTaskAtIndex(index);
                    taskList.markTask(index);
                    ui.showTaskMarked(task);
                    break;
                case UNMARK:
                    index = Integer.parseInt(hm.get("index"));
                    task = taskList.getTaskAtIndex(index);
                    taskList.unmarkTask(index);
                    ui.showTaskUnmarked(task);
                    break;
                case DELETE:
                    index = Integer.parseInt(hm.get("index"));
                    task = taskList.getTaskAtIndex(index);
                    taskList.deleteTask(index);
                    ui.showTaskDeleted(task, taskList.getNumberOfTasks());
                    break;
                case TODO:
                    String desc = hm.get("description");
                    task = new ToDo(desc);
                    taskList.addTask(task);
                    ui.showTaskAdded(task, taskList.getNumberOfTasks());
                    break;
                case DEADLINE:
                    desc = hm.get("description");
                    String by = hm.get("by");
                    task = new Deadline(desc, by);
                    taskList.addTask(task);
                    ui.showTaskAdded(task, taskList.getNumberOfTasks());
                    break;
                case EVENT:
                    desc = hm.get("description");
                    String from = hm.get("from");
                    String to = hm.get("to");
                    task = new Event(desc, from, to);
                    taskList.addTask(task);
                    ui.showTaskAdded(task, taskList.getNumberOfTasks());
                    break;
                case DUE:
                    by = hm.get("by");
                    DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_PARSE);
                    LocalDate byDate = LocalDate.parse(by, formatterParse);
                    ui.showTasksDue(byDate, taskList);
                    break;
                case FIND:
                    String keyword = hm.get("key");
                    ui.showTasksWithKeyword(keyword, taskList);
                    break;
                default:
                    throw new IllegalStateException();
                }
                store.save(taskList.getTaskList());
            } catch (IOException e) {
                ui.showSavingError();
            } catch (IllegalArgumentException | IllegalStateException e) {
                ui.showInvalidCommand();
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexingError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeParsingError();
            } catch (JeoException e) {
                ui.showJeoErrorMessage(e.getMessage());
            } finally {
                ui.showBodyDivider();
            }
        }
        sc.close();
    }

    /**
     * Starts the bot running.
     * @param args String representing the command line arguments.
     */
    public static void main(String[] args) {
        new JeoBot("./data.txt").run();
    }
}
