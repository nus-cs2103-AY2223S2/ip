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
     * @param
     * @return Task object as specified by the instruction.
     * @throws TaskFactoryException
     */
    public Task make(String taskType, String parameters) throws TaskFactoryException {

        try {
            switch (taskType) {
            case "todo":
                return makeTodo(parameters);
            case "deadline":
                return makeDeadline(parameters);
            case "event":
                return makeEvent(parameters);
            default:
                throw new TaskFactoryException("Invalid task type");
            }
        } catch (MissingTaskDescriptionException | InvalidDateTimeFormatException e) {
            throw e;
        }

    }

    private Task makeTodo(String parameter) throws MissingTaskDescriptionException {
        String taskDescription = parameter;
        if (taskDescription.isEmpty()) {
            throw new MissingTaskDescriptionException("Task Description cannot be empty!");
        }

        return new Todo(taskDescription);
    }

    private Task makeDeadline(String parameter) throws InvalidDateTimeFormatException {
        String taskDescription = parameter.split(" /by ", 2)[0];
        String taskDeadline = parameter.split(" /by ",2 )[1];

        try {
            LocalDateTime taskDeadlineDateTime = parseDate(taskDeadline);
            return new Deadline(taskDescription, taskDeadlineDateTime);
        } catch (DateTimeException e) {
            throw new InvalidDateTimeFormatException("Invalid date time format for deadline");
        }

    }

    private Task makeEvent(String parameter) throws InvalidDateTimeFormatException {
        String taskDescription = parameter.split(" /from ", 2)[0];
        String taskStart = parameter.split(" /from ", 2)[1].split(" /to ",2)[0];
        String taskEnd = parameter.split(" /from ", 2)[1].split(" /to ",2)[1];

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
