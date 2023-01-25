import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    private String directory;
    private String path;

    public Storage(String d, String p) {
        this.directory = d;
        this.path = p;
    }

    public void saveTasks() throws IOException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dataFile = new File(path);
        dataFile.createNewFile();

        FileWriter myWriter = new FileWriter(path);
        boolean isFirst = true;
        for (Task t : Task.tasks) {
            if (!isFirst) {
                myWriter.write("\n");
            }
            myWriter.write(t.toSaveString());
            isFirst = false;
        }
        myWriter.close();
    }

    public void loadTasks() throws IOException, DukeException {
        Scanner fileReader = new Scanner(new File(path));
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] taskData = data.split("\\$\\$\\$");

            switch (taskData[0]) {
                case "T":
                    Task.tasks.add(new Todo(taskData[1]));
                    break;
                case "D":
                    Task.tasks.add(new Deadline(taskData[1], taskData[3]));
                    break;
                case "E":
                    Task.tasks.add(new Event(taskData[1], taskData[3], taskData[4]));
                    break;
                default:
                    throw new DukeException("Error loading tasks from file!");
            }

            if (taskData[2].equals("T")) {
                Task.tasks.get(Task.tasks.size() - 1).mark();
            }
        }

        fileReader.close();
    }
}