package seedu.duke;

import seedu.duke.tasks.Task;
import seedu.duke.tasks.Todo;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class Parser {

    /**
     *  Checks whether task number is given
     *
     *  @param inputStrings String array of each word input by the user
     *  @return String of the task number if it exists
     */
    private String getTaskNumber(String[] inputStrings) throws DukeException {
        if (inputStrings.length == 1) {
            throw new DukeException("No task number was given!");
        } else {
            return inputStrings[1];
        }
    }

    /**
     *  Retrieve the keyword(s) from the input given by user
     *
     *  @param inputStrings String array of each word input by the user
     *  @return String of the keyword(s) that user wants to look for
     */
    private String getKeywords(String[] inputStrings) throws DukeException {
        inputStrings = Arrays.copyOfRange(inputStrings, 1, inputStrings.length);
        String keywords = String.join(" ", inputStrings).trim();
        if (keywords.length() == 0) {
            throw new DukeException("No input was given!");
        }
        return keywords;
    }

    private String getDate(String[] inputStrings) throws DukeException {
        // keyword in this case refers to the user input date (in the form DD-MM-YYYY)
        String inputDate = getKeywords(inputStrings);
        String[] inputDates = inputDate.split(" ");
        if (inputDates.length > 1) {
            throw new DukeException("Please only provide the date in the format of yyyy-MM-dd!");
        }
        return inputDate;
    }

    /**
     *  Checks the validity of the command
     *
     *  @param command String of the command sent by user
     *  @param listOfCommands String array of all the commands
     *  @return String of the command if valid
     */
    private String checkCommand(String command, String[] listOfCommands) throws DukeException {
        for (String cmd : listOfCommands) {
            if (cmd.equals(command)) {
                return command;
            }
        }
        throw new DukeException("I don't know what this command means!");
    }
    /**
     *  Checks whether task number is numeric & valid
     *
     *  @param taskList Current state of TaskList
     *  @param taskNumber String of the taskNumber
     *  @return Integer of the task number (in 0-indexing) if it is valid
     */
    private int checkTaskNumber(TaskList taskList, String taskNumber) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= taskList.getSize() || index < 0) {
                throw new DukeException("The task number given does not exist!");
            } else {
                return index;
            }
        } catch (NumberFormatException err) {
            throw new DukeException("The task number given is not numeric!");
        }
    }

    /**
     *  Checks whether description for Task is given
     *
     *  @param description Description of the Task
     */
    private void checkDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("There was no task description given!");
        }
    }

    /**
     *  Checks whether deadline is given
     *
     *  @param inputStrings String array of each word input by the user
     *  @return Index of /by in the array
     */
    private int checkDeadline(String[] inputStrings) throws DukeException {
        int byIndex = Arrays.asList(inputStrings).indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("There was no deadline given!");
        } else {
            return byIndex;
        }
    }

    /**
     *  Checks whether starting time stamp for Event is given
     *
     *  @param inputStrings String array of each word input by the user
     *  @return Index of /from in splitInput
     */
    private int checkStarting(String[] inputStrings) throws DukeException {
        int byIndex = Arrays.asList(inputStrings).indexOf("/from");
        if (byIndex == -1) {
            throw new DukeException("Please indicate a starting period!");
        } else {
            return byIndex;
        }
    }

    /**
     *  Checks whether ending time stamp for Event is given
     *
     *  @param inputStrings String array of each word input by the user
     *  @return Index of /to in splitInput
     */
    private int checkEnding(String[] inputStrings) throws DukeException {
        int byIndex = Arrays.asList(inputStrings).indexOf("/to");
        if (byIndex == -1) {
            throw new DukeException("Please indicate an ending period!");
        } else {
            return byIndex;
        }
    }

    /**
     *  Converts String timestamp into LocalDateTime object
     *
     *  @param timestamp String of the timestamp given
     *  @return LocalDateTime object of timestamp
     */
    private LocalDateTime convertTimestamp(String timestamp) throws DukeException {
        if (timestamp.equals("")) {
            throw new DukeException("There was no time period given!");
        }
        try {
            String[] dateTimeStrings = timestamp.split(" ");
            LocalDate date = LocalDate.parse(dateTimeStrings[0]);
            int hour = Integer.parseInt(dateTimeStrings[1].substring(0, 2));
            int min = Integer.parseInt(dateTimeStrings[1].substring(2, 4));
            LocalTime time = LocalTime.of(hour, min);
            return LocalDateTime.of(date, time);
        } catch (DateTimeParseException err) {
            throw new DukeException("Date formatting is wrong! Must be yyyy-MM-dd");
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Time not stated!");
        } catch (StringIndexOutOfBoundsException | NumberFormatException err) {
            throw new DukeException("Time must be in HHmm format!");
        } catch (DateTimeException err) {
            throw new DukeException("Time given is not valid!");
        }
    }

    /**
     *  Converts String date into LocalDate object
     *
     *  @param date String of the date given
     *  @return LocalDate object of date
     */
    private LocalDate convertDate(String date) throws DukeException {
        if (date.equals("")) {
            throw new DukeException("There was no time period given!");
        }
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException err) {
            throw new DukeException("Date formatting is wrong! Must be yyyy-MM-dd");
        }
    }


    /**
     * Handles message to be printed for TD tasks
     *
     * @param inputStrings String array of each word input by the user
     * @param taskList Current state of TaskList
     * @param storage Storage to overwrite save file
     * @param ui Ui to print lines for user to see and interact with
     * @return message for user to see when a TD task is added.
     */
    private String handleTodo(String[] inputStrings, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String description = String.join(" ", Arrays.copyOfRange(inputStrings, 1, inputStrings.length));
        checkDescription(description);
        Task newTask = new Todo(description);
        TaskList updatedList = taskList.addTask(newTask);
        assert updatedList.getSize() == (taskList.getSize() + 1) : "Task is not added!";
        storage.writeFile(updatedList);
        return ui.sayAddedTask(newTask, updatedList);
    }

    /**
     * Handles message to be printed for Deadline Tasks
     *
     * @param inputStrings String array of each word input by the user
     * @param taskList Current state of TaskList
     * @param storage Storage to overwrite save file
     * @param ui Ui to print lines for user to see and interact with
     * @return message for user to see when a deadline task is added
     */
    private String handleDeadline(String[] inputStrings, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        int byIndex = checkDeadline(inputStrings);
        String description = String.join(" ", Arrays.copyOfRange(inputStrings, 1, byIndex));
        checkDescription(description);
        String deadline = String.join(" ", Arrays.copyOfRange(inputStrings,
                byIndex + 1, inputStrings.length));
        LocalDateTime formattedDeadline = convertTimestamp(deadline);
        Task newTask = new Deadline(description, formattedDeadline);
        TaskList updatedList = taskList.addTask(newTask);
        assert updatedList.getSize() == taskList.getSize() + 1 : "Task is not added!";
        storage.writeFile(updatedList);
        return ui.sayAddedTask(newTask, updatedList);
    }

    /**
     * Handles message to be printed for Event Tasks
     *
     * @param inputStrings String array of each word input by the user
     * @param taskList Current state of TaskList
     * @param storage Storage to overwrite save file
     * @param ui Ui to print lines for user to see and interact with
     * @return message for user to see when an event task is added
     */
    private String handleEvent(String[] inputStrings, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        int fromIndex = checkStarting(inputStrings);
        int toIndex = checkEnding(inputStrings);
        String description = String.join(" ", Arrays.copyOfRange(inputStrings, 1, fromIndex));
        checkDescription(description);
        String from = String.join(" ",
                Arrays.copyOfRange(inputStrings, fromIndex + 1, toIndex));
        LocalDateTime formattedFrom = convertTimestamp(from);
        String to = String.join(" ",
                Arrays.copyOfRange(inputStrings, toIndex + 1, inputStrings.length));
        LocalDateTime formattedTo = convertTimestamp(to);
        Task newTask = new Event(description, formattedFrom, formattedTo);
        TaskList updatedList = taskList.addTask(newTask);
        assert updatedList.getSize() == taskList.getSize() + 1 : "Task is not added!";
        storage.writeFile(updatedList);
        return ui.sayAddedTask(newTask, updatedList);
    }

    /**
     * Handles message to be printed for marking Tasks
     *
     * @param inputStrings String array of each word input by the user
     * @param taskList Current state of TaskList
     * @param storage Storage to overwrite save file
     * @param ui Ui to print lines for user to see and interact with
     * @return message for user to see when task is marked
     */
    private String handleMark(String[] inputStrings, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        // taskNumber in 1-indexing
        String taskNumber = getTaskNumber(inputStrings);
        // index in 0-indexing
        int index = checkTaskNumber(taskList, taskNumber);
        TaskList updatedList = taskList.markTask(index);
        Task newTask = updatedList.get(index);
        storage.writeFile(updatedList);
        assert !updatedList.equals(taskList) : "Task is not marked!";
        return ui.sayMarkedTask(newTask);
    }

    /**
     * Handles message to be printed for unmarking Tasks
     *
     * @param inputStrings String array of each word input by the user
     * @param taskList Current state of TaskList
     * @param storage Storage to overwrite save file
     * @param ui Ui to print lines for user to see and interact with
     * @return message for user to see when task is unmarked
     */
    private String handleUnmark(String[] inputStrings, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String taskNumber = getTaskNumber(inputStrings);
        int index = checkTaskNumber(taskList, taskNumber);
        TaskList updatedList = taskList.unmarkTask(index);
        Task newTask = updatedList.get(index);
        storage.writeFile(updatedList);
        assert !updatedList.equals(taskList) : "Task is not unmarked!";
        return ui.sayUnmarkedTask(newTask);
    }

    /**
     * Handles message to be printed for deleting Tasks
     *
     * @param inputStrings String array of each word input by the user
     * @param taskList Current state of TaskList
     * @param storage Storage to overwrite save file
     * @param ui Ui to print lines for user to see and interact with
     * @return message for user to see when task is deleted
     */
    private String handleDelete(String[] inputStrings, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        // taskNumber in 1-indexing
        String taskNumber = getTaskNumber(inputStrings);
        // index in 0-indexing
        int index = checkTaskNumber(taskList, taskNumber);
        Task deletedTask = taskList.get(index);
        TaskList updatedList = taskList.deleteTask(index);
        assert updatedList.getSize() == taskList.getSize() - 1 : "Task is not added!";
        storage.writeFile(updatedList);
        return ui.sayDeletedTask(deletedTask, updatedList);
    }

    /**
     * Handles message to be printed for findings Tasks
     *
     * @param inputStrings String array of each word input by the user
     * @param taskList Current state of TaskList
     * @param ui Ui to print lines for user to see and interact with
     * @return list of all matching tasks
     */
    private String handleFind(String[] inputStrings, TaskList taskList, Ui ui) throws DukeException {
        String keywords = getKeywords(inputStrings);
        TaskList matchingTasks = taskList.findTask(keywords);
        return ui.sayMatchingTasks(matchingTasks);
    }

    /**
     * Handles message to be printed out for viewing schedule on a specific date
     *
     * @param inputStrings String array of each word input by the user
     * @param taskList Current state of TaskList
     * @param ui Ui to print lines for user to see and interact with
     * @return schedule of tasks on the specified date
     */
    private String handleSchedule(String[] inputStrings, TaskList taskList, Ui ui) throws DukeException {
        String timePattern = "d MMM yyyy";
        String date = getDate(inputStrings);
        String formattedDateStr = convertDate(date).format(DateTimeFormatter.ofPattern(timePattern));
        System.out.println(formattedDateStr);
        TaskList scheduledTasks = taskList.findTask(formattedDateStr);
        return ui.sayScheduledTasks(scheduledTasks, formattedDateStr);
    }

    /**
     *  Converts String timestamp into LocalDateTime object
     *
     *  @param inputStrings String array of each word input by the user
     *  @param listOfCommands String array of all the commands
     *  @param taskList Current state of TaskList
     *  @param storage Storage to overwrite save file
     *  @param ui Ui to print lines for user to see and interact with
     *  @return Enum Command of the input given
     */
    public String executeCommand(String[] inputStrings, String[] listOfCommands, TaskList taskList,
                                        Storage storage, Ui ui) throws DukeException {
        String commandStr = inputStrings[0];
        Duke.Commands command = Duke.Commands.valueOf(checkCommand(commandStr, listOfCommands));
        String output;
        try {
            switch (command) {
            case list:
                output = ui.showList(taskList);
                break;
            case bye:
                output = ui.sayGoodbye();
                break;
            case todo:
                output = handleTodo(inputStrings, taskList, storage, ui);
                break;
            case deadline:
                output = handleDeadline(inputStrings, taskList, storage, ui);
                break;
            case event:
                output = handleEvent(inputStrings, taskList, storage, ui);
                break;
            case mark:
                output = handleMark(inputStrings, taskList, storage, ui);
                break;
            case unmark:
                output = handleUnmark(inputStrings, taskList, storage, ui);
                break;
            case delete:
                output = handleDelete(inputStrings, taskList, storage, ui);
                break;
            case find:
                output = handleFind(inputStrings, taskList, ui);
                break;
            case schedule:
                output = handleSchedule(inputStrings, taskList, ui);
                break;
            default:
                output = "";
            }
        } catch (DukeException err) {
            output = err.getErrorMessage();
        }
        return output;
    }
}
