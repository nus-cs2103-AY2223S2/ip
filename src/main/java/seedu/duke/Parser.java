package seedu.duke;

import seedu.duke.tasks.*;

import java.time.*;
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
            throw new DukeException("No keyword was given!");
        }
        return keywords;
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
    private static LocalDateTime convertTimestamp(String timestamp) throws DukeException {
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
        String output = "";
        try {
            String description;
            String taskNumber;
            Task newTask;
            TaskList updatedList;
            int index;
            switch (command) {
            case list:
                output = ui.showList(taskList);
                break;
            case bye:
                output = ui.sayGoodbye();
                break;
            case todo:
                description = String.join(" ", Arrays.copyOfRange(inputStrings, 1, inputStrings.length));
                checkDescription(description);
                newTask = new Todo(description);
                updatedList = taskList.addTask(newTask);
                storage.writeFile(updatedList);
                output = ui.sayAddedTask(newTask, updatedList);
                break;
            case deadline:
                int byIndex = checkDeadline(inputStrings);
                description = String.join(" ", Arrays.copyOfRange(inputStrings, 1, byIndex));
                checkDescription(description);
                String deadline = String.join(" ", Arrays.copyOfRange(inputStrings,
                        byIndex + 1, inputStrings.length));
                LocalDateTime formattedDeadline = convertTimestamp(deadline);
                newTask = new Deadline(description, formattedDeadline);
                updatedList = taskList.addTask(newTask);
                storage.writeFile(updatedList);
                output = ui.sayAddedTask(newTask, updatedList);
                break;
            case event:
                int fromIndex = checkStarting(inputStrings);
                int toIndex = checkEnding(inputStrings);
                description = String.join(" ", Arrays.copyOfRange(inputStrings, 1, fromIndex));
                checkDescription(description);
                String from = String.join(" ",
                        Arrays.copyOfRange(inputStrings, fromIndex + 1, toIndex));
                LocalDateTime formattedFrom = convertTimestamp(from);
                String to = String.join(" ",
                        Arrays.copyOfRange(inputStrings, toIndex + 1, inputStrings.length));
                LocalDateTime formattedTo = convertTimestamp(to);
                newTask = new Event(description, formattedFrom, formattedTo);
                updatedList = taskList.addTask(newTask);
                storage.writeFile(updatedList);
                output = ui.sayAddedTask(newTask, updatedList);
                break;
            case mark:
                // taskNumber in 1-indexing
                taskNumber = getTaskNumber(inputStrings);
                // index in 0-indexing
                index = checkTaskNumber(taskList, taskNumber);
                updatedList = taskList.markTask(index);
                newTask = updatedList.get(index);
                storage.writeFile(updatedList);
                output = ui.sayMarkedTask(newTask);
                break;
            case unmark:
                taskNumber = getTaskNumber(inputStrings);
                index = checkTaskNumber(taskList, taskNumber);
                updatedList = taskList.unmarkTask(index);
                newTask = updatedList.get(index);
                output = ui.sayUnmarkedTask(newTask);
                break;
            case delete:
                // taskNumber in 1-indexing
                taskNumber = getTaskNumber(inputStrings);
                // index in 0-indexing
                index = checkTaskNumber(taskList, taskNumber);
                Task deletedTask = taskList.get(index);
                updatedList = taskList.deleteTask(index);
                storage.writeFile(updatedList);
                output = ui.sayDeletedTask(deletedTask, updatedList);
                break;
            case find:
                String keywords = getKeywords(inputStrings);
                TaskList matchingTasks = taskList.findTask(keywords);
                output = ui.sayMatchingTasks(matchingTasks);
                break;
            }
        } catch (DukeException err) {
            output = err.getErrorMessage();
        }
        return output;
    }
}
