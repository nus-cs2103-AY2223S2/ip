package dudu.util;

import dudu.exception.TaskIOException;
import dudu.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
     * @throws TaskIOException If the data cannot be saved
     */
    public void saveTask(ArrayList<Task> list) throws TaskIOException {
        try {
            FileWriter fileWriter = new FileWriter(this.getFile());
            for (Task task : list) {
                fileWriter.write(task.encode() + '\n');
            }
            fileWriter.flush();
        } catch (IOException ex) {
            throw new TaskIOException("Cannot save task");
        }
    }

    /**
     * Update the data file.
     *
     * @param list Updated list of tasks.
     * @throws TaskIOException If the data cannot be saved
     */
    public void updateTask(ArrayList<Task> list) throws TaskIOException {
        saveTask(list);
    }

    /**
     * Reads the tasks form the data file.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> loadTask() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.getFile());
            while (scanner.hasNextLine()) {
                list.add(Task.decode(scanner.nextLine()));
            }
        } catch (TaskIOException | FileNotFoundException ex) {
            System.out.println(ex);
        }
        return list;
    }

    /**
     * Gets the file.
     *
     * @return Returns the file containing task list.
     * @throws TaskIOException If the file cannot be accessed.
     */
    public File getFile() throws TaskIOException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        try {
            parentFile.mkdir();
            file.createNewFile();
        } catch (IOException ex) {
            throw new TaskIOException("cannot create file");
        }
        return file;
    }
}
