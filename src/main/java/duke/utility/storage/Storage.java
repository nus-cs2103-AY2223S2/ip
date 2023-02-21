package duke.utility.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import duke.tasklist.TaskList;

/**
 * Represents a <code>Storage</code> object that contain method to write and read data from a file.
 * 
 * 
 * @author Brian Quek
 */
public class Storage {
    private static final String FOLDER_PATH = "storage/";
    private static final String FILE_PATH = "storage/storage.txt";


    /**
     * Reads data from the .txt file and wraps into a TaskList object.
     * 
     * @return the list of tasks from the given .txt file.
     */
    public static TaskList readData() {
        TaskList list = new TaskList();

        try {
            validateFile();
            FileInputStream fileInput = new FileInputStream(FILE_PATH);
            ObjectInputStream objInput = new ObjectInputStream(fileInput);
            list = (TaskList) objInput.readObject();
            objInput.close();
            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println(e + "IO EXCEPTION");
        } catch (ClassNotFoundException e) {
            System.out.println(e + "CLASS NOT FOUND");
        }

        return list == null ? new TaskList() : list;
    }


    /**
     * Writes data to the .txt file specified by FILE_PATH, saving the TaskList object as a whole.
     * 
     * @param list the list of tasks to be written into the .txt file.
     */
    public static void writeData(TaskList list) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            objOutput.writeObject(list);
            objOutput.close();
            fileOutput.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /**
     * Checks if the relvant file/folder is created, creates it if it does not exist.
     */
    private static void validateFile() {
        File data = new File(FILE_PATH);

        if (data.exists()) {
            return;
        }

        FileCreate.createFolder(Paths.get(FOLDER_PATH));
        FileCreate.createFile(Paths.get(FILE_PATH));
        Storage.writeData(new TaskList());

    }
}
