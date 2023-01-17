package duke;

import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String fileName = "data.txt";

    public void save(List<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            System.out.println("\t[ERROR] While saving, the following error occurred: \n\t" + e);
        }
    }

    public void save(List<Task> tasks, String fileName) {
        this.fileName = fileName;
        this.save(tasks);
    }

    public List<Task> load() throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        @SuppressWarnings("unchecked")
        List<Task> tasks = (ArrayList<Task>) ois.readObject();
        fin.close();
        return tasks;
    }

    public List<Task> load(String fileName) throws IOException, ClassNotFoundException, ClassCastException {
        this.fileName = fileName;
        return this.load();
    }
}
