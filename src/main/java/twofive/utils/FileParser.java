package twofive.utils;

import twofive.exception.*;
import twofive.task.Deadline;
import twofive.task.Event;
import twofive.task.Task;
import twofive.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private File taskFile;

    public FileParser(File taskFile) {
        this.taskFile = taskFile;
    }

    public ArrayList<Task> parseFile() throws FileNotFoundException, EmptyDescriptionException, InvalidTaskTypeException,
            EmptyDeadlineException, EmptyEndTimeException, EmptyStartTimeException, TaskDoneException, DateTimeParseException {
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
                        LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
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
                        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
                        LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);
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
