package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private Path homeDir;
    private Path dataFile;

    public Storage(String homeDir) {
        Path dir = Paths.get(homeDir, "data");
        if (!Files.exists(dir)) {
            System.out.println("Default data directory not found! Creating new directory...");
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.homeDir = dir;
    }

    public ArrayList<Task> load() throws DukeException {

        Path dataFile = Paths.get(String.valueOf(this.homeDir), "data.txt");
        if (!Files.exists(dataFile)) {
            System.out.println("Data file not found! Creating new data file...");
            try {
                Files.createFile(dataFile);
            } catch (IOException e) {
                throw new DukeException("Error occurred loading file!");
            }
        }
        this.dataFile = dataFile;

        List<String> readFile;
        try {
            readFile = Files.readAllLines(this.dataFile);
        } catch (IOException e) {
            throw new DukeException("Failed to parse data file! Maybe it is corrupted?");
        }
        ArrayList<Task> loadedTasks = new ArrayList<>();
        for (String line : readFile) {
            String[] fileData = line.split("\\|");
            switch (fileData[0]) {
            case "T":
                Todo todo = new Todo(fileData[2], fileData[1]);
                loadedTasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(fileData[2], fileData[3], fileData[1]);
                loadedTasks.add(deadline);
                break;
            case "E":
                Event event = new Event(fileData[2], fileData[3], fileData[4], fileData[1]);
                loadedTasks.add(event);
                break;
            default:
                // Write default case if any
            }
        }
        return loadedTasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter saveWriter = new FileWriter(dataFile.toFile(), false);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                saveWriter.write(task.toData() + System.lineSeparator());
            }
            saveWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving data!");
            e.printStackTrace();
        }
    }

}
