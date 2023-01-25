package duke;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.io.FileWriter;

public class Storage {
    private final Path filePath;
    private List<Task> taskState;
    protected Storage(String filePath) {
        Path file = Paths.get(filePath);
        Path parent = file.getParent();
        try {
            Files.createDirectories(parent);
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            // all good.
        } catch (IOException e) {
            System.out.println("Critical error");
        }
        this.filePath = file;
    }

    // Load task file as specified in the filePath
    protected List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(filePath.toFile());
            while (sc.hasNextLine()) {
                String[] details = sc.nextLine().split("[|]{2}");
                tasks.add(TaskList.getInstance(details[0], !details[1].equals("0"),
                        Arrays.copyOfRange(details, 2, details.length)));
            }
        } catch (IOException e) {
            System.err.println("Unexpected error encountered.");
        }
        this.taskState = tasks;
        return tasks;
    }

    protected void write() throws IOException{
        FileWriter writer = new FileWriter(this.filePath.toString());
        for (Task t : taskState) {
            String s = t.fileMessage();
            writer.write(s);
        }
        writer.close();
    }
}
