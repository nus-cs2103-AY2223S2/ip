package duke.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.exceptions.DukeInvalidFileFormatException;
import duke.tasks.Task;

/**
 * The storage manager for Duke.
 */
public class Storage {

    private final File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads the task list from disk.
     *
     * @return A list of tasks.
     * @throws DukeInvalidFileFormatException If the file is incorrectly formatted.
     */
    public ArrayList<Task> loadTaskList() throws DukeInvalidFileFormatException {
        ArrayList<Task> arrayList = new ArrayList<>();
        Scanner sc;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            // if this is a new user, there'll be no `duke.txt` to load from disk
            return arrayList;
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Task task = Parser.parseTask(line);
            arrayList.add(task);
        }

        sc.close();
        return arrayList;
    }

    /**
     * Saves the task list to disk.
     *
     * @param taskList The list of tasks to be saved to disk.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            // overwrite old file
            FileWriter fw = new FileWriter(file, false);
            fw.write(taskList.toDukeFileString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: failed to save task list to disk");
        }
    }
}
