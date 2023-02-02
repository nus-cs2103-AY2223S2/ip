package main.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws DukeException {
        TaskList taskList = new TaskList();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                taskList.add(Task.parseTaskFromDB(line));
            }
        } catch (FileNotFoundException ex) {
            throw new DukeException(ERROR.CORRUPTED_TASK_DATA.getMessage());
        } catch (IOException ex) {
            throw new DukeException(ERROR.CORRUPTED_TASK_DATA.getMessage());
        }

        return taskList;
    }

    public boolean save(TaskList list) {
        try {
            // Create data dir if it doesn't exist
            Files.createDirectories(Paths.get("data/"));

            PrintWriter pw = new PrintWriter(this.filePath);
            for (Task task: list) {
                pw.println(task.toString());
            }
            pw.close();
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
