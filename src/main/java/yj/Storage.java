package yj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private File tasksFile;

    public Storage(String filePath) {
        // Create tasks file if it doesn't exist
        tasksFile = new File(filePath);
        if (!tasksFile.exists()) {
            try {
                tasksFile.createNewFile();
            } catch (Exception e) {
                System.out.println("Crapadoodle! I couldn't create a new file. Something went wrong.");
            }
        }
    }

    /**
     * Saves tasks to file.
     * @param tasks List of tasks
     */
    public void save(List<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(tasksFile);
            for (Task task : tasks) {
                fileWriter.write(task.toSaveString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Crapadoodle! I couldn't write to the file. Something went wrong.");
        }
    }

    /**
     * Loads tasks from file.
     * @return List of tasks
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        // Read tasks from file
        try {
            Scanner scanner = new Scanner(tasksFile);
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
