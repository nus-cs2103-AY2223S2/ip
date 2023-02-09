package duke.util;

import duke.exception.DukeException;
import duke.exception.ERROR;
import duke.task.Task;
import duke.task.TaskList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    private static final String FILEPATH_DIRECTORY = "data/";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws DukeException {
        TaskList taskList = new TaskList();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line = br.readLine();
            while (line != null) {
                taskList.add(Task.parseTaskFromDB(line));
                line = br.readLine();
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
            Files.createDirectories(Paths.get(FILEPATH_DIRECTORY));

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
