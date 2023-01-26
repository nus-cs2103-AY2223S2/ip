package seedu.duke;

import seedu.duke.Tasks.*;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class Parser {

    /**
     *  Checks the validity of the command
     *  @param command String of the command sent by user
     *  @param commandsList String array of all the commands
     *  @return String of the command if valid
     */
    private String checkCommand(String command, String[] commandsList) throws DukeException {
        for (String cmd : commandsList) {
            if (cmd.equals(command)) {
                return command;
            }
        }
        throw new DukeException("I don't know what this command means!");
    }

    /**
     *  Checks whether task number is given
     *  @param splitInput String array of each word input by the user
     *  @return String of the task number if it exists
     */
    private String getTaskNumber(String[] splitInput) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("No task number was given!");
        } else {
            return splitInput[1];
        }
    }

    /**
     *  Checks whether task number is numeric & valid
     *  @param taskList Current state of TaskList
     *  @param taskNumber String of the taskNumber
     *  @return Integer of the task number if it is valid
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
     *  @param description Description of the Task
     */
    private void checkDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("There was no task description given!");
        }
    }

    /**
     *  Checks whether deadline is given
     *  @param splitInput String array of each word input by the user
     *  @return Index of /by in the array
     */
    private int checkDeadline(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("There was no deadline given!");
        } else {
            return byIndex;
        }
    }

    /**
     *  Checks whether starting time stamp for Event is given
     *  @param splitInput String array of each word input by the user
     *  @return Index of /from in splitInput
     */
    private int checkStarting(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/from");
        if (byIndex == -1) {
            throw new DukeException("Please indicate a starting period!");
        } else {
            return byIndex;
        }
    }

    /**
     *  Checks whether ending time stamp for Event is given
     *  @param splitInput String array of each word input by the user
     *  @return Index of /to in splitInput
     */
    private int checkEnding(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/to");
        if (byIndex == -1) {
            throw new DukeException("Please indicate an ending period!");
        } else {
            return byIndex;
        }
    }

    /**
     *  Converts String timestamp into LocalDateTime object
     *  @param timestamp String of the timestamp given
     *  @return LocalDateTime object of timestamp
     */
    private static LocalDateTime convertTimestamp(String timestamp) throws DukeException {
        if (timestamp.equals("")) {
            throw new DukeException("There was no time period given!");
        }
        try {
            String[] dateTime = timestamp.split(" ");
            LocalDate date = LocalDate.parse(dateTime[0]);
            int hour = Integer.parseInt(dateTime[1].substring(0, 2));
            int min = Integer.parseInt(dateTime[1].substring(2, 4));
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
     *  @param splitInput String array of each word input by the user
     *  @param commandList String array of all the commands
     *  @param taskList Current state of TaskList
     *  @param storage Storage to overwrite save file
     *  @param ui Ui to print lines for user to see and interact with
     *  @return Enum Command of the input given
     */
    public Duke.Commands executeCommand(String[] splitInput, String[] commandList,TaskList taskList,
                                        Storage storage, Ui ui) throws DukeException {
        String commandStr = splitInput[0];
        Duke.Commands command = Duke.Commands.valueOf(checkCommand(commandStr, commandList));
        try {
            String description;
            String taskNumber;
            Task newTask;
            TaskList updatedList;
            int index;
            switch (command) {
            case list:
                ui.showList(taskList);
                break;
            case bye:
                ui.sayGoodbye();
                break;
            case todo:
                description = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length));
                checkDescription(description);
                newTask = new Todo(description);
                updatedList = taskList.addTask(newTask);
                storage.writeFile(updatedList);
                ui.sayAddedTask(newTask, updatedList);
                break;
            case deadline:
                int byIndex = checkDeadline(splitInput);
                description = String.join(" ", Arrays.copyOfRange(splitInput, 1, byIndex));
                checkDescription(description);
                String deadline = String.join(" ", Arrays.copyOfRange(splitInput,
                        byIndex + 1, splitInput.length));
                LocalDateTime formattedDeadline = convertTimestamp(deadline);
                newTask = new Deadline(description, formattedDeadline);
                updatedList = taskList.addTask(newTask);
                storage.writeFile(updatedList);
                ui.sayAddedTask(newTask, updatedList);
                break;
            case event:
                int fromIndex = checkStarting(splitInput);
                int toIndex = checkEnding(splitInput);
                description = String.join(" ", Arrays.copyOfRange(splitInput, 1, fromIndex));
                checkDescription(description);
                String from = String.join(" ",
                        Arrays.copyOfRange(splitInput, fromIndex + 1, toIndex));
                LocalDateTime formattedFrom = convertTimestamp(from);
                String to = String.join(" ",
                        Arrays.copyOfRange(splitInput, toIndex + 1, splitInput.length));
                LocalDateTime formattedTo = convertTimestamp(to);
                newTask = new Event(description, formattedFrom, formattedTo);
                updatedList = taskList.addTask(newTask);
                storage.writeFile(updatedList);
                ui.sayAddedTask(newTask, updatedList);
                break;
            case mark:
                // taskNumber in 1-indexing
                taskNumber = getTaskNumber(splitInput);
                // index in 0-indexing
                index = checkTaskNumber(taskList, taskNumber);
                updatedList = taskList.markTask(index);
                newTask = updatedList.get(index);
                storage.writeFile(updatedList);
                ui.sayMarkedTask(newTask);
                break;
            case unmark:
                taskNumber = getTaskNumber(splitInput);
                index = checkTaskNumber(taskList, taskNumber);
                updatedList = taskList.unmarkTask(index);
                newTask = updatedList.get(index);
                ui.sayUnmarkedTask(newTask);
                break;
            case delete:
                // taskNumber in 1-indexing
                taskNumber = getTaskNumber(splitInput);
                // index in 0-indexing
                index = checkTaskNumber(taskList, taskNumber);
                Task deletedTask = taskList.get(index);
                updatedList = taskList.deleteTask(index);
                storage.writeFile(updatedList);
                ui.sayDeletedTask(deletedTask, updatedList);
                break;
            }
        } catch (DukeException err) {
            System.out.println(err.getErrorMessage());
        }
        return command;
    }
}
