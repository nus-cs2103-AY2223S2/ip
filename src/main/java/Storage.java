import java.io.*;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public void save(TaskList lst) {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lst);
            oos.close();
            fos.close();
            System.out.println("Your tasks are saved in the local disk! Roarrrrrrrrrrrrrrrrrr!");
        } catch (Exception e) {
            System.out.println("Roarrrrrrrrrrrrrrrrr! I cannot save your tasks in the local disk!");
            e.printStackTrace();
        }
    }

    public TaskList load() {
        File f = new File(filePath);
        if (f.exists()) {
            TaskList lst = new TaskList();
            try {
                FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                lst = (TaskList) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                System.out.println("Roarrrrrrrrrrrrrrrrrrrr! I cannot load your tasks in the local disk!");
                e.printStackTrace();
            }
            return lst;
        } else {
            System.out.println("Roarrrrrrrrrrrrrrr! You do not have a task list saved in the local disk!");
            System.out.println("Never mind! I will create one for you immediately. Roarrrrrrrrrrrrrrrrrrr!");
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Roarrrrrrrrrrrrrrrrrr! I cannot create the task list file!");
            }
            return new TaskList();
        }
    }
}
