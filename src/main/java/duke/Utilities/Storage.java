package duke.Utilities;

import duke.Exception.NoSuchFileException;
import duke.Task.Task;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class to store and load data from a file.
 */
public class Storage {
    private List<Task> tasks;
    private File file;

    /**
     * The constructor for a storage object, that loads a file from the filepath or creates a new file with the
     * filepath name input.
     * @param filepath The file name.
     */
    public Storage(String filepath) {
        this.tasks = new ArrayList<>();
        this.file = new File(filepath);
    }

    /**
     * Loads an existing task list from the data file.
     * @return the list of tasks stored in the file.
     * @throws NoSuchFileException If there is no space to make the file, throw this exception.
     */
    public List<Task> loadFromFile() throws NoSuchFileException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            tasks.clear();
            String line = reader.readLine();
            while (line != null) {
                tasks.add(Task.dataToTask(line));
                line = reader.readLine();
            }
            return tasks;
        } catch (IOException exception) {
            throw new NoSuchFileException(file.getName());
        }
    }

    /**
     * Saves the existing task list with the latest changes to the file.
     * @param taskList The task list with the latest changes to be saved.
     */
    public void saveToFile(List<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : taskList) {
                writer.write(task.taskToData() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save the file :( " + e);
        }

    }
}
