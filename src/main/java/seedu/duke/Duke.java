/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.duke.task.Task;

/**
 * Represents the main program of Duke.
 */
public class Duke {

    private static final String EXIT_FIND_MESSAGE = "Exiting out of \"found\" list\n";

    private static boolean isFind = false;
    private static boolean isDisplayList = false;
    private static TaskList tempTasks;
    private static TaskList tasks;

    private final Ui ui;
    private final Storage storage;

    /**
     * Represents the set of commands by the user.
     */
    protected enum Commands {
        bye,
        mark,
        unmark,
        list,
        todo,
        deadline,
        event,
        delete,
        tag,
        find,
        originalList
    }

    /**
     * Constructor for the Duke class.
     *
     * @param filepath The file path for the data stored in the txt file.
     */
    public Duke(String filepath) {
        assert filepath.equals("") : "File path should not be empty";
        ui = new Ui();
        storage = new Storage(filepath);
        if (!isFind) {
            try {
                tasks = new TaskList(storage.load());
            } catch (DukeException e) {
                ui.showLoadingError();
                tasks = new TaskList();
            }
        }
    }

    /**
     * Returns the string message to be displayed.
     *
     * @param input The input of the user.
     * @return The string message to be displayed by the bot.
     */
    public String run(String input) {
        // Description of the task.
        String description = "";
        // Return of the final String to add.
        String dukeText = "";

        Parser userParse = new Parser(input);
        try {
            Commands userCommand = userParse.checkCommand(userParse.command);
            switch(userCommand) {
            case bye: {
                dukeText += byeCommand(ui, storage);
                break;
            }
            case mark: {
                dukeText += markCommand(ui, userParse, storage);
                break;
            }
            case unmark: {
                dukeText += unmarkCommand(ui, userParse, storage);
                break;
            }
            case list: {
                dukeText += listCommand(ui);
                break;
            }
            case todo: {
                dukeText += todoCommand(ui, storage, userParse, description);
                break;
            }
            case deadline: {
                dukeText += deadlineCommand(ui, storage, userParse, description);
                break;
            }
            case event: {
                dukeText += eventCommand(ui, storage, userParse, description);
                break;
            }
            case delete: {
                dukeText += deleteCommand(ui, storage, userParse);
                break;
            }
            case tag: {
                dukeText += addTagCommand(ui, storage, userParse);
                break;
            }
            case find: {
                dukeText += findCommand(ui, userParse);
                break;
            }
            case originalList: {
                dukeText += originalListCommand(ui, storage);
                break;
            }
            default: {
                break;
            }
            }
        } catch (DukeException e) {
            dukeText += e.getMessage();
        }
        return dukeText;
    }

    /**
     * Returns the duke response.
     *
     * @param input The input from the user.
     * @return The response of Duke.
     */
    protected String getResponse(String input) {
        Duke currDuke = new Duke("data/duke.txt");
        return "Duke: \n" + currDuke.run(input);
    }

    /**
     * Returns the list of Commands.
     *
     * @return A String of List of commands.
     */
    public static String getCommands() {
        String commandsMessage;
        List<String> commandList = new ArrayList<>();
        for (Commands curr : Commands.values()) {
            commandList.add(curr.name() + "\n");
        }
        commandsMessage = commandList.stream().collect(Collectors.joining());
        return commandsMessage;
    }

    /**
     * Returns the exit find list message and storing some variable settings.
     *
     * @return The exit find list message.
     */
    public static String exitFindMessage() {
        isFind = false;
        tempTasks = new TaskList(tasks);
        return EXIT_FIND_MESSAGE;
    }

    /**
     * Returns delete message for find command.
     *
     * @param userParse The user input.
     * @param ui The Ui of the program.
     * @return The string message for the deleted task while in find command.
     * @throws DukeException if there is an out of bound delete index or filepath not found for storing.
     */
    public static String deleteInFindMessage(Parser userParse, Ui ui) throws DukeException {
        Task deleted;

        deleted = tempTasks.delete(userParse);
        for (int i = 0; i < tasks.tasksCounter; i++) {
            if (deleted == tasks.tasksList.get(i)) {
                tasks.tasksList.remove(deleted);
                tasks.tasksCounter--;
            }
        }
        return ui.deleteMessage(tempTasks, deleted);
    }

