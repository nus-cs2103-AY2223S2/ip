package cluck.storage;

import cluck.taskList.TaskList;
import cluck.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File saveFile;

    public Storage(String filePath) {
        saveFile = new File(filePath);
    }

    public TaskList readSave() {
        if (saveFile.mkdirs()) {
            return new TaskList();
        }
        try {
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
            writer.write(taskList.toSave());
            writer.close();
        } catch (IOException e) {
            System.out.println("Buh oh! An error occurred!!");
            e.printStackTrace();
        }
    }
}
