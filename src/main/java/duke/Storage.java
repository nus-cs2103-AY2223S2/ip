package duke;

import duke.task.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Storage {
    private final File dataFile;

    public Storage(String filepath) {
        this.dataFile = new File(filepath);
    }

    /**
     * Loads tasks from data file into Duke
     * @return list of Tasks
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            if (dataFile.createNewFile()) {
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            String line = br.readLine();
            while (line != null) {
                tasks.add(Parser.parseSavedTask(line));
                line = br.readLine();
            }
            return tasks;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    /**
     * Saves tasks to data file
     * @param tasks List of tasks to be saved
     */
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
