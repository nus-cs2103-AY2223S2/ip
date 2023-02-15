package duke.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;

/**
 * TaskStorage is a class that handles the saving/loading of tasks to storage.
 */
public class TaskStorage {
    private static final String SAVE_DIR = "data";
    private static final String SAVE_NAME = "tasks.txt";

    /**
     * Saves tasks to storage.
     *
     * @param tasks ArrayList of tasks to save to storage.
     * @throws TaskSaveException
     */
    public void saveTasks(ArrayList<Task> tasks) throws TaskSaveException {
        try {
            Path savePath = Paths.get(SAVE_DIR, SAVE_NAME);
            Files.createDirectories(Paths.get(SAVE_DIR));
            if (!Files.exists(savePath)) {
                Files.createFile(savePath);
            }
            FileWriter fw = new FileWriter(savePath.toFile(), false);
            fw.write(serializeTasks(tasks));
            fw.close();
        } catch (IOException e) {
            throw new TaskSaveException("Tasks not saved!");
        }
    }

    private String serializeTasks(ArrayList<Task> tasks) {
        StringBuilder serialized = new StringBuilder();
        for (Task task : tasks) {
            serialized.append(task.serialize()).append("\n");
        }
        return serialized.toString();
    }

    private Task parseTaskString(String taskStr) throws DukeException {
        String taskType = taskStr.split("|")[0];
        switch (taskType) {
        case "T":
            return Task.deserialize(taskStr);
        case "D":
            return Deadline.deserialize(taskStr);
        case "E":
            return Event.deserialize(taskStr);
        default:
            return null;
        }
    }

    /**
     * Loads tasks from storage.
     *
     * @return ArrayList of tasks loaded from storage.
     * @throws TaskSaveException
     */
    public ArrayList<Task> loadTasks() throws TaskSaveException {
        Path savePath = Paths.get(SAVE_DIR, SAVE_NAME);
        ArrayList<Task> tasks = new ArrayList<Task>();

        if (!Files.exists(savePath)) {
            return tasks;
        }

        try {
            File saveF = savePath.toFile();
            Scanner s = new Scanner(saveF);
            while (s.hasNext()) {
                tasks.add(parseTaskString(s.nextLine()));
            }
            s.close();
            return tasks;
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
            throw new TaskSaveException("Unable to load tasks!");
        } catch (DukeException e) {
            throw new TaskSaveException("Unable to load tasks! Tasks save file may be corrupted!");
        }
    }
}
