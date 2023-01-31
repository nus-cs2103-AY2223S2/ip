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
    /** TaskList to be loaded from storage. */
    TaskList loadTaskList;

    /**
     * Constructor for the Storage class.
     *
     * @param s The file storage class will operate on.
     */
    public Storage(String s) {
        filePath = Paths.get(home, "data", s);
        loadTaskList = new TaskList();
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
        dukeDataFile = new File(filePath.toString());
        if (Files.exists(filePath)) {
            System.out.println("FILE EXIST");
            TaskList loadTaskList= new TaskList();
            try {
                List<String> allLines = Files.readAllLines(filePath);
                for (String line : allLines) {
                    String[] lineArray = line.split(",");
                    boolean b;
                    if (lineArray[1].equals("1")) {
                        b = true;
                    } else {
                        b = false;
                    }
                    switch (lineArray[0]) {
                        case "T":
                            loadTaskList.addTask(lineArray[2], b);
                            break;
                        case "D":
                            loadTaskList.addTask(lineArray[2], lineArray[3], b);
                            break;
                        case "E":
                            loadTaskList.addTask(lineArray[2], lineArray[3], lineArray[4], b);
                            break;
                        case "":
                            break;
                    }
                }
                return loadTaskList;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("NO EXIST");
            try {
                FileUtils.write(dukeDataFile, "");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new TaskList();
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
}
