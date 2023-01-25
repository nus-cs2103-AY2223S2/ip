package duke.storage;

import duke.tasklist.TaskList;
import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import duke.ui.Ui;

public class Storage {

    private static final String DEFAULT_DIRECTORY = "./data/";
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";
    private File dukeFile;
    private TaskList tasks;
    private Ui ui;

    public Storage(Ui ui) {
        this.ui = ui;
        File directory = new File(DEFAULT_DIRECTORY);
        boolean directoryCreated = directory.mkdir();
        if (directoryCreated) {
            ui.directoryCreate();
        }

        dukeFile = new File(DEFAULT_FILEPATH);
        try {
            boolean fileCreated = dukeFile.createNewFile();
            if (fileCreated) {
                ui.fileCreate();
            }

        } catch (IOException e) {
            ui.showError(e);
        }

        tasks = new TaskList(this.ui);
    }

    public TaskList load() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(DEFAULT_FILEPATH));
            boolean emptyFile = true;

            for (String line : allLines) {
                emptyFile = false;
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
                }

                String done = taskSplit[1];
                if (done.equals("1")) {
                    assert task != null;
                    task.markDone();
                }

                tasks.loadTask(task);
            }

            if (!emptyFile) {
                ui.printTasks(tasks);
            }

        } catch (IOException e) {
            ui.showError(e);
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        this.tasks = tasks;

        try {
            PrintWriter writer = new PrintWriter(dukeFile);

            ui.uploading();

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

            ui.saved();

        } catch (IOException e) {
            ui.showError(e);
        }

    }

}
