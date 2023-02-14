package bob;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Storage {
    private Path filePath;

    /**
     * Returns a Storage object which can read and write tasks to a .txt file
     * @param filePath String file path of the input/output file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    private Task decodeTask(String s) throws IOException {
        Task t;
        // Separator is " | ", \\ for regex since | is a special character
        String[] input = s.split(" \\| ");
        String type = input[0];
        boolean isDone = Boolean.parseBoolean(input[1]);
        String desc = input[2];

        switch (type) {
        case "T":
            t = new Todo(desc);
            break;
        case "D":
            LocalDate deadline = LocalDate.parse(input[3]);
            t = new Deadline(desc, deadline);
            break;
        case "E":
            LocalDate start = LocalDate.parse(input[3]);
            LocalDate end = LocalDate.parse(input[4]);
            t = new Event(desc, start, end);
            break;
        default:
            throw new IOException("Invalid file input encountered!");
        }
        t.isDone = isDone;
        return t;
    }

    private String encodeTask(Task t) {
        assert t != null;
        String common = String.format("%s | %s | %s", t.type, t.isDone, t.description);
        StringBuilder encode = new StringBuilder(common);
        switch (t.type) {
        case "D":
            Deadline d = (Deadline) t;
            encode.append(String.format(" | %s", d.deadline));
            break;
        case "E":
            Event e = (Event) t;
            encode.append(String.format(" | %s | %s", e.start, e.end));
            break;
        default:
            assert false;
        }
        return encode.toString();
    }

    /**
     * Reads the existing file (if any) and loads the decoded Task objects into the given TaskList object.
     * @param taskList TaskList object that stores the tasks
     * @throws BobException If there was an error in reading the file
     */
    public void load(TaskList taskList) throws BobException {
        if (Files.exists(this.filePath)) {
            try {
                BufferedReader reader = Files.newBufferedReader(this.filePath);
                String line = reader.readLine();
                while (line != null) { // While not EOF
                    Task t = decodeTask(line);
                    taskList.add(t);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                throw new BobException("Error while loading tasks :(");
            }
        }
    }

    /**
     * Read tasks from a taskList object and writes it to the given file.
     * @param taskList TaskList object that has the tasks
     * @throws BobException If there was an error in writing to the file
     */
    public void save(TaskList taskList) throws BobException {
        try {
            // Make all necessary directories
            Files.createDirectories(this.filePath.getParent());
            BufferedWriter writer = Files.newBufferedWriter(this.filePath);

            for (Task t : taskList.getList()) {
                String outputLine = encodeTask(t);
                writer.write(outputLine); // Write to buffer
                writer.newLine();
            }
            writer.flush(); // Write to file
        } catch (Exception e) {
            throw new BobException("Error while saving tasks :(");
        }
    }
}
