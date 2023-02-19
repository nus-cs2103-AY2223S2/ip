package duke;

import duke.exceptions.DukeException;
import duke.exceptions.FileLoadFailedException;
import duke.exceptions.FileSaveFailedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with utilities to save/load a Duke TaskList to/from a file
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for a Storage object.
     *
     * @param filePath Relative path to saved TaskList file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Sets the filePath to a new filePath.
     *
     * @param filePath Relative path to saved TaskList file
     */
    public void changeFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a TaskList from supplied file.
     *
     * @return ArrayList of Task objects that can be passed into a TaskList constructor.
     * @throws DukeException If the file was corrupted, or could not be read.
     */
    public ArrayList<Task> loadTasklistFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            return tasks;
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new FileLoadFailedException();
        }

        for (String line : lines) {
            List<String> args = List.of(line.split("\\|"));
            String type = args.get(0);
            String isDone = args.get(1);
            String description = args.get(2);

            Task task;
            switch (type) {
                case "todo":
                    task = new Todo(description);
                    break;
                case "deadline":
                    Date by = Parser.parseDate(args.get(3));
                    task = new Deadline(description, by);
                    break;
                case "event":
                    Date from = Parser.parseDate(args.get(3));
                    Date to = Parser.parseDate(args.get(4));
                    task = new Event(description, from, to);
                    break;
                default:
                    throw new FileLoadFailedException();
            }

            if (isDone.equals("true")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Saves a TaskList to the supplied file.
     *
     * @param tasks TaskList to save.
     * @throws DukeException if the tasklist failed to save to the file.
     */
    public void saveTasklistToFile(TaskList tasks) throws DukeException {
        Path path = Paths.get(filePath);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(tasks.get(i).getFileRepresentation()).append("\n");
        }

        try {
            Files.write(path, sb.toString().getBytes());
        } catch (IOException e) {
            throw new FileSaveFailedException();
        }
    }
}
