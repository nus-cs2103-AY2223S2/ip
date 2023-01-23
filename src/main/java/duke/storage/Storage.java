package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private Path filename;

    public Storage(String filename) {
        String[] parts = filename.split("/");
        this.filename = Paths.get(parts[0], parts[1], parts[2]);
    }

    public void save(TaskList tasks) throws DukeException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename.toString()))) {
            oos.writeObject(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        File file = filename.toFile();
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        if(file.length() != 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename.toString()))) {
                tasks.setTasks((ArrayList<Task>) ois.readObject());
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return tasks;
    }
}





