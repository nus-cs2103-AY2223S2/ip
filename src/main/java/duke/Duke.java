package duke;

import java.time.LocalDate;

import duke.fx.FxUi;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Handles duke.Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    static final int TODO_LENGTH = "todo ".length();
    static final int EVENT_LENGTH = "event ".length();
    static final int DEADLINE_LENGTH = "deadline ".length();
    static final int FIND_LENGTH = "find ".length();
    static final String FROM_SUBSTRING = " /from ";
    static final String TO_SUBSTRING = " /to ";
    static final String BY_SUBSTRING = " /by ";

    /**
     * Handles identifying task type.
     */
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    private final TaskList taskList;
    private final FxUi ui;
    private final Storage storage;
    private final Parser parser;

    /**
     * Duke constructor.
     *
     * @param ui The user interface to display responses.
     */
    public Duke(FxUi ui) {
        this.ui = ui;
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        this.parser = new Parser();
        storage.initializeList();
    }

    /**
     * Adds task into task list.
     *
     * @param description Description of task to be added.
     * @param taskType Task type of task to be added.
     */
    public void addTask(String description, TaskType taskType) {
        switch (taskType) {
        case TODO:
            Todo todoTask = new Todo(description);
            taskList.add(todoTask);
            ui.displayText("Got it. I've added this task: " + todoTask);
            ui.displayText("Now you have " + taskList.size() + " tasks in the list.");
            break;
        case EVENT:
            try {
                String modifiedDescription = parser.getPostDescription(description, FROM_SUBSTRING);

                String newDescription = parser.getPreDescription(description, FROM_SUBSTRING);
                LocalDate fromDate = parser.getDateTime(parser.getPreDescription(modifiedDescription, TO_SUBSTRING));
                LocalDate toDate = parser.getDateTime(parser.getPostDescription(modifiedDescription, TO_SUBSTRING));
                Event eventTask = new Event(newDescription, fromDate, toDate);
                taskList.add(eventTask);

                ui.displayText("Got it. I've added this task: " + eventTask);
                ui.displayText("Now you have " + taskList.size() + " tasks in the list.");
                break;
            } catch (Exception e) {
                ui.displayText("Invalid format, please try again using [task] "
                        + "/from [YYYY-MM-DD] /to [YYYY-MM-DD]");
                break;
            }
        case DEADLINE:
            try {
                String newDescription = parser.getPreDescription(description, BY_SUBSTRING);
                LocalDate byDate = parser.getDateTime(parser.getPostDescription(description, BY_SUBSTRING));
                Deadline deadlineTask = new Deadline(newDescription, byDate);
                taskList.add(deadlineTask);

                ui.displayText("Got it. I've added this task: " + deadlineTask);
                ui.displayText("Now you have " + taskList.size() + " tasks in the list.");
                break;
            } catch (Exception e) {
                ui.displayText("Invalid format, please try again using [task] /by [YYYY-MM-DD]");
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
    public void mark(int itemNo) throws DukeException {
        taskList.get(itemNo).setDone(true);
        ui.displayText("Nice! I've marked this task as done: " + taskList.get(itemNo).toString());
        storage.saveToFile();
    }

    /**
     * Unmarks task from task list.
     *
     * @param itemNo Item number in list to be unmarked.
     */
    public void unmark(int itemNo) {
        taskList.get(itemNo).setDone(false);
        ui.displayText("OK, I've marked this task as not done yet: " + taskList.get(itemNo).toString());
        storage.saveToFile();
    }

    /**
     * Deletes task from task list.
     *
     * @param itemNo Item number in list to be deleted.
     */
    public void delete(int itemNo) {
        ui.displayText("Noted. I've removed this task: "
                + taskList.get(itemNo).toString());
        taskList.remove(itemNo);
        storage.saveToFile();
        ui.displayText("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Searches task containing search inputs.
     *
     * @param searchInput Substring to find if any tasks contains it.
     */
    public void find(String searchInput) {
        ui.displayText("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(searchInput)) {
                ui.displayText(String.format("%d. %s", count,
                        taskList.get(i).toString()));
                count++;
            }
        }
    }

    /**
     * Start Duke.
     */
    public void start() {
        ui.displayIntro();
    }

    /**
     * Passes user's input.
     *
     * @param userInput User's string input.
     */
    public void handleInput(String userInput) {
        try {
            String cmd = parser.getCmd(userInput);
            switch (cmd) {

            case "list":
                ui.displayText("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    ui.displayText(String.format("%d. %s", i + 1,
                            taskList.get(i).toString()));
                }
                break;
            case "mark":
                mark(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "unmark":
                unmark(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "todo":
                addTask(parser.getDescription(userInput, TODO_LENGTH), TaskType.TODO);
                break;
            case "event":
                addTask(parser.getDescription(userInput, EVENT_LENGTH), TaskType.EVENT);
                break;
            case "deadline":
                addTask(parser.getDescription(userInput, DEADLINE_LENGTH), TaskType.DEADLINE);
                break;
            case "delete":
                delete(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "find":
                find(parser.getDescription(userInput, FIND_LENGTH));
                break;
            default:
                ui.displayText("I'm sorry, but I don't know what that means");
            }
        } catch (DukeException exception) {
            ui.displayText(exception.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ui.displayText("Invalid format of input, please try again!");
        } catch (NumberFormatException e) {
            ui.displayText("Invalid integer, please try again!");
        }
    }
}
