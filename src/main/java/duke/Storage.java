package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/** Loads and saves tasks from file*/
public class Storage {
    /** User's root directory path*/
    private static final String userDir = System.getProperty("user.dir");
    /** Data directory path*/
    private static final Path dataDirPath = Paths.get(userDir, "data");
    /** duke.txt file path*/
    private static final Path dataFilePath = Paths.get(userDir, "data", "duke.Duke.txt");

    /**
     * Creates data directory if it doesn't exist
     */
    public static void createDataDir() {
        try {
            if (!Files.exists(dataDirPath)) {
                Files.createDirectory(dataDirPath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to create directory");
            e.printStackTrace();
        }
    }

    /**
     * Creates duke.txt if it doesn't exist. Reads data from duke.txt otherwise
     * @return ArrayList of all Tasks in duke.txt
     */
    public static ArrayList<Task> load() {
        //Create duke.Duke.txt inside data dir if it doesn't exist
        if (!Files.exists(dataFilePath)) {
            try {
                Files.createFile(dataFilePath);
            } catch (IOException e) {
                System.out.println("An error occurred while trying to create duke.Duke.txt");
                e.printStackTrace();
            }
        }
        List<String> data = null;
        //if duke.Duke.txt exists, read from it
        try {
            data = Files.readAllLines(dataFilePath);
        } catch (IOException e) {
            System.out.println("An error occurred while trying to read duke.Duke.txt");
            e.printStackTrace();
        }
        ArrayList<Task> tasks = new ArrayList<>();
        assert data != null;
        for (String x: data) {
            String[] inp = x.split("\\|");
            String type = inp[0];
            if (type.equals("T")) {
                Task tsk = new Task(inp[1]);
                tsk.setIsDone(Boolean.parseBoolean(inp[1]));
                tasks.add(tsk);
            } else if (type.equals("TD")) {
                ToDo td = new ToDo(inp[2]);
                td.setIsDone(Boolean.parseBoolean(inp[1]));
                tasks.add(td);
            } else if (type.equals("D")) {
                Deadline d = new Deadline(inp[2], LocalDate.parse(inp[3]));
                d.setIsDone(Boolean.parseBoolean(inp[1]));
                tasks.add(d);
            } else if (type.equals("E")) {
                Event e = new Event(inp[2], LocalDateTime.parse(inp[3]),
                        LocalDateTime.parse(inp[4]));
                e.setIsDone(Boolean.parseBoolean(inp[1]));
                tasks.add(e);
            }
        }
        return tasks;
    }

    /**
     * Writes into duke.txt
     * @param allData is an ArrayList containing all Tasks
     */
    public static void upload(ArrayList<Task> allData) {
        assert !allData.isEmpty();
        ArrayList<String> writing = new ArrayList<>();
        for (Task x : allData) {
            writing.add(x.toFile());
        }
        try {
            Files.write(dataFilePath, writing);
        } catch (IOException e) {
            System.out.println("Error while writing to file!");
        }
    }
}
