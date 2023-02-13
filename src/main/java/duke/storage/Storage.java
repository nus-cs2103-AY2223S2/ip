package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Handles loading and saving of tasks to local storage.
 */
public class Storage {
    private final Path dukeDataDir = Path.of(System.getProperty("user.dir") + "/data");
    private File dataFile;
    private final TaskList tasks;
    private final String fileName;

    /**
     * Create a Storage class that interfaces between a TaskList and a File.
     * @param tasks a TaskList.
     * @param fileName Name of the local file that is used as storage in the user's system.
     */
    public Storage(TaskList tasks, String fileName) {
        this.tasks = tasks;
        this.fileName = fileName;
    }

    /**
     * Loads tasks from a local file into a TaskList, creates one if one does not exist.
     * @throws IOException If fail to load/create a storage file
     */
    public void load() throws IOException {
        try {
            if (!Files.exists(dukeDataDir)) {
                Files.createDirectories(dukeDataDir);
            }

            this.dataFile = new File(this.dukeDataDir + fileName);
            boolean isCreated = true;

            if (!this.dataFile.exists()) {
                isCreated = this.dataFile.createNewFile();
            }

            if (!isCreated) {
                throw new DukeException("Failed to create storage file!");
            }

            Scanner sc = new Scanner(this.dataFile);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                parseLine(input, tasks);
            }

        } catch (FileNotFoundException | DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves current TaskList into local file.
     * @throws IOException If fail to save tasks into file
     */
    public void save() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(this.dataFile);
            for (int i = 0; i < this.tasks.size(); i++) {
                String line = tasks.get(i).toSaveFormat();
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to save existing tasks...");
        }
    }

    /**
     * Parse input from file into tasks.
     * @param input Line from local file.
     * @param tasks TaskList to store tasks.
     * @throws DukeException If fail to create task from invalid input.
     */
    public static void parseLine(String input, TaskList tasks) throws DukeException {
        String[] inputList = input.split(",");
        String taskType = inputList[0];
        String taskName = inputList[1];
        String isDone = inputList[2];

        Task newTask;

        switch (taskType) {
        case "T":
            newTask = new ToDo(taskName);
            break;
        case "D":
            String by = inputList[3];
            LocalDateTime deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            newTask = new Deadline(taskName, deadline);
            break;
        case "E":
            String from = inputList[3];
            String to = inputList[4];
            LocalDateTime startDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            LocalDateTime endDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            newTask = new Event(taskName, startDate, endDate);
            break;
        default:
            throw new DukeException("Unable to parse this line: " + input);
        }
        if (isDone.equals("X")) {
            newTask.markAsDone();
        }
        assert newTask != null : "Cannot add a null task into a TaskList";
        tasks.addTask(newTask);

    }
}
