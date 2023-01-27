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

public class Storage {
    private static final String FOLDER_PATH = "src/main/java/duke/utility/storage/";
    private static final String FILE_PATH = "src/main/java/duke/utility/storage/storage.txt";

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
