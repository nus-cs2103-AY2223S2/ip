package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a storage to handle saving and loading save file.
 */
public class Storage {
    private String fileDirectory;
    private String fileName;
    private Ui ui;

    private List<Task> taskList = new ArrayList<>();

    /**
     * Constructor for storage.
     *
     * @param fileDirectory Directory of the save file.
     * @param fileName      Name of the save file.
     * @param ui            Ui instance to take care of user interface.
     */
    public Storage(String fileDirectory, String fileName, Ui ui) {
        this.fileDirectory = fileDirectory;
        this.fileName = fileName;
        this.ui = ui;
    }

    /**
     * Adds a task from the save file to the task list.
     */
    private void loadTaskFromFile(String task) {
        // Parser
        String[] command = task.split("\\|");
        String taskType = command[0];
        String description = command[2];

        Task t = new Task("");

        switch (taskType) {
        case "T":
            t = new Todo(description);
            break;
        case "D":
            LocalDate by = LocalDate.parse(command[3].trim());
            t = new Deadline(description, by);
            break;
        case "E":
            LocalDate from = LocalDate.parse(command[3].trim());
            LocalDate to = LocalDate.parse(command[4].trim());
            t = new Event(description, from, to);
            break;
        default:
        }

        boolean isMarked = command[1].equals("1");
        if (isMarked) {
            t.markAsDone();
        }

        this.taskList.add(t);
    }

    /**
     * Creates parent directories and the file if necessary. Then returns file.
     *
     * @param directory Directory of the file
     * @param filename  Name of the file
     * @return The file
     */
    private File fileWithAssurance(String directory, String filename) {
        // Load parent directories
        File dir = new File(directory);

        // Create parent directories if necessary
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Load file
        File file = new File(directory + "/" + filename);

        // Create file if not exist
        try {
            if (file.createNewFile()) {
                ui.showFileCreatedSuccessfully();
            } else {
                ui.showCreatingFile();
            }
        } catch (IOException e) {
            ui.showError("Something went wrong while creating a new save");
        }

        return file;
    }

    /**
     * Loads the save file at startup if it exists.
     * If directory does not exist, create a new directory.
     *
     * @return List of tasks from the save file
     */
    public List<Task> load() {

        File file = fileWithAssurance(this.fileDirectory, this.fileName);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                this.loadTaskFromFile(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            ui.showSaveNotFound();
        } finally {
            return this.taskList;
        }
    }

    /**
     * Saves the task list to hard drive.
     */
    public void save(List<Task> tasks) {
        ui.showSavingFile();
        try {
            FileWriter fw = new FileWriter(this.fileDirectory + "/" + this.fileName);

            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                fw.write(t.toSavedString() + System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            ui.showError("Something went wrong with saving file.");
        }
    }
}
