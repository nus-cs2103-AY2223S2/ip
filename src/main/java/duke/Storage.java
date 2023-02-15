package duke;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    /** String representation of user home directory. */
    String home = System.getProperty("user.home");
    /** Path object of storage entry. */
    Path filePath;
    /** File object of storage entry. */
    File dukeDataFile;

    /**
     * Constructor for the Storage class.
     *
     * @param s The file storage class will operate on.
     */
    public Storage(String s) {
        filePath = Paths.get(home, "data", s);
    }

    //TODO: Just a test, will remove later
    public void dirTest() {
        try {
            dukeDataFile = new File(filePath.toString());
            FileUtils.write(dukeDataFile, "Test\ning");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method loads the tasks in storage to TaskList.
     *
     * @return New TaskList if storage is empty, else the loaded TaskList
     * @throws DukeException If there is any issues with reading the file
     */
    public TaskList load() throws DukeException{
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

     */
        List<String> allLines;
        TaskList resultTaskList;
        allLines = readFileAsList(filePath);
        resultTaskList = convertListToTaskList(allLines);
        return resultTaskList;
    }


    private List<String> readFileAsList(Path filePath) {
        List<String> allLines;
        try {
            allLines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allLines;
    }


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

    private void createNewFile(File fileToWrite) {
        try {
            FileUtils.write(fileToWrite, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
