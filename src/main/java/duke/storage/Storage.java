package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeFileCreationException;
import duke.exceptions.DukeReadException;
import duke.exceptions.DukeUnknownCommandException;
import duke.exceptions.DukeWriteException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

/**
 * Represents a Storage.
 * Responsible for the reading and writing to save file for Duke.
 * @author pzhengze
 */
public class Storage {
    /** Relative path of the save file */
    private final String path;

    /** The save file in File object form */
    private final File saveFile;

    /**
     * Constructor for Storage object.
     * @param path The relative path of the save file.
     */
    public Storage(String path) {
        this.path = path;
        saveFile = new File(path);
    }

    /**
     * Deletes old save file and saves input task list into new save file.
     * @param tasks The list of tasks to be saved.
     * @throws DukeWriteException if the writer fails to write to save file.
     * @throws DukeFileCreationException if a new save file fails to be created.
     */
    public void save(ArrayList<Task> tasks) throws DukeWriteException, DukeFileCreationException {
        // Deletes old save and creates new one.
        saveFile.delete();
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new DukeFileCreationException();
        }

        // Convert all task into String form and writes it into save file.
        StringBuilder save = new StringBuilder();
        for (Task task : tasks) {
            save.append(task.save());
        }
        this.writeToFile(save.toString());
    }

    /**
     * Appends given string into a new line at bottom of save file.
     * @param s The string to be appended.
     * @throws DukeWriteException if the writer fails to write into the save file.
     */
    private void writeToFile(String s) throws DukeWriteException {
        try {
            assert s != null;
            FileWriter fw = new FileWriter(path, true);
            fw.write(s);
            fw.close();
        } catch (IOException ioException) {
            throw new DukeWriteException();
        }
    }

    /**
     * Reads save file and creates a list of Tasks based on it.
     * @return The list of Tasks.
     * @throws DukeFileCreationException if it fails to create a save file.
     * @throws DukeReadException if it fails to read the save file.
     * @throws DukeUnknownCommandException if it fails to understand the save file.
     */
    public ArrayList<Task> loadSaveFile() throws DukeFileCreationException,
            DukeReadException, DukeUnknownCommandException {
        Scanner scanner;

        ArrayList<Task> tasks = new ArrayList<>();

        if (!checkSaveFileExist()) {
            return tasks;
        }

        try {
            scanner = new Scanner(saveFile);
        } catch (FileNotFoundException ignored) {
            throw new DukeReadException();
        }

        // Reads save file line by line and creates new Tasks based on it.
        createTasks(scanner, tasks);

        return tasks;
    }

    /**
     * Checks if the save file's parent directory and the save file exists. Creates them if they do not.
     * @return false if they do not exist. true if they do.
     * @throws DukeFileCreationException if error occurred while creating save file or its parent directory.
     */
    private boolean checkSaveFileExist() throws DukeFileCreationException {
        try {
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            if (!saveFile.exists()) {
                saveFile.createNewFile();
                return false;
            }
            return true;
        } catch (IOException e) {
            throw new DukeFileCreationException();
        }
    }

    /**
     * Scanners the save file line by line and creates tasks based on the commands in the save file.
     * @param scanner The scanner reading the save file.
     * @param tasks The ArrayList to hold the created tasks.
     * @throws DukeUnknownCommandException if the command in the save file cannot be recognised.
     */
    private void createTasks(Scanner scanner, ArrayList<Task> tasks) throws DukeUnknownCommandException {
        while (scanner.hasNext()) {
            String fn = scanner.next();
            assert fn != null;
            String[] details = scanner.nextLine().strip().split("-");

            switch (fn) {
            case "todo":
                tasks.add(new ToDos(details[0], Boolean.parseBoolean(details[1])));
                break;
            case "deadline":
                tasks.add(Deadlines.createDeadlines(details[0], Boolean.parseBoolean(details[1]), details[2]));
                break;
            case "event":
                tasks.add(Events.createEvents(details[0], Boolean.parseBoolean(details[1]), details[2], details[3]));
                break;
            default:
                throw new DukeUnknownCommandException();
            }
        }
    }
}
