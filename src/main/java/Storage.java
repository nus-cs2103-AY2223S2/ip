import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File taskFile;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage(String filePath) throws IOException {
        this.taskFile = new File(filePath);
        this.taskFile.getParentFile().mkdirs(); // Create parent directories if absent
        this.taskFile.createNewFile(); // Create task file if absent
    }

    // Loads saved task file
    public ArrayList<Task> load() throws FileNotFoundException, EmptyDescriptionException, InvalidTaskTypeException,
            EmptyDeadlineException, EmptyEndTimeException, EmptyStartTimeException, TaskDoneException {
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
                        try {
                            LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
                            currentTask = new Deadline(taskDescription, deadline);
                        } catch (DateTimeParseException e) {
                            System.out.println("Deadline must be in the format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31");
                        }
                    } else {
                        // Missing deadline for Deadline task
                        throw new EmptyDeadlineException();
                    }
                    break;
                case "E":
                    if (taskSplit.length == 5) {
                        String startTimeString = taskSplit[3].trim();
                        String endTimeString = taskSplit[4].trim();
                        try {
                            LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
                            LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);
                            currentTask = new Event(taskDescription, startTime, endTime);
                        } catch (DateTimeParseException e) {
                            System.out.println("Start time and end time must be in the format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31");
                        }
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

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(taskFile);
        fw.write(tasks.saveTasksString());
        fw.close();
    }
}