    /**
     * Returns a bye message. Stores the required data.
     *
     * @param ui The UI for the program.
     * @param storage The Storage for the program.
     * @return The bye message.
     * @throws DukeException if filepath cannot be found.
     */
    public static String byeCommand(Ui ui, Storage storage) throws DukeException {
        storage.write(tasks);
        return ui.bye();
    }

    /**
     * Returns a marked message. Stores the required data.
     *
     * @param ui The UI for the program.
     * @param userParse The user input.
     * @param storage The storage for the program.
     * @return The marked message for the task.
     * @throws DukeException if mark index is out of bound or filepath not found for storing.
     */
    public static String markCommand(Ui ui, Parser userParse, Storage storage) throws DukeException {
        String message = "";
        int userIndex = userParse.checkValidIndex(userParse.inputArr[1]) - 1;

        if (!isDisplayList) {
            isDisplayList = true;
            ui.noListError(tasks);
        }
        if (isFind) {
            TaskList marked = tempTasks.mark(userParse);
            message += ui.markDisplay(marked, userParse);
            for (int i = 0; i < tasks.tasksCounter; i++) {
                if (marked.tasksList.get(userIndex)
                        == tasks.tasksList.get(i)) {
                    tasks.tasksList.get(i).mark();
                }
            }
        } else {
            message += ui.markDisplay(tasks.mark(userParse), userParse);
        }
        storage.write(tasks);
        return message;
    }

    /**
     * Returns an unmarked message. Stores the required data.
     *
     * @param ui The UI for the program.
     * @param userParse The user input.
     * @param storage The storage for the program.
     * @return The unmarked message for the task.
     * @throws DukeException if unmark index is out of bound or filepath not found for storing.
     */
    public static String unmarkCommand(Ui ui, Parser userParse, Storage storage) throws DukeException {
        String message = "";
        int userIndex = userParse.checkValidIndex(userParse.inputArr[1]) - 1;

        if (!isDisplayList) {
            isDisplayList = true;
            ui.noListError(tasks);
        }

        if (isFind) {
            TaskList unmarked = tempTasks.unmark(userParse);
            message += ui.unmarkDisplay(unmarked, userParse);
            for (int i = 0; i < tasks.tasksCounter; i++) {
                if (unmarked.tasksList.get(userIndex)
                        == tasks.tasksList.get(i)) {
                    tasks.tasksList.get(i).unmark();
                }
            }
        } else {
            message += ui.unmarkDisplay(tasks.unmark(userParse), userParse);
        }
        storage.write(tasks);
        return message;
    }

    /**
     * Returns the list messages for the list command.
     *
     * @param ui The UI for the program.
     * @return The list message.
     */
    public static String listCommand(Ui ui) {
        String message = "";

        isDisplayList = true;
        if (isFind) {
            message += "Here are the list of found tasks:\n";
            message += ui.list(tempTasks);
        } else {
            message += "Here are the list of tasks:\n";
            message += ui.list(tasks);
        }
        return message;
    }

    /**
     * Returns the todo command message. Add todo tasks to the taskList and store it.
     *
     * @param ui The UI for the program.
     * @param storage The storage for the program.
     * @param userParse The user input.
     * @param description The description of the task.
     * @return The string of the todo message.
     * @throws DukeException if the todo command is empty or filepath not found for storing.
     */
    public static String todoCommand(Ui ui, Storage storage, Parser userParse, String description)
            throws DukeException {
        String message = "";

        if (isFind) {
            message += exitFindMessage();
        }
        tasks.addTodo(description, userParse);
        message += ui.addTodoMessage(tasks);
        storage.write(tasks);
        return message;
    }

