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
            String taskTypeFull = "";
            switch (taskType) {
            case "T":
                taskTypeFull = "todo";
                break;
            case "D":
                taskTypeFull = "deadline";
                break;
            case "E":
                taskTypeFull = "event";
                break;
            default:
                throw new InvalidTaskTypeException();
            }
            if (taskSplit.length >= 3) {
                boolean isTaskDone = taskSplit[1].trim().equals("1");
                String taskDescription = taskSplit[2].trim();
                Task currentTask = null;
                switch (taskType) {
                case "T":
                    currentTask = new ToDo(taskDescription);
                    break;
                case "D":
                    if (taskSplit.length == 4) {
                        String deadlineString = taskSplit[3].trim();
                        LocalDateTime deadline = LocalDateTime.parse(deadlineString, FORMATTER);
                        currentTask = new Deadline(taskDescription, deadline);
                    } else {
                        // Missing deadline for Deadline task
                        throw new EmptyDeadlineException();
                    }
                    break;
                case "E":
                    if (taskSplit.length == 5) {
                        String startTimeString = taskSplit[3].trim();
                        String endTimeString = taskSplit[4].trim();
                        LocalDateTime startTime = LocalDateTime.parse(startTimeString, FORMATTER);
                        LocalDateTime endTime = LocalDateTime.parse(endTimeString, FORMATTER);
                        currentTask = new Event(taskDescription, startTime, endTime);
                    } else {
                        if (taskSplit.length == 4) {
                            // Missing end time for Event task
                            throw new EmptyEndTimeException();
                        } else if (taskSplit.length == 3) {
                            // Missing start time for Event task
                            throw new EmptyStartTimeException();
                        }
                    }
                    break;
                default:
                    throw new InvalidTaskTypeException();
                }
                if (isTaskDone) {
                    currentTask.setDone();
                }
                tasks.add(currentTask);
            } else {
                // Missing description for task
                throw new EmptyDescriptionException(taskTypeFull);
            }
        }
        return tasks;
    }
}
