package saturday.utilities;

import saturday.collections.TaskList;

import java.io.*;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTaskList(TaskList taskList) {
        try {
            File file = new File(filePath);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            Ui.output(e.getMessage());
        }
    }

    public TaskList loadTaskList() {
        TaskList taskList = new TaskList();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                taskList = (TaskList) in.readObject();
                in.close();
                fileIn.close();
            }
        } catch (IOException | ClassNotFoundException e) {}
        return taskList;
    }
}
