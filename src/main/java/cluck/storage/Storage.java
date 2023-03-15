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
     * @param filePath the file path of the saved tasks
     */
    public Storage(String filePath) {

        this.directory = System.getProperty("user.dir");
        this.filePath = Paths.get(directory, filePath);
        this.saveFile = this.filePath.toFile();
        this.saveDir = this.saveFile.getParentFile();

        if (!this.saveDir.exists()) {
            this.saveDir.mkdir();
        }
        if (!this.saveFile.exists()) {
            try {
                this.saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads saved file at save location, returns a task list class containing the saved tasks.
     * If the file does not exist, the method returns an empty task list
     *
     * @return task list containing saved tasks or empty task list
     */
    public TaskList readSave() {
        Scanner savedFileScanner;
        try {
            savedFileScanner = new Scanner(this.saveFile);
        } catch (Exception e) {
            e.printStackTrace();
            return new TaskList();
        }
        return this.populateTaskList(savedFileScanner);
    }

    private TaskList populateTaskList(Scanner savedFileScanner) {
        String taskString;
        Task currTask;
        TaskList taskList = new TaskList();

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
