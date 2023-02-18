package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;

/**
 * Creates a new storage.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class Storage {
    private File file;
    private String path;

    /**
     * A public constructor to initialize Storage instance.
     *
     * @param path The path of the file.
     */
    Storage(String path) {
        this.file = new File(path);
        this.path = path;
    }

    /**
     * Opens file containing task data and loads them into Task arraylist.
     *
     * @param tasks List of tasks.
     */
    protected void loadData(TaskList tasks) {
        try {

            File f = new File(path);

            if (!f.exists()) {
                throw new DukeException("File does not exists!\n");
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] descriptions = input.split(" ", 2);
                Parser.parseInput(tasks, descriptions[0], descriptions);
            }

            tasks.finishInitialization();

        } catch (DukeException error) {
            tasks.finishInitialization();
            Ui.errorMsg(error.getMessage());
        } catch (FileNotFoundException error) {
            tasks.finishInitialization();
            Ui.errorMsg(error.getMessage());
        }
    }

    /**
     * Saves task description into file.
     *
     * @param path The path of the file.
     * @param tasks List of task objects.
     */
    protected void saveData(String path, TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(path);

            for (Task task : tasks.getTaskList()) {
                fileWriter.write(task.getDescription());
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException error) {
            Ui.errorMsg("Something went wrong: " + error.getMessage());
        }
    }
}
