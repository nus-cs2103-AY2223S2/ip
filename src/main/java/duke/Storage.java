package duke;

import java.io.*;

public class Storage {
    static String saveFolder = "data";
    static String savePath = saveFolder + "/save.txt";

    static void store(TaskList list) {
        File dir = new File(saveFolder);
        if (!dir.exists()) {
            boolean ignored = dir.mkdir();
        }
        try (FileOutputStream fw = new FileOutputStream(savePath);
             ObjectOutputStream out = new ObjectOutputStream(fw)
        ) {
            out.writeObject(list);
        } catch (IOException e) {
            System.out.println("Saving error");
        }
    }

    static TaskList load() {
        try (FileInputStream fileInputStream = new FileInputStream(savePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (TaskList) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("No save file");
            return new TaskList();
        }
    }
}
