package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Encapsulates a storage unit which handles loading tasks from the
 * file and saving tasks in the file.
 *
 * @author Sean Chin Jun Kai
 */
public class Storage {
    private static final String DIR_PATH = System.getProperty("user.home") + "/ip/data";
    private String filePath;
    public Storage(String path) {
        this.filePath = path;
    }

    /**
     * Loads all saved tasks in txt file.
     *
     * @return List of saved tasks.
     * @throws DukeException if there is an error with reading or creating the txt file.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        try {
            File dir = new File(DIR_PATH);
            dir.mkdirs();
            File taskFile = new File(DIR_PATH + filePath);
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            Scanner fileScanner = new Scanner(taskFile);
            while (fileScanner.hasNext()) {
                String input = fileScanner.nextLine();
                Task task = readTaskString(input);
                tasks.addTask(task);
            }
        } catch (IOException | DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves all tasks into txt file.
     *
     * @param tasks List of current tasks.
     * @throws DukeException if there is an error writing to the txt file.
     */
    public void saveToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(DIR_PATH + filePath);
            fw.write(tasks.saveString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }


    /**
     * Formats tasks in txt file into corresponding Task objects.
     *
     * @param input task in txt file.
     * @return Task object.
     * @throws DukeException if there is an error reading the file.
     */
    public static Task readTaskString(String input) throws DukeException {
        String[] parsed = input.split(" \\| ");
        Task task;
        int len = parsed.length;
        switch (len) {
        case 3: // this is a task
            Todo todo = new Todo(parsed[2]);
            if (parsed[1].equals("1")) {
                todo.mark();
            }
            task = todo;
            break;
        case 4: // this is a deadline
            LocalDate date = LocalDate.parse(parsed[3]);
            Deadline deadline = new Deadline(parsed[2], date);
            if (parsed[1].equals("1")) {
                deadline.mark();
            }
            task = deadline;
            break;
        case 5: // this is an event
            Event event = new Event(parsed[2], parsed[3], parsed[4]);
            if (parsed[1].equals("1")) {
                event.mark();
            }
            task = event;
            break;
        default:
            throw new DukeException("Unable to read file contents!");
        }
        return task;
    }
}
