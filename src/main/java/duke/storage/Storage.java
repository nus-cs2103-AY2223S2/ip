package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String directory;
    private String path;
    public Storage(String d, String p) {
        this.directory = d;
        this.path = p;
    }

    public void saveTasks(TaskList taskList) throws IOException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dataFile = new File(path);
        dataFile.createNewFile();

        FileWriter myWriter = new FileWriter(path);
        boolean isFirst = true;
        for (Task t : taskList.getTasks()) {
            if (!isFirst) {
                myWriter.write("\n");
            }
            myWriter.write(t.toSaveString());
            isFirst = false;
        }
        myWriter.close();
    }

    public void loadTasks(TaskList taskList) throws IOException, DukeException {
        Scanner fileReader = new Scanner(new File(path));
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] taskData = data.split("\\$\\$\\$");

            switch (taskData[0]) {
                case "T":
                    taskList.getTasks().add(new Todo(taskData[1]));
                    break;
                case "D":
                    taskList.getTasks().add(new Deadline(taskData[1], taskData[3]));
                    break;
                case "E":
                    taskList.getTasks().add(new Event(taskData[1], taskData[3], taskData[4]));
                    break;
                default:
                    throw new DukeException("Error loading tasks from file!");
            }

            if (taskData[2].equals("T")) {
                taskList.getTasks().get(taskList.getTasks().size() - 1).mark();
            }
        }

        fileReader.close();
    }
}