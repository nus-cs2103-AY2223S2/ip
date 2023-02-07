package sys;

import exception.InvalidDateFormatException;
import exception.InvalidTaskStringException;

import task.Task;
import task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage area used to contain the task list.
 */
public class Storage {
    private String path;

    /**
     * Constructor for storage.
     * @param path The filepath to store the task list.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the TaskList object from the storage.
     * If storage path is corrupted, return a fresh task list.
     *
     * @return Task list parsed from the file located in the storage path.
     */
    public TaskList load() {
        TaskList tasks = new TaskList();

        try {
            File f = new File(path);
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String content = s.nextLine();
                tasks.addTask(Task.parseTask(content));
            }
        } catch (FileNotFoundException e) {
            System.out.println("tasks.txt not found, generating new task list...");
        } catch (InvalidTaskStringException | InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the given task list to the storage.
     *
     * @param tasks The task list that is to be stored in the storage path.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(path);

            // Remove enumeration.
            String[] lines = tasks.toString().split("\n");
            StringBuilder newContent = new StringBuilder();

            // Only add to storage file if line is not empty.
            if (!lines[0].equals("")) {
                for (String line: lines) {
                    newContent.append(line.substring(3));
                    newContent.append('\n');
                }

                // Remove last new line character ('\n').
                newContent.deleteCharAt(newContent.length() - 1);
            }

            fw.write(newContent.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}