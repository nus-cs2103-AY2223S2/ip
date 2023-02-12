package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;

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
     * @param taskList contains list of tasks from an instance of Duke.
     */
    public void saveToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            assert writer != null : "Error saving to file";
            for (int i = 0; i < taskList.getSize(); i++) {
                String taskText = taskList.getTask(i).toFile();
                writer.write(taskText);
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("saving changes to file");
        }
    }

    /**
     * Loads file data into a TaskList.
     * @param taskList TaskList file data is to be laoded into.
     * @throws DukeException If file does not exist.
     */
    public void loadFileInto(TaskList taskList) throws DukeException {
        try {
            Scanner fileData = new Scanner(this.file);
            while (fileData.hasNextLine()) {
                String taskString = fileData.nextLine();
                assert !taskString.isEmpty() : "File contains invalid inputs";
                taskList.addTaskFromString(taskString);
            }
            fileData.close();
        } catch (FileNotFoundException e) {
            this.createFile(this.filePath);
            this.loadFileInto(taskList);
        }
    }
}
