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
    /**
     * Handles identifying task type.
     */
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    private TaskList taskList;
    private FxUi ui;
    private Storage storage;
    private Parser parser;

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
        storage.initalizeList();
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
            ui.displayText("Got it. I've added this task: " + todoTask.toString());
            ui.displayText("Now you have " + taskList.size() + " tasks in the list.");
            break;
        case EVENT:
            try {
                String modifiedDescription = parser.getPostDescription(description, " /from ");
                LocalDate fromDate = parser.getDateTime(parser.getPreDescription(modifiedDescription, " /to "));
                LocalDate toDate = parser.getDateTime(parser.getPostDescription(modifiedDescription, " /to "));
                Event eventTask = new Event(parser.getPreDescription(description, " /from "), fromDate, toDate);
                taskList.add(eventTask);
                ui.displayText("Got it. I've added this task: " + eventTask.toString());
                ui.displayText("Now you have " + taskList.size() + " tasks in the list.");
                break;
            } catch (Exception e) {
                ui.displayText("Invalid format, please try again using [task] "
                        + "/from [YYYY-MM-DD] /to [YYYY-MM-DD]");
                break;
            }
        case DEADLINE:
            try {
                Deadline deadlineTask = new Deadline(parser.getPreDescription(description, " /by "),
                        parser.getDateTime(parser.getPostDescription(description, " /by ")));
                taskList.add(deadlineTask);
                ui.displayText("Got it. I've added this task: " + deadlineTask.toString());
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
        if (taskList.size() <= itemNo || itemNo < 0) {
            throw new DukeException("Task number is invalid, please enter a valid task number!");
        }
        assert taskList.size() > itemNo;
        taskList.get(itemNo).setDone(true);
        ui.displayText("Nice! I've marked this task as done: " + taskList.get(itemNo).toString());
        storage.saveToFile();
        assert taskList.get(itemNo).isDone();
    }

    /**
     * Unmarks task from task list.
     *
     * @param itemNo Item number in list to be unmarked.
     */
    public void unmark(int itemNo) throws DukeException {
        if (taskList.size() <= itemNo || itemNo < 0) {
            throw new DukeException("Task number is invalid, please enter a valid task number!");
        }
        assert taskList.size() > itemNo;
        taskList.get(itemNo).setDone(false);
        ui.displayText("OK, I've marked this task as not done yet: " + taskList.get(itemNo).toString());
        storage.saveToFile();
        assert !taskList.get(itemNo).isDone();
    }

    /**
     * Deletes task from task list.
     *
     * @param itemNo Item number in list to be deleted.
     */
    public void delete(int itemNo) throws DukeException {
        int initialSize = taskList.size();
        if (taskList.size() <= itemNo || itemNo < 0) {
            throw new DukeException("Task number is invalid, please enter a valid task number!");
        }
        assert taskList.size() > itemNo;
        ui.displayText("Noted. I've removed this task: "
                + taskList.get(itemNo).toString());
        taskList.remove(itemNo);
        storage.saveToFile();
        ui.displayText("Now you have " + taskList.size() + " tasks in the list.");
        assert taskList.size() == initialSize - 1;
    }

    /**
     * Searches task containing search inputs.
     *
     * @param searchInput Substring to find if any tasks contains it.
     */
    public void find(String searchInput) {
        int count = 1;
        boolean hasResult = false;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(searchInput)) {
                if (!hasResult) {
                    hasResult = true;
                    ui.displayText("Here are the matching tasks in your list:");
                }
                ui.displayText(String.format("%d. %s", count,
                        taskList.get(i).toString()));
                count++;
            }
        }

        if (!hasResult) {
            assert count == 1;
            ui.displayText("There are no matching tasks in your list!");
        }
    }

    /**
     * List out all tasks.
     */
    public void list() {
        if (taskList.size() == 0) {
            ui.displayText("Congrats! You have 0 tasks!!");
        } else {
            assert taskList.size() > 0;
            ui.displayText("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                ui.displayText(String.format("%d. %s", i + 1,
                        taskList.get(i).toString()));
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
                list();
                break;
            case "mark":
                mark(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "unmark":
                unmark(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "todo":
                addTask(parser.getDescription(userInput, 5), TaskType.TODO);
                break;
            case "event":
                addTask(parser.getDescription(userInput, 6), TaskType.EVENT);
                break;
            case "deadline":
                addTask(parser.getDescription(userInput, 9), TaskType.DEADLINE);
                break;
            case "delete":
                delete(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "find":
                find(parser.getDescription(userInput, 5));
                break;
            default:
                ui.displayText("I'm sorry, but I don't know what that means");
            }
        } catch (DukeException exception) {
            ui.displayText(exception.getMessage());
        } catch (NumberFormatException e) {
            ui.displayText("Invalid integer, please try again!");
        }
    }
}
