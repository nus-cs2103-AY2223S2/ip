package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class deals with making sense of the user command.
 */
public class Parser {
    /** Deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;

    /** Contains the task list */
    private TaskList tasks;

    /** Deals with interactions with the user */
    private Ui ui;

    /** Contains the command input by the user */
    private String command;


    /**
     * Constructs Parser to parse the input command from the user.
     *
     * @param command Command input by the user.
     * @param ui User interface to interact with the user.
     * @param tasks Task operations.
     * @param storage Storage to save tasks.
     */
    public Parser(String command, Ui ui, TaskList tasks, Storage storage) {
        this.command = command;
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Parses the command input by the user and executes it.
     *
     * @throws DukeException If the tasks cannot be saved to the file.
     */
    public void parse() throws DukeException {
        String firstWord = command.split(" ")[0].toLowerCase();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        switch (firstWord) {
        case "bye":
            parseByeCommand();
            break;
        case "list":
            parseListCommand();
            break;
        case "mark":
            parseMarkCommand();
            break;
        case "unmark":
            parseUnmarkCommand();
            break;
        case "delete":
            parseDeleteCommand();
            break;
        case "todo":
            parseTodoCommand(firstWord);
            break;
        case "deadline":
            parseDeadlineCommand(firstWord, format);
            break;
        case "event":
            parseEventCommand(firstWord, format);
            break;
        case "find":
            parseFindCommand();
            break;
        case "help":
            parseHelpCommand();
            break;
        default:
            ui.showUnknownError();
        }
    }

    private void parseByeCommand() {
        ui.printGoodBye();
    }

    private void parseListCommand() {
        String s = "Here are the tasks in your list:";
        ui.listTasks(tasks.getListOfTasks(), s);
    }

    private void parseMarkCommand() throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.markTask(ui, index);
        storage.save(tasks.getListOfTasks());
    }

    private void parseUnmarkCommand() throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.unmarkTask(ui, index);
        storage.save(tasks.getListOfTasks());
    }

    private void parseDeleteCommand() throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.deleteTask(ui, index);
        storage.save(tasks.getListOfTasks());
    }

    private void parseTodoCommand(String firstWord) throws DukeException {
        try {
            String description = command.substring(5);
            tasks.saveTask(ui, description);
            storage.save(tasks.getListOfTasks());
        } catch(StringIndexOutOfBoundsException e) {
            ui.showDescriptionError(firstWord);
        }
    }

    private void parseDeadlineCommand(String firstWord, DateTimeFormatter format) throws DukeException {
        try {
            int byIdx = command.indexOf("/by");
            String description = command.substring(9, byIdx - 1);
            LocalDateTime by = LocalDateTime.parse(command.substring(byIdx + 4), format);
            tasks.saveTask(ui, description, by);
            storage.save(tasks.getListOfTasks());
        } catch (DateTimeParseException e) {
            ui.showDateTimeError();
        } catch(StringIndexOutOfBoundsException e) {
            ui.showDescriptionError(firstWord);
        }
    }

    private void parseEventCommand(String firstWord, DateTimeFormatter format) throws DukeException {
        try {
            int fromIdx = command.indexOf("/from");
            int toIdx = command.indexOf("/to");
            String description = command.substring(6, fromIdx - 1);
            LocalDateTime from = LocalDateTime.parse(command.substring(fromIdx + 6, toIdx - 1), format);
            LocalDateTime to = LocalDateTime.parse(command.substring(toIdx + 4), format);
            tasks.saveTask(ui, description, from, to);
            storage.save(tasks.getListOfTasks());
        } catch (DateTimeParseException e) {
            ui.showDateTimeError();
        } catch(StringIndexOutOfBoundsException e) {
            ui.showDescriptionError(firstWord);
        }
    }

    private void parseFindCommand() {
        String description = command.substring(5);
        tasks.findTask(ui, description);
    }

    private void parseHelpCommand() throws DukeException {
        ui.showHelpPage();
    }
}
