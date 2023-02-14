package duke.storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.ui.Ui;
import duke.dukeexceptions.DukeExceptions;
import duke.tasklist.TaskList;
import duke.tasks.Task;

/**
 * Class to abstract the interaction between local storage and program task list.
 */
public class Storage {
    private File file;
    private final String path;
    private Ui ui;

    /**
     * Constructor for storage abstraction.
     *
     * @param path the data path where we will load and store the data
     */
    public Storage(String path) {
        this.path = path;
        fileSetup();
    }

    /**
     * Sets up file and directory if not present.
     */
    public void fileSetup() {
        File directory = new File(path);
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }

            this.file = new File(path, "duke.txt");

            if (!this.file.exists()) {
                this.file.createNewFile();
            }


        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Reads locally stored data for program and returns it as a Tasklist.
     *
     * @return Loaded task list.
     */
    public TaskList loadFile() {
        TaskList result = new TaskList();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                result.addTask(Task.decode(nextLine));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DukeExceptions exceptions) {
            ui.showError(exceptions);
        }
        return result;
    }

    /**
     * Stores a task list locally.
     * @param taskList task list to be stored.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);

            fileWriter.write(taskList.saveTaskList());

            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
