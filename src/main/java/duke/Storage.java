package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/** Storage module for Duke. */
public class Storage {
    private File dataFile;
    private boolean hasFile;
    private Ui ui;

    /**
     * Initializes a Storage object for the current session.
     * 
     * @param path Path to the saved data
     * @param ui   Ui object for the current session
     */
    public Storage(String path, Ui ui) {
        Path fullPath = Paths.get(System.getProperty("user.dir"), path);
        this.dataFile = fullPath.toFile();
        this.hasFile = true;
        this.ui = ui;

        try {
            // create the parent directories and file if needed
            this.dataFile.getParentFile().mkdir();
            this.dataFile.createNewFile();
        } catch (IOException e) {
            // do nothing if we can't create the file!
            // either the file already exists, or we do not have permission to create it
            // the second case will be handled in the read and save methods
        }
    }

    /**
     * Reads tasks from the save file and store them in the given TaskList.
     * 
     * @param taskList TaskList to store saved tasks in
     */
    public void readToTaskList(TaskList taskList) {
        try {
            Scanner reader = new Scanner(this.dataFile);
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] args = line.split("\\|");
                Task task = null;
                switch (args[0]) {
                case "T":
                    task = new ToDo(args[2], Integer.parseInt(args[1]) == 1);
                    break;
                case "D":
                    task = new Deadline(args[2], Integer.parseInt(args[1]) == 1, args[3]);
                    break;
                case "E":
                    task = new Event(args[2], Integer.parseInt(args[1]) == 1, args[3], args[4]);
                    break;
                default:
                    throw new InvalidArgumentException(line);
                }
                taskList.addTask(task);
            }
            this.ui.clearMessage();
            reader.close();
        } catch (FileNotFoundException e) {
            this.notifyNoStorage();
        } catch (InvalidDateException | InvalidArgumentException e) {
            this.ui.clearMessage();
            this.ui.addToMessage("Duke ran into an error while reading saved data.", false);
            this.ui.displayMessage();
            taskList.clearTasks();
        }
    }

    /**
     * Saves tasks from a given TaskList to the save file.
     * 
     * @param taskList TaskList containing the tasks to be saved
     */
    public void saveToFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.dataFile);
            String toWrite = taskList.serializeTasks();
            writer.write(toWrite);
            writer.close();
        } catch (IOException | NullPointerException e) {
            this.notifyNoStorage();
        }
    }

    /** Notify the user that storage is not available for the current session. */
    public void notifyNoStorage() {
        if (this.hasFile) {
            this.ui.addToMessage("WARNING: Duke cannot read from/write to a storage file. ");
            this.ui.addToMessage("         All tasks created will only last within this session.");
            this.ui.addToMessage("", false);
            this.ui.displayMessage();
            this.hasFile = false;
        }
    }

}
