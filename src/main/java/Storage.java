import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final Path path;

    Storage() {
        String currPath = System.getProperty("user.dir");
        this.path = Paths.get(currPath, "src", "data", "duke.txt");
    }

    public ArrayList<Task> readFile() {
        ArrayList<Task> data = new ArrayList<>();
        try {
            File file = new File(String.valueOf(this.path));
            Scanner sc = new Scanner(file);
            // scan and read each line on the duke.txt file
            // need to be able to have a common function that creates the different types of tasks
            while (sc.hasNextLine()) {
                String[] taskArr = sc.nextLine().split("\\|");
                String taskType = taskArr[0];
                boolean isDone = Boolean.parseBoolean(taskArr[1]);
                switch (taskType) {
                case "T":
                    data.add(new Todo(taskArr[2], isDone, taskType));
                    break;
                case "D":
                    String deadline = taskArr[3];
                    data.add(new Deadline(taskArr[2], isDone, taskType, deadline));
                    break;
                case "E":
                    String from = taskArr[3];
                    String to = taskArr[4];
                    data.add(new Event(taskArr[2], isDone, taskType, from, to));
                    break;
                }
            }
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        }
        return data;
    }

    public void writeFile(ArrayList<Task> taskList) {
        StringBuilder taskString = new StringBuilder();
        for (Task task : taskList) {
            String formattedTask = task.formatTask();
            taskString.append(formattedTask);
            taskString.append("\n");
        }
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(this.path));
            fileWriter.write(taskString.toString());
            fileWriter.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
