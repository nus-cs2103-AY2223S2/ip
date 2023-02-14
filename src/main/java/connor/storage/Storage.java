package connor.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import connor.task.Deadline;
import connor.task.Event;
import connor.task.Task;
import connor.task.Todo;

/**
 * Storage object to read/write to file(memory).
 */
public class Storage {

    /** dataFile that stores the tasks between sessions. */
    private File dataFile;

    /**
     * Constructor that reads information from a File object.
     *
     * @param dataFile File contains the current list of Tasks.
     */
    public Storage(File dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * Returns a Task after reading one line in the memory.
     *
     * @param str String of one line in the memory.
     * @return Task instance read from memory.
     * @throws CorruptedDataException if command is unreadable.
     */
    public Task interpretLine(String str) throws CorruptedDataException {
        String[] directives = str.split("\\|");
        if (directives[0].equals("T")) {
            return new Todo(directives[2], Boolean.parseBoolean(directives[1]));
        } else if (directives[0].equals("D")) {
            return new Deadline(directives[2], Boolean.parseBoolean(directives[1]), directives[3]);
        } else if (directives[0].equals("E")) {
            return new Event(directives[2], Boolean.parseBoolean(directives[1]), directives[3], directives[4]);
        }
        throw new CorruptedDataException();
    }

    /**
     * Returns a LinkedList that is from the memory if it is valid and not corrupted.
     * Ignores that line if memory is corrupted.
     *
     * @param sc scanner object to read the File object.
     * @return LinkedList read from memory.
     */
    public LinkedList<Task> readFile(Scanner sc) {
        LinkedList<Task> tasks = new LinkedList<>();
        while (sc.hasNextLine()) {
            try {
                tasks.add(interpretLine(sc.nextLine()));
            } catch (CorruptedDataException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Returns a LinkedList that is read from the dataFile.
     *
     * @return LinkedList that is read from dataFile.
     * @throws IOException if File does not exist.
     */
    public LinkedList<Task> initialize() throws IOException {
        this.dataFile.createNewFile();
        Scanner sc = new Scanner(this.dataFile);
        return readFile(sc);
    }

    /**
     * Writes the contents of the LinkedList into the memory.
     *
     * @param tasks the collection of Task to be stored in memory.
     */
    public void updateFile(LinkedList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(String.valueOf(this.dataFile));
            writer.write("");
            for (int i = 0; i < tasks.size(); i++) {
                writer.append(tasks.get(i).dataFormat()).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ALERT! Unable to overwrite data, input is not saved!");
        }
    }
}
