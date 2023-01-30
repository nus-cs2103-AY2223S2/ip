package storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

import task.Task;
import task.TaskList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList tasks) {
        try {
            OutputStream output = new FileOutputStream("./data.txt");
            ObjectOutputStream objOut = new ObjectOutputStream(output);
            objOut.writeObject(tasks);
            objOut.close();
        } catch (IOException e) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public TaskList loadTasks() {
        InputStream input = null;
        try {
            input = new FileInputStream("./data.txt");
            ObjectInputStream objIn = new ObjectInputStream(input);
            Object o = objIn.readObject();
            TaskList tasks = new TaskList();
            if (o instanceof TaskList) {
                tasks = (TaskList) o;
            }
            objIn.close();
            return tasks;
        } catch (FileNotFoundException e1) {
            return new TaskList();
        } catch (IOException | ClassNotFoundException e2) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, e2);
            return new TaskList();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
