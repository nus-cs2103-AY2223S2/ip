package duke.storage;

import duke.task.*;
//import duke.task.Task;
//import duke.task.TaskList;

import java.io.*;
import java.util.ArrayList;

/**
 * Stores and loads tasks from a text file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage object.
     *
     * @param filePath The path of where tasks objects are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loop through taskList and store each task object into a file.
     *
     * @param taskList TaskList object containing the list of tasks.
     */
    public void saveTasks(TaskList taskList) {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < taskList.size(); i++) {
                oos.writeObject(taskList.get(i));
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loop through all objects in the file and stores each into an arrayList to be returned.
     * If file has not been created yet, create the file for future use.
     *
     * @return ArrayList of task objects that are read from a text file.
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(this.filePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object o;
                while (true) {
                    try {
                        o = ois.readObject();
                        if (o instanceof Task) {
                            tasks.add((Task) o);
                        } else {
                            System.out.println("Object is not a task");
                        }
                    } catch (EOFException e) {
                        System.out.println("what happened");
                        break;
                    }
                }
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create new file");
            }
        }
        return tasks;
    }
}
