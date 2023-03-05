package cluck.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import cluck.exceptions.CorruptedDataException;
import cluck.tasklist.TaskList;
import cluck.tasks.Task;


/**
 * Storage class handles the reading and writing of saved tasks in a given .txt file.
 */
public class Storage {
    private File saveFile;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path of the saved tasks
     */

    private String home = System.getProperty("user.home");
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(home, "data", filePath);

        // Create tasks file if it doesn't exist
        saveFile = new File(this.filePath.toString());
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (Exception e) {
                System.out.println("Crapadoodle! I couldn't create a new file. Something went wrong.");
            }
        }
    }

    public TaskList populateTaskList() {
        TaskList taskList = new TaskList();
        Scanner savedFileScanner;
        try {
            savedFileScanner = new Scanner(saveFile);
            saveFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return new TaskList();
        }
        String taskString;
        Task currTask;
        while (savedFileScanner.hasNextLine()) {
            taskString = savedFileScanner.nextLine();
            try {
                currTask = Task.buildTaskFromSave(taskString);
                taskList.addTask(currTask);
            } catch (CorruptedDataException e) {
                System.out.println(e);
            }
        }
        return taskList;
    }

    /**
     * Saves the current list of tasks into a txt file.
     * This will overwrite previous saves.
     * There should be no missing directory error since readSave()
     * will create the save directory if it does not exist.
     *
     * @param taskList list of task to be saved
     */
    public void writetoSave(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(saveFile);
            writer.write(taskList.toSaveFormat());
            writer.close();
        } catch (IOException e) {
            System.out.println("Buh oh! An error occurred!!");
            e.printStackTrace();
        }
    }
}
