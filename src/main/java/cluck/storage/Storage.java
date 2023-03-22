package cluck.storage;

import java.io.File;
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

    private String directory;
    private Path filePath;
    private File saveFile;
    private File saveDir;


    /**
     * Instantiates a new Storage.
     *
     * @param filePath File path of the saved tasks.
     */
    public Storage(String filePath) {
        this.directory = System.getProperty("user.dir");
        this.filePath = Paths.get(directory, filePath);
        this.saveFile = this.filePath.toFile();
        this.saveDir = this.saveFile.getParentFile();
    }


    /**
     * Reads saved file at save location, returns a task list class containing the saved tasks.
     * If the file does not exist, the method returns an empty task list.
     *
     * @return Task list containing saved tasks or empty task list.
     */
    public TaskList readSave() {
        try {
            if (saveDir.mkdir()) {
                saveFile.createNewFile();
                return new TaskList();
            } else if (saveFile.createNewFile()) {
                return new TaskList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.populateTaskList();
    }

    private TaskList populateTaskList() {
        String taskString;
        Task currTask;
        TaskList taskList = new TaskList();
        Scanner savedFileScanner;

        try {
            savedFileScanner = new Scanner(this.saveFile);
            while (savedFileScanner.hasNextLine()) {
                taskString = savedFileScanner.nextLine();
                currTask = Task.buildTaskFromSave(taskString);
                taskList.addTask(currTask);
            }
        } catch (IOException | CorruptedDataException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Saves the current list of tasks into a txt file.
     * This will overwrite previous saves.
     * There should be no missing directory error since readSave()
     * will create the save directory if it does not exist.
     *
     * @param taskList TaskList instance containing tasks to be saved.
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
