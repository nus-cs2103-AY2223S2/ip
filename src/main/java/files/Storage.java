package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import parsers.TaskInfoParser;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents an internal storage for Duke and also a medium for Duke to read, load and save tasks
 * to and from files of a specific filepath.
 */
public class Storage {

    static final String BANNER = "____________________________________________________________";

    /**
     * Returns a task list after loading all the tasks from a file.
     * Creates a path and a file if file does not exist and then return an empty task list.
     * @param filePath the path of the file from which Storage will load tasks from
     * @return a task list containing tasks read from file
     * @throws IOException if reading is not successful
     */
    public static TaskList loadData(String filePath) throws IOException {
        TaskList taskList = new TaskList();
        try {
            taskList = readFromFile(filePath);
            System.out.println("Hrmm Hrmm, some past tasks I see!!\n'list' command to see more, you must enter");
            return taskList;
        } catch (FileNotFoundException e) {
            Path path = Paths.get("src/main/data");
            Files.createDirectories(path);
            File newTaskFile = new File(filePath);
            newTaskFile.createNewFile();
            taskList = new TaskList();
            return taskList;
        }
    }

    /**
     * Returns a task list from which the file's tasks will be read and stored into only if the file exists.
     * @param path the path of the file from which Storage will retrieve data from
     * @return a task list containing tasks which are read from file
     * @throws FileNotFoundException if file does not exist at the specified path
     */
    public static TaskList readFromFile(String path) throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File file = new File(path);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] commandArray = line.trim().split(" ");
            Task task = TaskInfoParser.parse(commandArray);
            taskList.addTaskSilent(task);
        }
        fileScanner.close();
        return taskList;
    }

    /**
     * Saves tasks into file located at the filepath specified.
     * @param filepath path of the file to write tasks into
     * @param taskList task list containing task to write to file
     */
    public String saveData(String filepath, TaskList taskList) {
        return DukeFileWriter.writeToFile(filepath, taskList);
    }
}
