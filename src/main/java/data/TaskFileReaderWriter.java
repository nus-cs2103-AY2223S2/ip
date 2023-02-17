package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import utils.Utility;

/**
 * The TaskFileReaderWriter class provides methods for reading and writing tasks to and from a file.
 * It includes methods for loading tasks from a file and updating a task file with tasks from a
 * TaskManager object, and creating a new task file.
 * @author Nicholas Lee
 */
public class TaskFileReaderWriter {

    private static final String DIRECTORY_NAME = "userData";
    private static final String FILE_NAME = "taskMasterData.txt";

    public TaskFileReaderWriter() {}

    /**
     * Creates a new task storage .txt file in the user's drive in a specified directory and file name.
     *
     * @return boolean value indicating the success of the file creation.
     */
    public boolean createTaskFile() {

        File taskFile = new File(DIRECTORY_NAME + File.separator + FILE_NAME);

        if (!taskFile.exists()) {
            boolean isMkdirSuccess = taskFile.getParentFile().mkdirs();
            if (!isMkdirSuccess) {
                return false;
            }
        }

        try {
            boolean isNewFileSuccess = taskFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Converts a string representation of a task from storage file
     * on the user's drive into a {@link Task} object.
     *
     * @param taskString The string representation of the task in the format of
     *                   "category|isCompleted|details|(deadline/start) (end)"
     * @return The created Task object
     */
    public Task readTaskString(String taskString) {

        String[] taskArr = taskString.split("\\|");
        String category = taskArr[0];
        boolean isCompleted = Boolean.parseBoolean(taskArr[1]);
        String details = taskArr[2];

        Task newTask = null;

        switch (category) {
        case "Deadline":
            String deadline = taskArr[3];
            LocalDateTime deadlineDate = Utility.convertStringToDateTime(deadline);
            newTask = new Deadline(details, deadlineDate, isCompleted);
            break;
        case "Event":
            String start = taskArr[3];
            String end = taskArr[4];
            LocalDateTime startDate = Utility.convertStringToDateTime(start);
            LocalDateTime endDate = Utility.convertStringToDateTime(end);
            newTask = new Event(details, startDate, endDate, isCompleted);
            break;
        case "To-Do":
            newTask = new ToDo(details, isCompleted);
            break;
        default:
            break;

        }
        return newTask;
    }

    /**
     * Updates the file on the user's drive with the tasks in a given {@link TaskManager} object.
     *
     * @param taskManager The TaskManager object containing the tasks to be written to the file.
     * @return boolean value indicating the success of the file update operation.
     */
    public boolean updateTaskFile(TaskManager taskManager) {
        File taskFile = new File(DIRECTORY_NAME + File.separator + FILE_NAME);

        String taskString = null;
        String details;
        boolean isCompleted;

        try (FileWriter fileWriter = new FileWriter(taskFile)) {
            for (Task task : taskManager.getTasks()) {
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    details = deadlineTask.getDetails();
                    String deadline = deadlineTask.getDeadline();
                    isCompleted = deadlineTask.isCompleted();
                    taskString = "Deadline|" + isCompleted + "|" + details + "|" + deadline;

                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    details = eventTask.getDetails();
                    String start = eventTask.getStartString();
                    String end = eventTask.getEndString();
                    isCompleted = eventTask.isCompleted();
                    taskString = "Event|" + isCompleted + "|" + details + "|" + start + "|" + end;

                } else if (task instanceof ToDo) {
                    ToDo toDoTask = (ToDo) task;
                    details = toDoTask.getDetails();
                    isCompleted = toDoTask.isCompleted();
                    taskString = "To-Do|" + isCompleted + "|" + details;
                }
                assert taskString != null;
                fileWriter.write(taskString + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
      * Reads data from a file and creates a new TaskManager object.
      * @return A TaskManager object initialised with the tasks read from the file.
      */
    public TaskManager loadDataFromFile() {
        TaskManager taskManager = new TaskManager();
        try {
            Scanner scanner = new Scanner(new File(DIRECTORY_NAME + File.separator + FILE_NAME));

            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();

                if (!taskString.isBlank()) {
                    Task task = readTaskString(taskString);
                    taskManager.addTask(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taskManager;
    }
}
