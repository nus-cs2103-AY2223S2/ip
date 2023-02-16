package tasks;



import exception.InvalidDateTimeFormatException;
import exception.MissingTaskDescriptionException;
import exception.TaskFactoryException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskFactory {

    /**
     * Returns a Task according to the given instruction.
     * Parses the instruction to determine which type of Command to return.
     *
     * @param instruction
     * @return Task object as specified by the instruction.
     * @throws TaskFactoryException
     */
    public Task make(String instruction) throws TaskFactoryException {
        String[] splitStr = instruction.split("\\s+", 2);
        String taskType = splitStr[0];

        try {
            switch (taskType) {
            case "todo":
                return makeTodo(instruction);
            case "deadline":
                return makeDeadline(instruction);
            case "event":
                return makeEvent(instruction);
            default:
                throw new TaskFactoryException("Invalid task type");
            }
        } catch (MissingTaskDescriptionException | InvalidDateTimeFormatException e) {
            throw e;
        }

    }

    private Task makeTodo(String instruction) throws MissingTaskDescriptionException {
        String[] splitStr = instruction.split("\\s+", 2);

        if (splitStr.length < 2) {
            throw new MissingTaskDescriptionException("Task Description cannot be empty!");
        }

        String taskDescription = splitStr[1];
        return new Todo(taskDescription);
    }

    private Task makeDeadline(String instruction) throws InvalidDateTimeFormatException {
        String[] splitStr = instruction.split("\\s+", 2);
        String taskDescription = splitStr[1].split(" /by ")[0];
        String taskDeadline =splitStr[1].split(" /by ")[1];
        try {
            LocalDateTime taskDeadlineDateTime = parseDate(taskDeadline);
            return new Deadline(taskDescription, taskDeadlineDateTime);
        } catch (DateTimeException e) {
            throw new InvalidDateTimeFormatException("Invalid date time format for deadline");
        }

    }

    private Task makeEvent(String instruction) throws InvalidDateTimeFormatException {
        String[] splitStr = instruction.split("\\s+", 2);
        String taskDescription = splitStr[1].split(" /from ")[0];
        String taskStart =splitStr[1].split(" /from ")[1].split(" /to ")[0];
        String taskEnd =splitStr[1].split(" /from ")[1].split(" /to ")[1];

        try {
            LocalDateTime taskStartDateTime = parseDate(taskStart);
            LocalDateTime taskEndDateTime = parseDate(taskEnd);
            return new Event(taskDescription, taskStartDateTime, taskEndDateTime);
        } catch (DateTimeException e) {
            throw new InvalidDateTimeFormatException("invalid date time format");
        }

    }

    private LocalDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
            return dateTime;
        } catch (DateTimeException e) {
           throw e;
        }


    }

}
