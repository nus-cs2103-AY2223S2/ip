package duke.storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;
import duke.ui.Ui;

/**
 * Represents a Storage instance of Duke.
 * A storage instance handles all communication between Duke and local data file.
 */
public class Storage {

    private static final String DEFAULT_DIRECTORY = "./data/";
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";
    private File dukeFile;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Storage instance.
     * Storage object is constructed with an Ui instance.
     * Ui object assists Storage in communicating with User.
     *
     * @param ui Ui instance.
     */
    public Storage(Ui ui) {
        this.ui = ui;
        File directory = new File(DEFAULT_DIRECTORY);
        boolean isDirectoryCreated = directory.mkdir();
        if (isDirectoryCreated) {
            ui.directoryCreate();
        }

        dukeFile = new File(DEFAULT_FILEPATH);
        try {
            boolean isFileCreated = dukeFile.createNewFile();
            if (isFileCreated) {
                ui.fileCreate();
            }

        } catch (IOException e) {
            ui.showError(e);
        }
        assert dukeFile.exists() : "XyDuck data file not created.";
        tasks = new TaskList(this.ui);
    }

    /**
     * Returns TaskList instance after loading tasks from data file.
     *
     * @return TaskList object with loaded tasks.
     */
    public TaskList load() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(DEFAULT_FILEPATH));
            boolean isEmptyFile = true;

            for (String line : allLines) {
                isEmptyFile = false;
                String[] taskSplit = line.split(",,");
                String type = taskSplit[0];
                Task task = null;
                switch (type) {
                case "T":
                    task = new ToDo(taskSplit[2]);
                    break;
                case "D":
                    task = new Deadline(taskSplit[2], taskSplit[3]);
                    break;
                case "E":
                    task = new Event(taskSplit[2], taskSplit[3], taskSplit[4]);
                    break;
                default:
                    break;
                }

                String done = taskSplit[1];
                if (done.equals("1")) {
                    assert task != null : "Unrecognised task type.";
                    task.setDone();
                }

                tasks.loadTask(task);
            }

            if (!isEmptyFile) {
                ui.printTasks(tasks);
            }

        } catch (IOException | DukeException e) {
            ui.showError(e);
        }
        return tasks;
    }

    /**
     * Returns Tasks has been successfully saved message.
     * Saves existing tasks in TaskList into data file.
     *
     * @param tasks Updated TaskList with changes to be saved.
     * @return Task has been saved message.
     */
    public String save(TaskList tasks) {
        this.tasks = tasks;

        try {
            PrintWriter writer = new PrintWriter(dukeFile);
            int count = 0;
            for (Task task : tasks.getTasks()) {
                count++;
                String toWrite = task.getSaveFormat();
                if (count == 1) {
                    writer.println(toWrite);
                } else {
                    writer.append(toWrite).append("\n");
                }
            }
            writer.close();
            return ui.saved();

        } catch (IOException e) {
            return ui.showError(e);
        }
    }

}
