import java.io.*;
import java.util.ArrayList;

public class Storage {
    static String savePath = "data/save.txt";
    static void store(ArrayList<Task> list) {
        try (FileOutputStream fw = new FileOutputStream(savePath);
             ObjectOutputStream out = new ObjectOutputStream(fw)
        ) {
            out.writeObject(list);
        } catch (IOException e) {
            System.out.println("Saving error");
        }
    }

    @SuppressWarnings("unchecked")
    static ArrayList<Task> load() {
        try (FileInputStream fileInputStream = new FileInputStream(savePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (ArrayList<Task>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No save file");
            return new ArrayList<>();
        }
    }
}
