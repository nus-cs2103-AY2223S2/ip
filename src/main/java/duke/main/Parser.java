package duke.main;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

import duke.task.*;

/**
 * Understands what the command is asking for.
 */
public class Parser {

    public int checkExceptionAndGetTask(String command, TaskList allTasks) throws DukeException {
        DukeException.missingIndexException(command);
        DukeException.invalidIndexException(command, allTasks.getNumberOfTask());
        String[] str = command.split(" ");
        int taskIndex = Integer.parseInt(str[1]) - 1;

        return taskIndex;
    }

    /**
     * Parses the command to process.
     * If command calls for list, task list is printed.
     * If command starts with "mark", task will be mark as done.
     * If command starts with "unmark", task will be unmarked as
     * undone.
     * If command starts with "todo", "deadline" or "event", task will
     * be added to task list.
     * If command starts with "delete", task will be deleted from task
     * list.
     * If command starts with "show deadlines or events on", matching
     * tasks on input date will be shown.
     * If command starts with "find", matching tasks to keyword will
     * be shown.
     * If command starts with "archive", current task list will be
     * saved to archive.
     * If command starts with "bye", exit message will be printed.
     * Task list and text file on hard disk containing all commands
     * updates if possible after each command.
     * Corresponding messages will also be printed to signify
     * completion of command.
     * Each command has its corresponding exceptions catching.
     *
     * @param command Command typed by user.
     * @param ui Ui object to print corresponding messages.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     */
    public String parse(String command, Ui ui, TaskList allTasks, Storage storage, Storage storageArchive) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            if (command.equals("list")) {
                return listCommand(ui, allTasks);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                return markOrUnmarkCommand(command, allTasks, storage);
            } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                return tasksCommands(command, allTasks, storage, storageArchive, dateTimeFormatter);
            } else if (command.startsWith("delete")) {
                return deleteCommand(command, allTasks, storage);
            } else if (command.startsWith("show deadlines or events on") || command.startsWith("find")) {
                return showOrFindCommands(command, ui, allTasks);
            } else if (command.equals("archive")) {
                return archiveCommand(ui, command, allTasks, storage);
            } else if (command.equals("bye")){
                return ui.printByeMessage();
            } else {
                DukeException.invalidCommandException(command);
            }
        } catch (DukeException d) {
            return d.getMessage();
        } catch (NumberFormatException nfe) {
            return "\t ☹ OOPS!!! The task index to delete or un/mark a task cannot be a non-integer.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert false: "Uncaught error";
        return "Uncaught error";
    }

    /**
     * Prints task list.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param ui Ui object to print corresponding messages.
     * @param allTasks A list of existing tasks.
     * @return Ui response to user.
     */
    public String listCommand(Ui ui, TaskList allTasks) {
        String output;
        output = ui.printCommandList(allTasks.getAllTasks());
        return output;
    }

    /**
     * Marks task as done.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     * @throws IOException if file cannot be loaded.
     */
    public String markCommand(String command, TaskList allTasks, Storage storage) throws DukeException, IOException {
        int taskIndex = checkExceptionAndGetTask(command, allTasks);
        Task oldTask = allTasks.getTask(taskIndex);
        Task task = allTasks.markTaskAsDone(oldTask, taskIndex);
        storage.saveListToFile(command, task, allTasks);
        return task.markAsDone();
    }

    /**
     * Unmarks task as undone.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     * @throws IOException if file cannot be loaded.
     */
    public String unmarkCommand(String command, TaskList allTasks, Storage storage) throws DukeException, IOException {
        int taskIndex = checkExceptionAndGetTask(command, allTasks);
        Task oldTask = allTasks.getTask(taskIndex);
        Task task = allTasks.unmarkTaskAsUndone(oldTask, taskIndex);
        storage.saveListToFile(command, task, allTasks);
        return task.unmarkAsUndone();
    }

    /**
     * Marks or unmarks task as done or undone respectively.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     * @throws IOException if file cannot be loaded.
     */
    public String markOrUnmarkCommand(String command, TaskList allTasks, Storage storage)
            throws DukeException, IOException {
        if (command.startsWith("mark")) {
            return markCommand(command, allTasks, storage);
        } else {
            return unmarkCommand(command, allTasks, storage);
        }
    }

    /**
     * Adds todo task to task list.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     * @throws IOException if file cannot be loaded.
     */
    public String todoCommand(String command, TaskList allTasks, Storage storage, Storage storageArchive)
            throws DukeException, IOException {
        DukeException.emptyCommandException(command);
        String[] str = command.split("todo");
        String taskName = str[1];
        Todo todo = new Todo(allTasks.getNumberOfTask(), false,
                taskName, allTasks.getNumberOfTask() + 1);
        allTasks.addTask(todo);
        storage.saveListToFile(command, todo, allTasks);
        storageArchive.saveListToFile(command, todo, allTasks);
        return todo.printToDoTask();
    }

    /**
     * Adds deadline task to task list.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     * @throws IOException if file cannot be loaded.
     */
    public String deadlineCommand(String command, TaskList allTasks, Storage storage, Storage storageArchive,
                                  DateTimeFormatter dateTimeFormatter) throws DukeException, IOException {
        DukeException.emptyCommandException(command);
        DukeException.missingTimingException(command);
        String[] str = command.split("/by ");
        String taskName = str[0].split("deadline")[1];
        LocalDateTime taskDeadline = LocalDateTime.parse(str[1], dateTimeFormatter);
        Deadline deadline = new Deadline(allTasks.getNumberOfTask(), false,
                taskName, taskDeadline, allTasks.getNumberOfTask() + 1);
        allTasks.addTask(deadline);
        storage.saveListToFile(command, deadline, allTasks);
        storageArchive.saveListToFile(command, deadline, allTasks);
        return deadline.printDeadlineTask();
    }

    /**
     * Adds event task to task list.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     * @throws IOException if file cannot be loaded.
     */
    public String eventCommand(String command, TaskList allTasks, Storage storage, Storage storageArchive,
                               DateTimeFormatter dateTimeFormatter) throws DukeException, IOException {
        DukeException.emptyCommandException(command);
        DukeException.missingTimingException(command);
        String[] str = command.split("/from ");
        String taskName = str[0].split("event")[1];
        String[] eventStartEndTime = str[1].split(" /to ");
        LocalDateTime eventStartTime = LocalDateTime.parse(eventStartEndTime[0], dateTimeFormatter);
        LocalDateTime eventEndTime = LocalDateTime.parse(eventStartEndTime[1], dateTimeFormatter);
        Event event = new Event(allTasks.getNumberOfTask(), false,
                taskName, eventStartTime, eventEndTime, allTasks.getNumberOfTask() + 1);
        allTasks.addTask(event);
        storage.saveListToFile(command, event, allTasks);
        storageArchive.saveListToFile(command, event, allTasks);
        return event.printEventTask();
    }

    /**
     * Adds todo, deadline or event to task list.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     * @throws IOException if file cannot be loaded.
     */
    public String tasksCommands(String command, TaskList allTasks, Storage storage, Storage storageArchive,
                                DateTimeFormatter dateTimeFormatter) throws DukeException, IOException {
        if (command.startsWith("todo")) {
            return todoCommand(command, allTasks, storage, storageArchive);
        } else if (command.startsWith("deadline")) {
            return deadlineCommand(command, allTasks, storage, storageArchive, dateTimeFormatter);
        } else {
            return eventCommand(command, allTasks, storage, storageArchive, dateTimeFormatter);
        }
    }

    /**
     * Deletes task from task list.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param allTasks A list of existing tasks.
     * @param storage Storage object to update text file of task list
     *                on hard disk.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     * @throws IOException if file cannot be loaded.
     */
    public String deleteCommand(String command, TaskList allTasks, Storage storage)
            throws DukeException, IOException {
        int taskIndex = checkExceptionAndGetTask(command, allTasks);
        Task task = allTasks.getTask(taskIndex);
        allTasks.deleteTask(taskIndex);
        storage.saveListToFile(command, task, allTasks);
        return task.printDelete(allTasks.getAllTasks());
    }

    /**
     * Shows matching tasks on input date.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param ui Ui object to print corresponding messages.
     * @param allTasks A list of existing tasks.
     * @return Ui response to user.
     * @throws DukeException if input is invalid.
     */
    public String showDeadlinesEventsCommand(Ui ui, String command, TaskList allTasks) throws DukeException {
        DukeException.emptyCommandException(command);
        String[] str = command.split("show deadlines or events on ");
        String dateTime = str[1];
        DateTimeFormatter dateTimeFormatter2 =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime1 = LocalDate.parse(dateTime, dateTimeFormatter2);
        return ui.printDeadlineOrEventsOnDay(dateTime1, allTasks);
    }

    /**
     * Shows matching tasks to keyword.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param ui Ui object to print corresponding messages.
     * @param allTasks A list of existing tasks.
     * @return Ui response to user.
     */
    public String findCommand(Ui ui, String command, TaskList allTasks) {
        String[] str = command.split("find");
        String keyword = str[1];
        return ui.printFindResults(keyword, allTasks);
    }

    /**
     * If command starts with "show deadlines or events on", matching
     * tasks on input date will be shown.
     * If command starts with "find", matching tasks to keyword will
     * be shown.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param ui Ui object to print corresponding messages.
     * @param allTasks A list of existing tasks.
     * @throws DukeException if input is invalid.
     * @return Ui response to user.
     */
    public String showOrFindCommands(String command, Ui ui, TaskList allTasks) throws DukeException {
        if (command.startsWith("show deadlines or events on")) {
            return showDeadlinesEventsCommand(ui, command, allTasks);
        } else {
            return findCommand(ui, command, allTasks);
        }
    }

    /**
     * Saves current task list to archive on archiveAll.txt.
     * Corresponding messages will be printed to signify
     * completion of command.
     *
     * @param command Command typed by user.
     * @param ui Ui object to print corresponding messages.
     * @param allTasks A list of existing tasks.
     * @return Ui response to user.
     * @throws IOException if file cannot be loaded.
     */
    public String archiveCommand(Ui ui, String command, TaskList allTasks, Storage storage) throws IOException {
        Storage storage1 = new Storage("data/archiveAll.txt");
        storage1.loadTxtFile();
        storage1.saveWholeListToFile(allTasks);
        storage.clear();
        allTasks.deleteAllTasks();
        return ui.printArchiveMessage();
    }

}


