package yj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

// File processing logic in this file is taken from https://github.com/nus-cs2103-AY2223S2/forum/issues/31#issuecomment-1400728653
public class Storage {

    private File tasksFile;

    private String home = System.getProperty("user.home");
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(home, "data", filePath);

        // Create tasks file if it doesn't exist
        tasksFile = new File(this.filePath.toString());
        if (!tasksFile.exists()) {
            try {
                FileUtils.writeStringToFile(tasksFile, "");
            } catch (Exception e) {
                System.out.println("Crapadoodle! I couldn't create a new file. Something went wrong.");
            }
        }
    }

    /**
     * Saves tasks to file.
     *
     * @param tasks List of tasks
     */
    public void save(List<Task> tasks) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            outputString.append(tasks.get(i).toSaveString());
            outputString.append(System.getProperty("line.separator"));
        }
        try {
            FileUtils.writeStringToFile(tasksFile, outputString.toString());
        } catch (IOException e) {
            System.out.println("Crapadoodle! I couldn't write to the file. Something went wrong.");
        }
    }

    /**
     * Loads tasks from file.
     *
     * @return List of tasks
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        // Read tasks from file
        Scanner scanner;
        try {
            scanner = new Scanner(tasksFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            String[] taskLineParts = taskLine.split("\\|");

            String taskType = taskLineParts[0].trim();
            int isDone = Integer.parseInt(taskLineParts[1].trim());
            String description = taskLineParts[2].trim();

            Task task;
            switch (taskType) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    String by = taskLineParts[3].trim();
                    task = new Deadline(description, by);
                    break;
                case "E":
                    String from = taskLineParts[3].trim();
                    String to = taskLineParts[4].trim();
                    task = new Event(description, from, to);
                    break;
                default:
                    task = new Task("");
            }
            if (isDone == 1) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;

    }
}
