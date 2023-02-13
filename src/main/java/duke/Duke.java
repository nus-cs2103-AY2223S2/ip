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
    static final String EASTER_EGG = "Eri?";
    static final int TODO_LENGTH = "todo ".length();
    static final int EVENT_LENGTH = "event ".length();
    static final int DEADLINE_LENGTH = "deadline ".length();
    static final int FIND_LENGTH = "find ".length();
    static final String FROM_SUBSTRING = " /from ";
    static final String TO_SUBSTRING = " /to ";
    static final String BY_SUBSTRING = " /by ";
    static final String BLANK_ERROR_MSG = "BLANK-ERROR-69";
    static final String TODO_FORMAT = "todo [task]";
    static final String EVENT_FORMAT = "event [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD]";
    static final String DEADLINE_FORMAT = "deadline [task] /by [YYYY-MM-DD]";

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
        taskList.sort();
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
            ui.displayText("Got it. I've added this task: " + todoTask
                    + "\nNow you have " + taskList.size() + " tasks in the list.");
            break;
        case EVENT:
            try {
                String modifiedDescription = parser.getPostDescription(description, FROM_SUBSTRING);

                String newDescription = parser.getPreDescription(description, FROM_SUBSTRING);
                LocalDate fromDate = parser.getDateTime(parser.getPreDescription(modifiedDescription, TO_SUBSTRING));
                LocalDate toDate = parser.getDateTime(parser.getPostDescription(modifiedDescription, TO_SUBSTRING));
                Event eventTask = new Event(newDescription, fromDate, toDate);
                taskList.add(eventTask);

                ui.displayText("Got it. I've added this task: " + eventTask
                        + "\nNow you have " + taskList.size() + " tasks in the list.");
                break;
            } catch (Exception e) {
                ui.displayText("Invalid format, please try again, type '" + EVENT_FORMAT + "'");
                break;
            }
        case DEADLINE:
            try {
                String newDescription = parser.getPreDescription(description, BY_SUBSTRING);

                LocalDate byDate = parser.getDateTime(parser.getPostDescription(description, BY_SUBSTRING));
                Deadline deadlineTask = new Deadline(newDescription, byDate);
                taskList.add(deadlineTask);

                ui.displayText("Got it. I've added this task: " + deadlineTask
                        + "\nNow you have " + taskList.size() + " tasks in the list.");
                break;
            } catch (Exception e) {
                ui.displayText("Invalid format, please try again, type '" + DEADLINE_FORMAT + "'");
                break;
            }
        default:
            break;
        }
        taskList.sort();
        storage.saveToFile();
    }

    /**
     * Marks task from task list.
     *
     * @param itemNo Item number in list to be marked.
     */
    public void mark(int itemNo) throws DukeException {
        handleInvalidTaskNo(itemNo);
        assert taskList.size() > itemNo;
        taskList.get(itemNo).setDone(true);
        ui.displayText("Great job! I've marked this task as done: " + taskList.get(itemNo).toString());
        storage.saveToFile();
        assert taskList.get(itemNo).isDone();
    }

    /**
     * Unmarks task from task list.
     *
     * @param itemNo Item number in list to be unmarked.
     */
    public void unmark(int itemNo) throws DukeException {
        handleInvalidTaskNo(itemNo);
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
        handleInvalidTaskNo(itemNo);
        assert taskList.size() > itemNo;

        StringBuilder displayString = new StringBuilder();
        displayString.append("Noted. I've removed this task: ").append(taskList.get(itemNo).toString());
        taskList.remove(itemNo);
        storage.saveToFile();
        displayString.append("\nNow you have ").append(taskList.size()).append(" tasks in the list.");
        ui.displayText(displayString.toString());
        assert taskList.size() == initialSize - 1;
    }

    /**
     * Handles error of invalid task number.
     *
     * @param itemNo User's input to check for error.
     */
    public void handleInvalidTaskNo(int itemNo) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("You have no remaining tasks!");
        }
        if (taskList.size() <= itemNo || itemNo < 0) {
            throw new DukeException("Invalid task number, please enter a valid task number!");
        }
    }

    /**
     * Searches task containing search inputs.
     *
     * @param searchInput Substring to find if any tasks contains it.
     */
    public void find(String searchInput) {
        int count = 1;
        boolean hasResult = false;
        StringBuilder foundString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(searchInput)) {
                hasResult = true;
                foundString.append(String.format("%d. %s", count, taskList.get(i).toString())).append("\n");
                count++;
            }
        }

        if (!hasResult) {
            assert count == 1;
            ui.displayText("There are no matching tasks in your list!");
        } else {
            ui.displayText("Here are the matching tasks in your list:\n" + foundString);
        }
    }

    /**
     * Displays the list of task.
     */
    public void getList() {
        if (taskList.size() == 0) {
            ui.displayText("Congrats! You have 0 tasks left!!");
        } else {
            assert taskList.size() > 0;
            StringBuilder listString = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                listString.append(String.format("%d. %s", i + 1, taskList.get(i).toString())).append("\n");
            }
            ui.displayText("Here are the tasks in your list:\n" + listString);
        }
    }

    /**
     * Provides details of given command.
     * If given command is invalid, brief details of all commands will be provided.
     *
     * @param cmd User's given command.
     */
    public void getHelp(String cmd) {
        switch (cmd) {
        case "list":
            ui.displayText("Type 'list' - returns all tasks");
            break;
        case "todo":
            ui.displayText("Type '" + TODO_FORMAT + "' - add todo task to list");
            break;
        case "deadline":
            ui.displayText("Type '" + DEADLINE_FORMAT + "' - add deadline task to list");
            break;
        case "event":
            ui.displayText("Type '" + EVENT_FORMAT + "' - add event task to list");
            break;
        case "mark":
            ui.displayText("Type 'mark [task #]' - mark task from list");
            break;
        case "unmark":
            ui.displayText("Type 'unmark [task #]' - unmark task from list");
            break;
        case "delete":
            ui.displayText("Type 'delete [task #]' - deletes task from list");
            break;
        case "find":
            ui.displayText("Type 'find [keyword]' - returns all tasks containing keyword");
            break;
        case "bye":
            ui.displayText("Type 'bye' - quit program");
            break;
        default:
            ui.displayText("Here are a list of commands, for more info type 'help [cmd]':\n"
                    + "- list\n- todo\n- deadline\n- event\n- mark\n- unmark\n- delete\n- find\n- bye");
        }
    }

    /**
     * Starts Duke.
     */
    public void start() {
        ui.displayIntro();
    }

    /**
     * Handles error of blank space.
     *
     * @param userInput User's input to check for error.
     * @param errorMsg Error message to display if error occurs.
     */
    public void handleBlankError(String userInput, String errorMsg) throws DukeException {
        if (parser.getDescription(userInput).equals(BLANK_ERROR_MSG)) {
            throw new DukeException(errorMsg);
        }
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
            case "help":
                getHelp(parser.getDescription(userInput));
                break;
            case "list":
                getList();
                break;
            case "mark":
                handleBlankError(userInput, "Please provide a task number to mark!");
                mark(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "unmark":
                handleBlankError(userInput, "Please provide a task number to unmark!");
                unmark(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "todo":
                handleBlankError(userInput, "Invalid format, please try again, type '"
                        + TODO_FORMAT + "'");
                addTask(parser.getDescription(userInput, TODO_LENGTH), TaskType.TODO);
                break;
            case "event":
                handleBlankError(userInput, "Invalid format, please try again, type '"
                        + EVENT_FORMAT + "'");
                addTask(parser.getDescription(userInput, EVENT_LENGTH), TaskType.EVENT);
                break;
            case "deadline":
                handleBlankError(userInput, "Invalid format, please try again, type '"
                        + DEADLINE_FORMAT + "'");
                addTask(parser.getDescription(userInput, DEADLINE_LENGTH), TaskType.DEADLINE);
                break;
            case "delete":
                handleBlankError(userInput, "Please provide a task number to delete!");
                delete(Integer.parseInt(parser.getDescription(userInput)) - 1);
                break;
            case "find":
                handleBlankError(userInput, "Please provide a sub string to find!");
                find(parser.getDescription(userInput, FIND_LENGTH));
                break;
            case EASTER_EGG:
                ui.displayText("If you run, gain one. Move forward, gain two");
                break;
            default:
                ui.displayText("I do not understand you!\nType 'help' to get help");
            }
        } catch (DukeException exception) {
            ui.displayText(exception.getMessage());
        } catch (NumberFormatException e) {
            ui.displayText("Invalid integer, please try again!");
        }
    }
}
