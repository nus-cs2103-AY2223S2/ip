package duke;

import duke.task.Tasks;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    String fileName = "";

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void saveTasks(ArrayList<Tasks> task) {
        try {
            FileOutputStream file = new FileOutputStream(this.fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(task);
            output.close();
        } catch (IOException e) {
            System.out.println("file error");
        }
    }
    //adapted from CHATGPT
    public ArrayList<Tasks> loadTasks() {
        try {
            FileInputStream file = new FileInputStream(this.fileName);
            ObjectInputStream output = new ObjectInputStream(file);
            ArrayList<Tasks> task = (ArrayList<Tasks>)output.readObject();
            output.close();
            return task;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<Tasks>();
        }
    }
}
