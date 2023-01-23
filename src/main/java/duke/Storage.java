package duke;
import java.io.*;
import duke.TaskList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTaskList(TaskList tl) {
        try {
            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tl);
            oos.close();
            fos.close();
            System.out.println("    Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TaskList loadTaskList() {
        File file = new File(this.filePath);
        if (file.exists()) {
            TaskList tl = new TaskList();
            try {
                FileInputStream fis = new FileInputStream(this.filePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                tl = (TaskList) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                System.out.println("File is empty");
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
