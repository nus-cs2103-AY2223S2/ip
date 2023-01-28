package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class contains variables and methods related to handling a data file for Duke.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Creates an instance of Storage.
     * @param filePath Path of the file containing data for Duke or destination file to
     *                 contain data for Duke.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Creates a file if the file does not exist.
     * @param filePath Path of the file containing data for Duke or destination file to contain
     *                 data for Duke.
     * @throws DukeException If file does not exist.
     */
    public void createFile(String filePath) throws DukeException {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            if (file.exists()) {
                throw new DukeException("file already exists");
            } else {
                file.getAbsoluteFile().getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("Successfully created new file.");
            }
        } catch (IOException e) {
            throw new DukeException("creating new file");
        }
    }

    /**
     * Stores data in given list of tasks into the file path of Storage.
     * @param lst contains list of tasks from an instance of Duke.
     */
    public void saveToFile(TaskList lst) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (int i = 0; i < lst.getSize(); i++) {
                String taskText = lst.getTask(i).toFile();
                writer.write(taskText);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(String.format("I wasn't able to write the data into %s", filePath));
        }
    }

    /**
     * Loads file data into a TaskList.
     * @param lst TaskList file data is to be laoded into.
     * @throws DukeException If file does not exist.
     */
    public void loadFileInto(TaskList lst) throws DukeException {
        try {
            Scanner fileData = new Scanner(this.file);
            while(fileData.hasNextLine()) {
                String taskString = fileData.nextLine();
                lst.addTaskFromString(taskString);
            }
            fileData.close();
        } catch (FileNotFoundException e) {
            this.createFile(this.filePath);
            this.loadFileInto(lst);
        }
    }
}
