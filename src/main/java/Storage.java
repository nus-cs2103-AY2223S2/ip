import java.io.*;
import java.util.ArrayList;

// learned from https://www.youtube.com/watch?v=TkC3sZxW2wY

public class Storage {
    private String fileName = "taskList.txt";

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(this.fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Task task : tasks) {
                oos.writeObject(task);
            }
            System.out.println("saved");
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadTasks() throws IOException {
        File file = new File("taskList.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(this.fileName);
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
                        break;
                    }
                }
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            file.createNewFile();
        }
        return tasks;
    }
}
