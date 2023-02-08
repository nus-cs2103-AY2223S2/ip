package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Storage handles the loading and saving of
 * the task list.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class Storage {
    private static final String FOLDER = "./";
    private File taskStorage;

    /**
     * Constructor of Storage.
     *
     * @param fileName Filename of saved task list.
     */
    public Storage(String fileName) {
        this.taskStorage = new File(FOLDER, fileName + ".txt");
    }

    /**
     * Saves the task list onto the hard drive.
     * @param tasks The list of tasks.
     */
    public void saveToDisk(TaskList tasks) {
        if (this.taskStorage.exists()) {
            this.taskStorage.delete();
        }
        try {
            this.taskStorage.createNewFile();
            FileWriter fileWriter = new FileWriter(this.taskStorage);
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i).toStorage() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Load existing txt file to tasklist.
     * @param tasks TaskList of Duke.
     * @param ui ui of Duke.
     * @param storage storage of Duke.
     */
    public void loadToDuke(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        if (this.taskStorage.exists()) {
            Scanner sc = new Scanner(this.taskStorage);
            Parser parser = new Parser();
            while (sc.hasNextLine()) {
                String[] taskInTxt = sc.nextLine().split("#");
                Command c = parser.parseStorage(taskInTxt);
                c.execute(tasks, ui, storage);
                if (taskInTxt[taskInTxt.length-1].equals("[X]")) {
                    tasks.get(tasks.size()-1).markAsDone();
                }
            }
            sc.close();
        }
    }
}
