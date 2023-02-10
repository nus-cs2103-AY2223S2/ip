package twofive.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import twofive.exception.EmptyDeadlineException;
import twofive.exception.EmptyDescriptionException;
import twofive.exception.EmptyEndTimeException;
import twofive.exception.EmptyStartTimeException;
import twofive.exception.InvalidTaskTypeException;
import twofive.exception.TaskDoneException;
import twofive.task.Deadline;
import twofive.task.Event;
import twofive.task.Task;
import twofive.task.ToDo;

/**
 * Parses the contents of a given file into a list of tasks.
 */
public class FileParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private File taskFile;

    public FileParser(File taskFile) {
        this.taskFile = taskFile;
    }

    /**
     * Parses the contents of the task file provided when initializing
     * the file parser into a list of objects of the type ToDo, Deadline
     * or Event.
     *
     * @return ArrayList containing Task objects.
     * @throws FileNotFoundException If task file cannot be found.
     * @throws EmptyDescriptionException If the description is absent in one or more tasks parsed.
     * @throws InvalidTaskTypeException If the task type is invalid for one or more tasks parsed.
     * @throws EmptyDeadlineException If the deadline is absent in one or more Deadline tasks parsed.
     * @throws EmptyEndTimeException If the end time is absent in one or more Event tasks parsed.
     * @throws EmptyStartTimeException If the start time is absent in one or more Event tasks parsed.
     * @throws TaskDoneException If a parsed task that is already done is being marked as done.
     * @throws DateTimeParseException If the date and time format retrieved from the file is invalid.
     */
    public ArrayList<Task> parseFile()
            throws FileNotFoundException, EmptyDescriptionException, InvalidTaskTypeException,
            EmptyDeadlineException, EmptyEndTimeException, EmptyStartTimeException, TaskDoneException,
            DateTimeParseException {
        Scanner s = new Scanner(this.taskFile);
        ArrayList<Task> tasks = new ArrayList<>();

        // Read tasks from file
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] taskSplit = nextLine.split("\\|");
            String taskType = taskSplit[0].trim();
            String taskTypeFull = parseTaskTypeFull(taskType);

            if (taskSplit.length < 3) {
                throw new EmptyDescriptionException(taskTypeFull);
            }

            boolean isTaskDone = taskSplit[1].trim().equals("1");
            String taskDescription = taskSplit[2].trim();
            Task currentTask = parseTask(taskType, taskDescription, taskSplit);
            if (isTaskDone) {
                currentTask.setDone();
            }
            tasks.add(currentTask);
        }
        return tasks;
    }

    private String parseTaskTypeFull(String taskType) throws InvalidTaskTypeException {
        switch (taskType) {
        case "T":
            return "todo";
        case "D":
            return "deadline";
        case "E":
            return "event";
        default:
            throw new InvalidTaskTypeException();
        }
    }

    private Task parseTask(String taskType, String taskDescription, String[] taskSplit)
            throws InvalidTaskTypeException, EmptyDeadlineException, EmptyStartTimeException, EmptyEndTimeException {
        switch (taskType) {
        case "T":
            return new ToDo(taskDescription);
        case "D":
            if (taskSplit.length < 4) {
                // Missing deadline for Deadline task
                throw new EmptyDeadlineException();
            }
            String deadlineString = taskSplit[3].trim();
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, FORMATTER);
            return new Deadline(taskDescription, deadline);
        case "E":
            if (taskSplit.length == 3) {
                // Missing start time for Event task
                throw new EmptyStartTimeException();
            } else if (taskSplit.length == 4) {
                // Missing end time for Event task
                throw new EmptyEndTimeException();
            }
            String startTimeString = taskSplit[3].trim();
            String endTimeString = taskSplit[4].trim();
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, FORMATTER);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, FORMATTER);
            return new Event(taskDescription, startTime, endTime);
        default:
            throw new InvalidTaskTypeException();
        }
    }
}
