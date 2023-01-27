package seedu.duke;

import seedu.duke.tasks.*;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class Parser {

    public String checkCommand(String command, String[] listOfCommands) throws DukeException {
        for (String cmd : listOfCommands) {
            if (cmd.equals(command)) {
                return command;
            }
        }
        throw new DukeException("I don't know what this command means!");
    }

    public String getTaskNumber(String[] inputStrings) throws DukeException {
        if (inputStrings.length == 1) {
            throw new DukeException("No task number was given!");
        } else {
            return inputStrings[1];
        }
    }

    public int checkTaskNumber(TaskList taskList, String taskNumber) throws DukeException {
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

    public void checkDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("There was no task description given!");
        }
    }

    public int checkDeadline(String[] inputStrings) throws DukeException {
        int byIndex = Arrays.asList(inputStrings).indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("There was no deadline given!");
        } else {
            return byIndex;
        }
    }

    public int checkStarting(String[] inputStrings) throws DukeException {
        int byIndex = Arrays.asList(inputStrings).indexOf("/from");
        if (byIndex == -1) {
            throw new DukeException("Please indicate a starting period!");
        } else {
            return byIndex;
        }
    }

    public int checkEnding(String[] inputStrings) throws DukeException {
        int byIndex = Arrays.asList(inputStrings).indexOf("/to");
        if (byIndex == -1) {
            throw new DukeException("Please indicate an ending period!");
        } else {
            return byIndex;
        }
    }

    public static LocalDateTime convertTimestamp(String timestamp) throws DukeException {
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

    public Duke.Commands executeCommand(String[] inputStrings, String[] listOfCommands,TaskList taskList,
                                        Storage storage, Ui ui) throws DukeException {
        String commandStr = inputStrings[0];
        Duke.Commands command = Duke.Commands.valueOf(checkCommand(commandStr, listOfCommands));
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
                description = String.join(" ", Arrays.copyOfRange(inputStrings, 1, inputStrings.length));
                checkDescription(description);
                newTask = new Todo(description);
                updatedList = taskList.addTask(newTask);
                storage.writeFile(updatedList);
                ui.sayAddedTask(newTask, updatedList);
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
                ui.sayAddedTask(newTask, updatedList);
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
                ui.sayAddedTask(newTask, updatedList);
                break;
            case mark:
                // taskNumber in 1-indexing
                taskNumber = getTaskNumber(inputStrings);
                // index in 0-indexing
                index = checkTaskNumber(taskList, taskNumber);
                updatedList = taskList.markTask(index);
                newTask = updatedList.get(index);
                storage.writeFile(updatedList);
                ui.sayMarkedTask(newTask);
                break;
            case unmark:
                taskNumber = getTaskNumber(inputStrings);
                index = checkTaskNumber(taskList, taskNumber);
                updatedList = taskList.unmarkTask(index);
                newTask = updatedList.get(index);
                ui.sayUnmarkedTask(newTask);
                break;
            case delete:
                // taskNumber in 1-indexing
                taskNumber = getTaskNumber(inputStrings);
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
