package leo.storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import leo.task.TaskList;


/**
 * Encapsulates the storage functions of the task list.
 */

public class Storage {
    /**
     * Writes serialized tasklist to a file.
     * @param tl
     */
    public static void writeObjectToFile(TaskList tl) {
        try {
            FileOutputStream fileOut = new FileOutputStream("taskList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(tl);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in taskList.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
