package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;
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
        try {
            String[] command = task.split("\\|");
            String taskType = command[0].trim();
            String description = command[2].trim();

            Task t;
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
                throw new DukeException(String.format("Invalid type of task: %s", task));
            }

            boolean isMarked = command[1].trim().equals("1");
            t.markStatus(isMarked);
            this.taskList.add(t);

        } catch (DukeException e) {
            ui.print(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ui.print(String.format("Task has wrong format: %s", task));
        }
    }

    /**
     * Creates parent directories and the file if necessary. Then returns file.
     *
     * @param directory Directory of the file
     * @param filename  Name of the file
     * @return The file
     */
    private File getFileWithAssurance(String directory, String filename) {
        /* Loads parent directories */
        File dir = new File(directory);

        /* Creates parent directories if necessary */
        if (!dir.exists()) {
            dir.mkdirs();
        }

        /* Loads file */
        File file = new File(directory + "/" + filename);

        /* Creates file if necessary */
        try {
            if (file.createNewFile()) {
                ui.print("No save file found. Creating new save...");
            } else {
                ui.print("Save file detected.");
            }
        } catch (IOException e) {
            ui.print("Something went wrong while creating a new save.");
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
        File file = getFileWithAssurance(this.fileDirectory, this.fileName);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                this.loadTaskFromFile(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
        }

        return this.taskList;
    }

    /**
     * Saves the task list to hard drive.
     */
    public void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.fileDirectory + "/" + this.fileName);

            for (Task t : tasks) {
                fw.write(t.toSavedString() + System.lineSeparator());
            }

            fw.close();
            ui.printFileSaved();
        } catch (IOException e) {
            ui.printFileSaveError();
        }
    }
}
