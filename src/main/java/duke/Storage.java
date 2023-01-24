package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Storage(String path) throws IOException {
        file = new File(path);

        file.getParentFile().mkdirs();
        file.createNewFile();

        writer = new BufferedWriter(new FileWriter(path, true));
        reader = new BufferedReader(new FileReader(path));
    }

    public ArrayList<Task> getTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        String taskStorageString = reader.readLine();

        while(taskStorageString != null) {
            String[] data = taskStorageString.split("\\|");
            taskList.add(parseTaskData(data));
            taskStorageString = reader.readLine();
        }

        return taskList;
    }

    private Task parseTaskData(String[] data) {
        switch (data[0]) {
            case "T":
                return new Todo(data[2], Boolean.parseBoolean(data[1]));
            case "D":
                return new Deadline(data[2], Boolean.parseBoolean(data[1]), data[3]);
            default:
                return new Event(data[2], Boolean.parseBoolean(data[1]), data[3], data[4]);
        }
    }

    public void storeTask(Task task) throws IOException {
        writer.write(task.toTaskStorageString());
        writer.newLine();
        writer.flush();
    }

    public void restructure(TaskList taskList) throws IOException {
        new FileWriter(file.getPath(), false).close();
        int size = taskList.getSize();

        for (int i = 1; i <= size; i++) {
            storeTask(taskList.getTask(i));
        }
    }

}
