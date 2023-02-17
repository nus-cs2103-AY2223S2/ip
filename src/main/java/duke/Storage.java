package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * Storage class that in charge of loading the data from the storage file
 * and writing the data into the storage file.
 *
 * @author Cheam Jia Wei
 */
public class Storage {
    private final String path;

    /**
     * Constructor for Storage class.
     *
     * @param path Relative path to the data file.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the data from the data file as the respective tasks into the task list.
     *
     * @param taskList The TaskList that data will be loaded into.
     * @throws FileNotFoundException Throws exception when data file cannot be found in the system.
     */
    public void loadTasks(TaskList taskList) throws FileNotFoundException {
        File toRead = new File(this.path);
        Scanner sc = new Scanner(toRead);

        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(" \\| ");
            taskList.loader(line);
        }
        sc.close();
    }

    /**
     * Writes the tasks into its data form into the data file.
     *
     * @param taskList The TaskList that data will be written from.
     */
    public void writeTasks(TaskList taskList) {
        try {
            new File(this.path).getParentFile().mkdirs();
            FileWriter writer = new FileWriter(this.path);
            for (int i = 0; i < taskList.size(); i++) {
                Task toSave = taskList.get(i);
                writer.write(toSave.toWrite());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file to write into");
        } catch (IOException e) {
            System.out.println("Unable to save task. Try again");
        }
    }
}
