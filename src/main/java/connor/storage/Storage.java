package connor.storage;

import connor.task.Deadline;
import connor.task.Event;
import connor.task.Task;
import connor.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {

    /** dataFile that stores the tasks between sessions. */
    private File dataFile;

    public Storage(File dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * Returns a Task instance depending on command.
     * Used for reading from memory.
     *
     * @param directives
     * @return Task instance depending on command.
     * @throws CorruptedDataException
     */
    public Task parseLine(String[] directives) throws CorruptedDataException {
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
     * Returns a Task after reading the dataformat in the memory.
     *
     * @param str
     * @return Task instance read from memory.
     * @throws CorruptedDataException
     */
    public Task interpretLine(String str) throws CorruptedDataException {
        String[] directives = str.split("\\|");
        return parseLine(directives);
    }

    /**
     * Returns a LinkedList that is from the memory if it is valid and not corrupted.
     * Throws an error and ignore that line if memory is corrupted.
     *
     * @param sc
     * @return LinkedList read from memory.
     */
    public LinkedList<Task> readFile(Scanner sc) {
        LinkedList<Task> tasks = new LinkedList<>();
        while (sc.hasNextLine()) {
            try {
                tasks.add(interpretLine(sc.nextLine()));
            } catch (CorruptedDataException e) {
                System.out.println("        " + e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Returns a LinkedList that is read from the dataFile.
     *
     * @return LinkedList that is read from dataFile.
     * @throws IOException
     */
    public LinkedList<Task> initialize() throws IOException {
        this.dataFile.createNewFile();
        Scanner sc = new Scanner(this.dataFile);
        return readFile(sc);
    }

    /**
     * Writes the contents of the LinkedList into the memory.
     *
     * @param tasks
     */
    public void updateFile(LinkedList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(String.valueOf(this.dataFile));
            writer.write("");
            for (int i = 0; i < tasks.size(); i++) {
                writer.append(tasks.get(i).dataFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("        ALERT! Unable to overwrite data, input is not saved!");
        }
    }
}
