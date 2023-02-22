package cluck.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
    public Storage(String filePath) {
        saveFile = new File(filePath);
    }

    /**
     * Reads the saved tasks from the file and returns a task list populated with the saved tasked.
     *
     * @return the task list containing instances of Task class
     */
    public TaskList readSave() {
        if (!saveFile.exists()) {
            return new TaskList();
        }
        try {
            saveFile.getParentFile().mkdirs();
            TaskList taskList = new TaskList();
            Task currTask;
            Scanner savedFileScanner = new Scanner(saveFile);
            while (savedFileScanner.hasNextLine()) {
                currTask = Task.buildTaskFromSave(savedFileScanner.nextLine());
                taskList.addTask(currTask);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the current list of tasks into a txt file.
     * This will overwrite previous saves.
     * There should be no missing directory error since readSave()
     * will create the save directory if it does not exist.
     *
     * @param taskList list of task to be saved
     */
    public void writeSave(TaskList taskList) {
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
