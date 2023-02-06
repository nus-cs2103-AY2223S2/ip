package dudu.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dudu.exception.DuduException;
import dudu.exception.InvalidCommandException;
import dudu.exception.TaskIoException;
import dudu.task.Task;

/**
 * Storage class to handle local storage of tasks
 */
public class Storage {
    private String filePath;

    /**
     * Constructor to store data into the data file.
     *
     * @param filePath File path to be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save task into the file.
     *
     * @param list List of tasks to be stored.
     * @throws TaskIoException If the data cannot be saved
     */
    public void saveTask(ArrayList<Task> list) throws TaskIoException {
        try {
            FileWriter fileWriter = new FileWriter(this.getFile());
            for (Task task : list) {
                fileWriter.write(task.encode() + '\n');
            }
            fileWriter.close();
        } catch (IOException ex) {
            throw new TaskIoException("Cannot save task");
        }
    }

    /**
     * Update the data file.
     *
     * @param list Updated list of tasks.
     * @throws TaskIoException If the data cannot be saved
     */
    public void updateTask(ArrayList<Task> list) throws TaskIoException {
        saveTask(list);
    }

    /**
     * Reads the tasks form the data file.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> loadTask() throws DuduException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.getFile());
            while (scanner.hasNextLine()) {
                list.add(Task.decode(scanner.nextLine()));
            }
        } catch (TaskIoException | FileNotFoundException | InvalidCommandException ex) {
            throw new DuduException(ex.toString());
        }
        return list;
    }

    /**
     * Gets the file.
     *
     * @return Returns the file containing task list.
     * @throws TaskIoException If the file cannot be accessed.
     */
    public File getFile() throws TaskIoException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        try {
            parentFile.mkdir();
            file.createNewFile();
        } catch (IOException ex) {
            throw new TaskIoException("cannot create file");
        }
        return file;
    }
}
