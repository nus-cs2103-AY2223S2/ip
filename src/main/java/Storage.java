import java.io.*;
import java.util.ArrayList;

public class Storage {
    private File dataFile;

    public Storage(String filepath) {
        File dataFile = new File(filepath);
        this.dataFile = dataFile;
    }
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            if (!dataFile.createNewFile()) {
                BufferedReader br = new BufferedReader(new FileReader(dataFile));
                String line = br.readLine();
                while (line != null) {
                    tasks.add(Parser.parseSavedTask(line));
                    line = br.readLine();
                }
            }
            return tasks;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public void save(ArrayList<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter(dataFile);
            for (Task task: tasks) {
                writer.println(task.toSavedString());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }
}
