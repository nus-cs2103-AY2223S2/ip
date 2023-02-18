package duke.storage;

import duke.exception.DukeException;
import duke.exception.DukeInvalidDataFileException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.stream.Collectors;

/**
 * Represents a storage manager that helps in storing and retrieving
 * the list of Tasks from a text file.
 */
public class Storage {

    public final String folderPath;
    public final String fileName;

    public final String filePath;

    public Storage(String filePath) {

        assert filePath != null : "A valid filepath should be provided";

        this.filePath = filePath;

        int splitIndex = filePath.lastIndexOf("/");
        this.folderPath = filePath.substring(0, splitIndex);
        this.fileName = filePath.substring(splitIndex + 1);
    }

    /**
     * Retrieves the list of Tasks from the text file.
     *
     * @return The list of Tasks stored in the text file.
     */
    public ArrayList<Task> readTasksFromFile() {

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File tasksFile = new File(this.filePath);
            Scanner fileScanner = new Scanner(tasksFile);

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                tasks.add(Task.getTaskFromString(data));
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            File tasksFolderObject = new File(this.folderPath);

            if (!tasksFolderObject.exists()) {
                tasksFolderObject.mkdir();
            }

            File tasksFileObject = new File(this.filePath);
            tasksFileObject.createNewFile();

        } catch (DukeException e) {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write("");
            fileWriter.close();

            return new ArrayList<Task>();

        } finally {
            return tasks;
        }

    }

    /**
     * Writes a list of Tasks to the text file.
     *
     * @param tasks The list of Tasks to be written to the text file.
     */
    public void writeTasksToFile(ArrayList<Task> tasks) throws DukeException {
        try {

            List<String> taskStrings = tasks
                    .stream()
                    .map(Task::getFileRepresentation)
                    .collect(Collectors.toList());

            String textToBeWritten = String.join("\n", taskStrings);

            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(textToBeWritten);
            fileWriter.close();

        } catch (IOException e) {
            throw new DukeInvalidDataFileException("An error occurred in writing the data to file :/");

        }
    }

}
