package elise.internal;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import elise.EliseException;
import elise.tasks.Task;

/**
 * Storage class to manage file saved in hard disk.
 */
public class Storage {
    private final Path filePath;
    private List<Task> taskState;
    private static final String PROJECT_PATH = System.getProperty("user.dir") + "/elise";

    /**
     * Constructor for Storage.
     * Creates missing directories and file.
     *
     * @param filePath Path of the file.
     */
    public Storage(String filePath) {
        Path file = Paths.get(PROJECT_PATH + filePath);
        System.out.println(file);
        try {
            Files.createDirectories(Paths.get(PROJECT_PATH));
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            // all good.
        } catch (IOException e) {
            System.out.println("Critical error");
        }
        this.filePath = file;
    }

    /**
     * Loads up the tasks saved in file and returns the list.
     *
     * @return List of initial tasks saved by data file.
     * @throws EliseException Possibly corrupted data file.
     */
    public List<Task> load() throws EliseException {
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

    /**
     * Writes the changes of the tasks into the file.
     *
     * @throws IOException Unexpected IOException.
     */
    public void write() throws IOException {
        FileWriter writer = new FileWriter(this.filePath.toString());
        for (Task t : taskState) {
            String s = t.fileMessage();
            writer.write(s);
        }
        writer.close();
    }
}
