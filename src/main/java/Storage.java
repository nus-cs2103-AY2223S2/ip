import java.io.*;

public class Storage {
    static String savePath = "data/save.txt";
    static void store(TaskList list) {
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
