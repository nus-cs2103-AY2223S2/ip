import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.util.ArrayList;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
public class FileManager {
    public ArrayList<Task> getFile() {
        try {
            FileInputStream fis = new FileInputStream("data\\pandora.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> toDoList = (ArrayList<Task>) ois.readObject();
            System.out.println("[Saved file loaded~]");
            return toDoList;
        } catch (IOException e) {
            System.out.println("[No saved file available, new To-Do-List created~]");
            ArrayList<Task> toDoList = new ArrayList<Task>();
            return toDoList;
        } catch (ClassNotFoundException e) {
            System.out.println("[No saved file available, new To-Do-List created~]");
            ArrayList<Task> toDoList = new ArrayList<Task>();
            return toDoList;
        }
    }

    public void saveFile(ArrayList<Task> toDoList) {
        try {
            FileOutputStream fos = new FileOutputStream("data\\pandora.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(toDoList);
            oos.close();
        } catch (IOException e) {
            System.out.println("Please create pandora.txt file");
        }
    }
}
