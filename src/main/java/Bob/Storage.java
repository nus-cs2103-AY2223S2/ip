package Bob;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Storage {
    private Path filePath;

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
        }
        return encode.toString();
    }

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
            System.out.println(e.getMessage());
            throw new BobException("Error while saving tasks :(");
        } 
    }
}