    /**
     * Returns the deadline command message. Add deadline tasks to the taskList and store it.
     *
     * @param ui The UI for the program.
     * @param storage The storage for the program.
     * @param userParse The user input.
     * @param description The description of the task.
     * @return The string of the deadline message.
     * @throws DukeException if any part of the deadline command is empty or filepath not found for storing.
     */
    public static String deadlineCommand(Ui ui, Storage storage, Parser userParse, String description)
            throws DukeException {
        String message = "";

        if (isFind) {
            message += exitFindMessage();
        }
        tasks.addDeadline(description, userParse);
        message += ui.addDeadlineMessage(tasks);
        storage.write(tasks);
        return message;
    }

    /**
     * Returns the event command message. Add event tasks to the taskList and store it.
     *
     * @param ui The UI for the program.
     * @param storage The storage for the program.
     * @param userParse The user input.
     * @param description The description of the task.
     * @return The string of the event message.
     * @throws DukeException if any part of the event command is empty or filepath not found for storing.
     */
    public static String eventCommand(Ui ui, Storage storage, Parser userParse, String description)
            throws DukeException {
        String message = "";

        if (isFind) {
            message += exitFindMessage();
        }
        tasks.addEvent(description, userParse);
        message += ui.addEventMessage(tasks);
        storage.write(tasks);
        return message;
    }

    /**
     * Returns the deleted command message. Stores the new data into the txt file.
     *
     * @param ui The UI for the program.
     * @param storage The storage for the program.
     * @param userParse The user input.
     * @return The string of the deleted command message.
     * @throws DukeException if there is no list command beforehand or filepath not found for storing.
     */
    public static String deleteCommand(Ui ui, Storage storage, Parser userParse) throws DukeException {
        String message = "";

        if (!isDisplayList) {
            isDisplayList = true;
            ui.noListError(tasks);
        }
        if (isFind) {
            message += deleteInFindMessage(userParse, ui);
        } else {
            message += ui.deleteMessage(tasks, tasks.delete(userParse));
        }
        storage.write(tasks);
        return message;
    }

    /**
     * Returns the find message for the find command.
     *
     * @param ui The UI for the program.
     * @param userParse The user input.
     * @return The string of the find lists.
     */
    public static String findCommand(Ui ui, Parser userParse) {
        isDisplayList = true;
        isFind = true;
        tempTasks = tasks.find(userParse);
        if (tempTasks.tasksList.isEmpty()) {
            isFind = false;
        }
        return ui.findMessage(tempTasks);
    }

    /**
     * Returns the original list. Stores the original list into the txt file.
     *
     * @param ui The UI for the program.
     * @param storage The storage for the program.
     * @return The string for the original list.
     * @throws DukeException if there is no file path.
     */
    public static String originalListCommand(Ui ui, Storage storage) throws DukeException {
        String message = "";

        isFind = false;
        tempTasks = new TaskList(tasks);
        message += "Here are the list of tasks:\n";
        message += ui.list(tasks);
        storage.write(tasks);
        return message;
    }

    /**
     * Returns the tag message for the command tag.
     *
     * @param ui The UI for the program.
     * @param storage The storage for the program.
     * @param userParse The user input.
     * @return The string of the tag message after tagging.
     * @throws DukeException if there is an invalid command index
     */
    public static String addTagCommand(Ui ui, Storage storage, Parser userParse) throws DukeException {
        String message = "";
        int userIndex = userParse.checkValidIndex(userParse.inputArr[1]) - 1;

        if (!isDisplayList) {
            isDisplayList = true;
            ui.noListError(tasks);
        }
        if (isFind) {
            TaskList marked = tempTasks.tag(userParse);
            message += ui.tagDisplay(marked, userParse);
            for (int i = 0; i < tasks.tasksCounter; i++) {
                if (marked.tasksList.get(userIndex)
                        == tasks.tasksList.get(i)) {
                    tasks.tasksList.get(i).addTag(marked.tasksList.get(userIndex));
                }
            }
        } else {
            message += ui.tagDisplay(tasks.tag(userParse), userParse);
        }
        storage.write(tasks);
        return message;
    }
}
