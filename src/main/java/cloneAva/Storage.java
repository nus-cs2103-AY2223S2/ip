package cloneAva;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private final static String STORAGE_DIR = "storage/";
    // File Name is stored here , so all storage functionality remains in the storage class
    private final static String FILE_NAME = "tasks.txt";

    public Storage(){
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            boolean success = directory.mkdir();
            if (!success){
                //throw AvaErrorCreatingDirectoryException();

            }
        }
    }

    public void writeToStorage(String input) /*throws AvaErrorUnableToStoreException*/ {
        File file  = new File(STORAGE_DIR + FILE_NAME);
        try {
        if (!file.exists()) {
            boolean s = file.createNewFile();
        }
            FileWriter fw = new FileWriter(file, true);
            //Design Decesion that each input will be stored in a new line
            fw.write(input + "\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            //throw AvaErrorUnableToStoreException
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<String> readStorage()/*throws AvaErrorUnableToReadStorageException*/ {
        ArrayList<String> res = new ArrayList<>();
        File file  = new File(STORAGE_DIR + FILE_NAME);
        try {
            if (!file.exists()) {
                // Creates a new file if file does not exists .
                file.createNewFile();
                return res;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                res.add(sc.nextLine());
            }
        } catch (IOException e) {
            //throw AvaErrorUnableToReadStorageException
        }
        return res;
    }

    public void deleteFile() {
        File file  = new File(STORAGE_DIR + FILE_NAME);
        boolean success = file.delete();
        if (!success) {
            // Throw Error
        }


    }

}
