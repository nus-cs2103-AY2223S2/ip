package seedu.duke;

import seedu.duke.tasks.*;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class Parser {

    public String checkCommand(String command, String[] commandsList) throws DukeException {
        for (String cmd : commandsList) {
            if (cmd.equals(command)) {
                return command;
            }
        }
        throw new DukeException("I don't know what this command means!");
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

    public String getTaskNumber(String[] splitInput) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("No task number was given!");
        } else {
            return splitInput[1];
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

    public int checkDeadline(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("There was no deadline given!");
        } else {
            return byIndex;
        }
    }

    public int checkStarting(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/from");
        if (byIndex == -1) {
            throw new DukeException("Please indicate a starting period!");
        } else {
            return byIndex;
        }
    }

    public int checkEnding(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/to");
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

    public Duke.Commands executeCommand(String[] inputStrings, String[] commandList, TaskList taskList,
                                        Storage storage, Ui ui) throws DukeException {
        String commandStr = inputStrings[0];
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
            case find:
                String keywords = getKeywords(inputStrings);
                TaskList matchingTasks = taskList.find(keywords);
                ui.sayMatchingTasks(matchingTasks);
                break;
            }
        } catch (DukeException err) {
            System.out.println(err.getErrorMessage());
        }
        return command;
    }
}
