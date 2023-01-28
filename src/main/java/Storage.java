import java.io.*;
import java.util.ArrayList;

// learned from https://www.youtube.com/watch?v=TkC3sZxW2wY

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
                System.out.println(e);
            }
        }
        return tasks;
    }
}
