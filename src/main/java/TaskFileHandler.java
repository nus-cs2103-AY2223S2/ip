import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskFileHandler {
    private static final Path dataPath = Paths.get("data");
    private static final Path taskListPath = Paths.get("data", "duke.txt");

    private static ArrayList<Task> parseTaskList() throws IOException {
        List<String> lines = Files.readAllLines(taskListPath);
        ArrayList<Task> taskList = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task newTask;

            switch (taskType) {
                case "T":
                    newTask = new ToDo(description);
                    break;
                case "D":
                    LocalDateTime dueDate = LocalDateTime.parse(parts[3]);
                    newTask = new Deadline(description, dueDate);
                    break;
                case "E":
                    LocalDateTime fromDate = LocalDateTime.parse(parts[3]);
                    LocalDateTime toDate = LocalDateTime.parse(parts[4]);
                    newTask = new Event(description, fromDate, toDate);
                    break;
                default:
                    System.out.println("Unknown Task!");
                    continue;
            }

            taskList.add(newTask);
            if (isDone) {
                newTask.setDone();
            }
        }
        return taskList;
    }
    public static ArrayList<Task> loadTaskList() {
        try {
            if (!Files.exists(dataPath)) {
                System.out.println("Creating data folder . . .");
                Files.createDirectories(dataPath);
            }
            if (!Files.exists(taskListPath)) {
                System.out.println("Creating task list file . . .");
                Files.createFile(taskListPath);
            }
            return parseTaskList();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void storeTaskList(ArrayList<Task> taskList) {
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(taskListPath);
            for (Task task : taskList) {
                String line = task.getTaskState();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }
}
