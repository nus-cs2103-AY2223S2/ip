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
    String path;

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
        TaskList tl = new TaskList();

        try {
            File f = new File(this.path);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String content = s.nextLine();
                tl.addTask(Task.parseTask(content));
            }
        } catch (FileNotFoundException e) {
            System.out.println("tasks.txt not found, generating new task list...");
        } catch (InvalidTaskStringException e) {
            System.out.println(e.getMessage());
        }

        return tl;
    }

    /**
     * Saves the given task list to the storage.
     *
     * @param tl The task list that is to be stored in the storage path.
     */
    public void save(TaskList tl) {
        try {
            FileWriter fw = new FileWriter(this.path);

            // remove enumeration
            String[] lines = tl.toString().split("\n");
            StringBuilder newContent = new StringBuilder();

            if (!lines[0].equals("")) {
                for (String line: lines) {
                    newContent.append(line.substring(3));
                    newContent.append('\n');
                }

                // remove last new line
                newContent.deleteCharAt(newContent.length() - 1);
            }

            // write to file
            fw.write(newContent.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
