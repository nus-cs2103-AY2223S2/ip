package henz.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import henz.tasklist.TaskList;
import henz.tasks.Deadline;
import henz.tasks.Event;
import henz.tasks.Task;
import henz.tasks.Todo;

/**
 * Storage class to interact with the text file store in ./data.
 */
public class Storage {
    private String cwd = System.getProperty("user.dir");
    private java.nio.file.Path dataPath;
    private String dataPathString;

    /**
     * Constructor.
     * @param filePath the path to the text file that stores the serialised tasks
     */
    public Storage(String filePath) {
        this.dataPath = java.nio.file.Paths.get(cwd, filePath.split("/"));
        this.dataPathString = dataPath.toString();
    }

    /**
     * Serialises task into a string that is storable in the text file.
     * @param task the task to be serialise
     * @return the string of the serialised task
     */
    private String serialise(Task task) {
        String data = "";
        String statusIcon = (task.getIsDone() ? "1" : "0");
        String description = task.getDescription();

        if (task instanceof Todo) {
            data = String.join(" | ", "T", statusIcon, description);
        }

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String by = deadline.getBy();
            data = String.join(" | ", "D", statusIcon, description, by);
        }

        if (task instanceof Event) {
            Event event = (Event) task;
            String from = event.getFrom();
            String to = event.getTo();
            data = String.join(" | ", "E", statusIcon, description, from, to);
        }

        return data;
    }

    /**
     * Deserialises the task string to a Task instance.
     * @param data the serialised task
     * @return the task that has been deserialised from the input
     */
    private Task deserialise(String data) {
        String[] params = data.split("\\|");
        String taskType = params[0].strip();
        Boolean isCompleted = params[1].contains("1");
        String description = params[2].strip();
        Task task = null;

        if (taskType.equals("T")) {
            task = new Todo(description);
        }

        if (taskType.equals("D")) {
            String by = params[3].strip();
            task = new Deadline(description, by);

        }

        if (taskType.equals("E")) {
            String from = params[3].strip();
            String to = params[4].strip();
            task = new Event(description, from, to);

        }

        if (isCompleted && task != null) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Returns the tasks from the text file.
     * @return the task list from the text file
     */
    public TaskList load() {
        TaskList tasks = new TaskList();

        try {
            boolean doesDirectoryExists = java.nio.file.Files.exists(dataPath);

            // 1. If the directory does not exist.
            if (!doesDirectoryExists) {
                try {
                    new File(this.dataPathString).createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 2. reads the file from the given data path.
            File dukeFile = new File(this.dataPathString);
            Scanner scanner = new Scanner(dukeFile);

            // 3. Loops through the lines in the file.
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = this.deserialise(data);
                tasks.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException error) {
            // Try already handles the creation of new file when it doesn't exist.
        }

        return tasks;
    }

    /**
     * Saves the tasks into the text file.
     * @param tasks the task list to be saved
     */
    public void save(TaskList tasks) {
        boolean doesDirectoryExists = java.nio.file.Files.exists(dataPath);

        // 1. If the directory does not exist.
        if (!doesDirectoryExists) {
            try {
                new File(this.dataPathString).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 2. Writes task into file.
        try {
            FileWriter writerObj = new FileWriter(this.dataPathString, false);
            // 2a. Serialise task into string to write into the file.
            for (int i = 0; i < tasks.size(); i++) {
                writerObj.write(this.serialise(tasks.get(i)) + "\n");
            }
            writerObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
