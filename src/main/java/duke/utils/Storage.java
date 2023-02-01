package duke.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskTypeException;
import duke.tasks.Task;
import duke.ui.Ui;


/**
 * Storage handles loading data from and saving data to files.
 */
public class Storage {
    private List<Task> taskList;

    /**
     * Creates a new Storage object.
     */
    public Storage() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Edit existing storage given a task list.
     * @param taskList The edited task list.
     */
    public void editStorage(List<Task> taskList) {
        this.taskList = taskList;
    }


    /**
     * Return task list from existing storage.
     * @return The task list stored in storage
     */
    public List<Task> getStorage() {
        return this.taskList;
    }

    /**
     * Load storage with the specified file.
     * @param file The file that stores required data.
     */
    public void loadFromFile(File file) {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(file));
            taskList.clear();
            String line = bufReader.readLine();
            while (line != null) {
                try {
                    taskList.add(Task.dataToTask(line));
                } catch (InvalidTaskTypeException | InvalidDateException e) {
                    System.out.println(Ui.HORIZONTAL_LINE + "\n" + e.getMessage()
                            + "\n" + Ui.HORIZONTAL_LINE);
                }
                line = bufReader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found when trying to read data file.");
        } catch (IOException e) {
            throw new RuntimeException("Error while loading from file: " + e);
        }
    }

    /**
     * Writes the tasks into the Storage file.
     * @param file The file to write tasks in.
     */
    public void saveToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : taskList) {
                writer.write(task.taskToData());
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving file: " + e);
        }

    }




}
