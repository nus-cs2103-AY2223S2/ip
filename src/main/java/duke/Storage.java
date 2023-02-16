package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;


/**
 * This is the class that processes the storage needs of Duke.
 */
public class Storage {

    /** String representation of user home directory. */
    private String home = System.getProperty("user.home");
    /** Path object of storage entry. */
    private Path filePath;
    /** File object of storage entry. */
    private File dukeDataFile;

    /**
     * Constructor for the Storage class.
     *
     * @param s The file storage class will operate on.
     */
    public Storage(String s) {
        filePath = Paths.get(home, "data", s);
    }

    /**
     * This method loads the tasks in storage to TaskList.
     *
     * @return New TaskList if storage is empty, else the loaded TaskList
     * @throws DukeException If there is any issues with reading the file
     */
    public TaskList load() throws DukeException {
        TaskList result;
        dukeDataFile = new File(filePath.toString());
        if (Files.exists(filePath)) {
            result = convertFileToTaskList(filePath);
        } else {
            createNewFile(dukeDataFile);
            result = new TaskList();
        }
        return result;
    }

    /**
     * This method saves the passed in TaskList to storage.
     *
     * @param saveTaskList The TaskList to save to storage.
     */
    public void save(TaskList saveTaskList) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < saveTaskList.tasks.size(); i++) {
            outputString.append(saveTaskList.tasks.get(i).asCsv());
            outputString.append(System.getProperty("line.separator"));
        }
        try {
            FileUtils.writeStringToFile(dukeDataFile, outputString.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method converts the saved file to a TaskList.
     *
     * @param filePath The File to be converted.
     * @return TaskList after conversion.
     */
    private TaskList convertFileToTaskList(Path filePath) {
        List<String> allLines;
        TaskList resultTaskList;
        allLines = readFileAsList(filePath);

        resultTaskList = convertListToTaskList(allLines);
        return resultTaskList;
    }

    /**
     * This method reads the file in the path provided as a List of Strings.
     *
     * @param filePath The File to be read.
     * @return List of Strings after reading.
     */
    private List<String> readFileAsList(Path filePath) {
        List<String> allLines;
        try {
            allLines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allLines;
    }

    /**
     * This method converts the list of Tasks represented as Strings into a TaskList.
     *
     * @param list The list of tasks to be converted.
     * @return The TaskList after conversion.
     */
    private TaskList convertListToTaskList(List<String> list) {
        TaskList result;
        result = new TaskList();

        for (int i = 0; i < list.size(); i++) {
            String currString = list.get(i);
            Task curr = convertStringToTask(currString);
            result.addTask(curr);
        }
        return result;
    }

    /**
     * This method converts individual Task represented as String into Task.
     *
     * @param itemString The task represented in String form.
     * @return The task after it is converted.
     */
    private Task convertStringToTask(String itemString) {
        String[] lineArray;
        boolean taskStatus;
        Task resultingTask;

        resultingTask = null;
        lineArray = itemString.split(",");
        taskStatus = lineArray[1].equals("1");

        switch (lineArray[0]) {
        case "T":
            resultingTask = new ToDo(lineArray[2], taskStatus);
            break;
        case "D":
            resultingTask = new Deadline(lineArray[2], lineArray[3], taskStatus);
            break;
        case "E":
            resultingTask = new Event(lineArray[2], lineArray[3], lineArray[4], taskStatus);
            break;
        default:
            break;
        }

        return resultingTask;
    }

    /**
     * This method creates a new file if the file do not exist.
     *
     * @param fileToWrite The file to be created.
     */
    private void createNewFile(File fileToWrite) {
        try {
            FileUtils.write(fileToWrite, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
