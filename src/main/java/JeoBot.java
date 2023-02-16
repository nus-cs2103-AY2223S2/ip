import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

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
 * @version 0.3
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
            taskList = new TaskList();
            ui.showLoadingErrorMessage();
        }
    }

    /**
     * Executes commands which the user inputs and returns output message accordingly.
     * @param input String representing the input message.
     * @return The output message.
     */
    public String run(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            HashMap<String, String> hm = Parser.parseString(input);
            Command command = Command.valueOf(hm.get("command").toUpperCase());
            switch (command) {
            case BYE:
                sb.append(ui.exitMessage());
                break;
            case LIST:
                sb.append(ui.showAllTasks(taskList));
                break;
            case MARK:
                assert hm.containsKey("index");
                int index = Integer.parseInt(hm.get("index"));
                Task task = taskList.getTaskAtIndex(index);
                taskList.markTask(index);
                sb.append(ui.taskMarkedMessage(task));
                break;
            case UNMARK:
                assert hm.containsKey("index");
                index = Integer.parseInt(hm.get("index"));
                task = taskList.getTaskAtIndex(index);
                taskList.unmarkTask(index);
                sb.append(ui.taskUnmarkedMessage(task));
                break;
            case DELETE:
                assert hm.containsKey("index");
                index = Integer.parseInt(hm.get("index"));
                task = taskList.getTaskAtIndex(index);
                taskList.deleteTask(index);
                sb.append(ui.taskDeletedMessage(task, taskList.getNumberOfTasks()));
                break;
            case TODO:
                assert hm.containsKey("description");
                String desc = hm.get("description");
                String tags = hm.get("tags");
                task = new ToDo(desc, tags);
                taskList.addTask(task);
                sb.append(ui.taskAddedMessage(task, taskList.getNumberOfTasks()));
                break;
            case DEADLINE:
                assert hm.containsKey("description");
                assert hm.containsKey("by");
                desc = hm.get("description");
                String by = hm.get("by");
                tags = hm.get("tags");
                task = new Deadline(desc, by, tags);
                taskList.addTask(task);
                sb.append(ui.taskAddedMessage(task, taskList.getNumberOfTasks()));
                break;
            case EVENT:
                assert hm.containsKey("description");
                assert hm.containsKey("from");
                assert hm.containsKey("to");
                desc = hm.get("description");
                String from = hm.get("from");
                String to = hm.get("to");
                tags = hm.get("tags");
                task = new Event(desc, from, to, tags);
                taskList.addTask(task);
                sb.append(ui.taskAddedMessage(task, taskList.getNumberOfTasks()));
                break;
            case DUE:
                assert hm.containsKey("by");
                by = hm.get("by");
                DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_PARSE);
                LocalDate byDate = LocalDate.parse(by, formatterParse);
                sb.append(ui.showTasksDue(byDate, taskList));
                break;
            case FIND:
                assert hm.containsKey("key");
                String keyword = hm.get("key");
                sb.append(ui.showTasksWithKeyword(keyword, taskList));
                break;
            default:
                throw new IllegalStateException();
            }
            store.save(taskList.getTaskList());
        } catch (IOException e) {
            sb.append(ui.savingErrorMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            sb.append(ui.invalidCommandMessage());
        } catch (IndexOutOfBoundsException e) {
            sb.append(ui.indexingErrorMessage());
        } catch (DateTimeParseException e) {
            sb.append(ui.dateTimeParsingErrorMessage());
        } catch (JeoException e) {
            sb.append(ui.jeoErrorMessage(e.getMessage()));
        }
        return sb.toString();
    }

    /**
     * Returns the response from JeoBot.
     *
     * @param input The input from the user.
     * @return The response message of JeoBot.
     */
    public String getResponse(String input) {
        return run(input);
    }
}
