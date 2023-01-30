import java.time.LocalDate;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Handles Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    /**
     * Handles identifying task type.
     */
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    private static final TaskList taskList = new TaskList();
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage(taskList);
    private static final Parser parser = new Parser();

    /**
     * Adds task into task list.
     *
     * @param description Description of task to be added.
     * @param taskType Task type of task to be added.
     */
    private static void addTask(String description, TaskType taskType) {
        switch (taskType) {
        case TODO:
            Todo todoTask = new Todo(description);
            taskList.add(todoTask);
            ui.displayAdded(todoTask.toString(), taskList.size());
            break;
        case EVENT:
            try {
                String modifiedDescription = parser.getPostDescription(description, " /from ");
                LocalDate fromDate = parser.getDateTime(parser.getPreDescription(modifiedDescription, " /to "));
                LocalDate toDate = parser.getDateTime(parser.getPostDescription(modifiedDescription, " /to "));
                Event eventTask = new Event(parser.getPreDescription(description, " /from "), fromDate, toDate);
                taskList.add(eventTask);
                ui.displayAdded(eventTask.toString(), taskList.size());
                break;
            } catch (Exception e) {
                ui.displayInvalidFromToFormat();
                break;
            }
        case DEADLINE:
            try {
                Deadline deadlineTask = new Deadline(parser.getPreDescription(description, " /by "),
                        parser.getDateTime(parser.getPostDescription(description, " /by ")));
                taskList.add(deadlineTask);
                ui.displayAdded(deadlineTask.toString(), taskList.size());
                break;
            } catch (Exception e) {
                ui.displayInvalidByFormat();
                break;
            }
        default:
            break;
        }
        storage.saveToFile();
    }

    /**
     * Marks task from task list.
     *
     * @param itemNo Item number in list to be marked.
     */
    private static void mark(int itemNo) throws DukeException {
        taskList.get(itemNo).setDone(true);
        ui.displayMarked(taskList.get(itemNo).toString());
        storage.saveToFile();
    }

    /**
     * Unmarks task from task list.
     *
     * @param itemNo Item number in list to be unmarked.
     */
    private static void unmark(int itemNo) {
        taskList.get(itemNo).setDone(false);
        ui.displayUnmarked(taskList.get(itemNo).toString());
        storage.saveToFile();
    }

    /**
     * Deletes task from task list.
     *
     * @param itemNo Item number in list to be deleted.
     */
    private static void delete(int itemNo) {
        ui.displayDeleted(taskList.get(itemNo).toString());
        taskList.remove(itemNo);
        storage.saveToFile();
        ui.displayTotalNumList(taskList.size());
    }

    /**
     * Searches task containing search inputs.
     *
     * @param searchInput Substring to find if any tasks contains it.
     */
    private static void find(String searchInput) {
        ui.displayResults(taskList, searchInput);
    }


    /**
     * Entry point to Duke application where Duke is initialized.
     *
     * @param args Unused arguments that user optionally provides when Duke is launched.
     */
    public static void main(String[] args) {
        ui.displayIntro();
        boolean isQuit = false;
        storage.initalizeList();

        while (!isQuit) {
            ui.displayRepeatedBlank();
            String userInput = ui.getUserInput();
            String cmd = parser.getCmd(userInput);

            try {
                switch (cmd) {
                case "bye":
                    isQuit = true;
                    ui.displayBye();
                    break;
                case "list":
                    ui.displayList(taskList);
                    break;
                case "mark":
                    mark(Integer.parseInt(parser.getDescription(userInput)) - 1);
                    break;
                case "unmark":
                    unmark(Integer.parseInt(parser.getDescription(userInput)) - 1);
                    break;
                case "todo":
                    addTask(parser.getDescription(userInput), TaskType.TODO);
                    break;
                case "event":
                    addTask(parser.getDescription(userInput), TaskType.EVENT);
                    break;
                case "deadline":
                    addTask(parser.getDescription(userInput), TaskType.DEADLINE);
                    break;
                case "delete":
                    delete(Integer.parseInt(parser.getDescription(userInput)) - 1);
                    break;
                case "find":
                    find(parser.getDescription(userInput));
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means");
                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.displayInvalidInputFormat();
            } catch (NumberFormatException e) {
                ui.displayInvalidIntFormat();
            }
        }
    }
}
