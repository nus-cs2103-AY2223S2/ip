package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import exception.DukeException;



/**
 * Represents Storage object to provide services of saving and loading data from local.
 */
public class Storage {
    private static String filePath;
    private static String directoryPath;
    private TaskList tasks;

    /**
     * Creates a new Storage object with file path and directory path where the data
     * file is stored.
     *
     * @param filePath
     * @param directoryPath
     */
    public Storage(String filePath, String directoryPath) {
        Storage.filePath = filePath;
        Storage.directoryPath = directoryPath;
    }

    /**
     * Reads stored data in the directoryPath and returns TaskList object that contains
     * all the saved tasks. It creates a new directory and new file if the directory does
     * not exist.
     *
     * @return TaskList object that contains all the saved tasks.
     * @throws IOException if new directory or new file cannot be created.
     * @throws DukeException if there is error parsing users' inputs.
     */
    public TaskList loadTasks() throws IOException, DukeException {
        File directory = new File(directoryPath);
        final File file = new File(filePath);

        if (!directory.exists()) {
            directory.mkdir();
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        }
        tasks = new TaskList();
        Scanner sc = new Scanner(file);
        String taskString;
        String[] s;
        while (sc.hasNext()) {
            taskString = sc.nextLine();
            s = taskString.split(" \\| ");

            switch (s[0].strip()) {
            case "T":
                Todo todo = new Todo(s[2]);
                if (s[1].equals("1")) {
                    todo.markDone();
                }
                tasks.addTask(todo, true);
                break;
            case "D":
                Deadline deadline = new Deadline(s[2], s[3]);
                if (s[1].equals("1")) {
                    deadline.markDone();
                }
                tasks.addTask(deadline, true);
                break;
            case "E":
                Event event = new Event(s[2], s[3], s[4]);
                tasks.addTask(event, true);
                break;
            default:
                break;
            }
        }
        return tasks;
    }

    /**
     * Saves current TaskList object and write data to the storage file.
     *
     * @throws IOException if new directory or new file cannot be created.
     */
    public void saveTasks() throws IOException {
        String path = "./data/duke.txt";
        String directoryPath = "./data";
        File directory = new File(directoryPath);
        final File file = new File(path);

        if (!directory.exists()) {
            directory.mkdir();
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        }

        final FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < tasks.getSize(); i++) {
            bw.append(tasks.getTask(i).getData());
        }
        bw.close();
    }
}
