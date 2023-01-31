package leo.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import leo.task.TaskList;

public class Storage {
    public static void writeObjectToFile(Object obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream("taskList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in taskList.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
