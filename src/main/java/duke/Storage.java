package duke;

import duke.task.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private File file;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Storage(String path) throws IOException {
        this.file = new File(path);
        this.writer = new BufferedWriter(new FileWriter(path, true));
        this.reader = new BufferedReader(new FileReader(path));
    }

    public void initializeStorage() throws IOException {
        this.file.createNewFile();
    }

    public ArrayList<Task> getTasks() throws IOException{
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
        this.writer.write(task.toTaskStorageString());
        this.writer.newLine();
        this.writer.flush();
    }

    public void restructure(TaskList taskList) throws IOException {
        new FileWriter(this.file.getPath(), false).close();
        int size = taskList.getSize();

        for (int i = 1; i <= size; i++) {
            this.storeTask(taskList.getTask(i));
        }
    }

}
