package duke.storage;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.*;

/**
 * Represents the storage of Duke, allowing the application to load and save tasks to a text file.
 */
public class Storage {
    protected File file;

    /**
     * Finds or creates a storage text file to save and load tasks.
     * @param fileInput The String file path to find the storage text file.
     * @throws DukeException If creating the directory or file for storage is unsuccessful.
     */
    public Storage(String fileInput) throws DukeException {
        try {
            this.file = new File(fileInput);
            if (!file.exists()) {
                Files.createDirectories(Paths.get(file.getParent()));
                this.file = Files.createFile(Path.of(fileInput)).toFile();
            }
        } catch (IOException err) {
            throw new DukeException("Error, the directory of tasks cannot be found or created. "
                                    + "Any new tasks you enter will not be saved.");
        }
    }

    /**
     * Loads the tasks written in the storage file (if any) to an ArrayList.
     * @return An ArrayList of tasks that was written into the storage text file.
     * @throws DukeException If there is an error with opening a scanner to read the storage file.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner scFile = new Scanner(this.file);
            while (scFile.hasNextLine()) {
                String input = scFile.nextLine();
                String[] cur = input.split(",", 3);
                if (cur[0].equals("T")) {
                    tasks.add(new Todo(cur[2], Boolean.parseBoolean(cur[1])));
                } else if (cur[0].equals("D")) {
                    tasks.add(new Deadline(cur[2], Boolean.parseBoolean(cur[1])));
                } else {
                    tasks.add(new Event(cur[2], Boolean.parseBoolean(cur[1])));
                }
            }
            return tasks;
        } catch (FileNotFoundException err) {
            throw new DukeException("There has been an error loading your saved tasks. "
                                    + "Any new tasks you enter will not be saved.");
        }
    }

    /**
     * Saves the ArrayList of tasks into the storage file.
     * @param taskList The taskList to save the tasks from.
     * @throws DukeException If there is an error with writing the tasks into the storage text file.
     */
    public void saveTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task cur : taskList.getTasks()) {
                String curText = cur.saveTask();
                fileWriter.write(curText);
                fileWriter.write('\n');
            }
            fileWriter.close();
        } catch (IOException err) {
            throw new DukeException("Error, your tasks cannot be saved.");
        }
    }

    public Boolean isFileLoaded() {
        return this.file != null;
    }
}
