package duke;

import duke.Exception.NoSuchFileException;
import duke.Task.Task;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Task> tasks;
    private File file;

    public Storage(String filepath) {
        this.tasks = new ArrayList<>();
        this.file = new File(filepath);
    }

    public List<Task> loadFromFile() throws NoSuchFileException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            tasks.clear();
            String line = reader.readLine();
            while (line != null) {
                tasks.add(Task.dataToTask(line));
                line = reader.readLine();
            }
            return tasks;
        } catch (IOException exception) {
            throw new NoSuchFileException(file.getName());
        }
    }


    public void saveToFile(List<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : taskList) {
                writer.write(task.taskToData() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save the file :( " + e);
        }

    }
}
