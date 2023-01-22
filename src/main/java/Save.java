import java.io.*;
import java.util.ArrayList;

public class Save {
    public static void saveTaskList(TaskList tl) {
        try {
            FileOutputStream fos = new FileOutputStream("taskList.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tl);
            oos.close();
            fos.close();
            System.out.println("    Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TaskList loadTaskList() {
        File file = new File("taskList.txt");
        if (file.exists()) {
            TaskList tl = new TaskList();
            try {
                FileInputStream fis = new FileInputStream("taskList.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                tl = (TaskList) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tl;
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Cant create file");
            }
            return new TaskList();
        }
    }
}
