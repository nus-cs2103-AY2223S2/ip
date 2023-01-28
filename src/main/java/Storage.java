import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private final static String STORAGE_DIR = "storage/";

    public Storage(){
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            boolean success = directory.mkdir();
            if (!success){
                //throw AvaErrorCreatingDirectoryException();

            }
        }
    }

    public void writeToStorage(String fileName, String input, boolean canAppend) /*throws AvaErrorUnableToStoreException*/ {
        File file  = new File(STORAGE_DIR + fileName);
        try {
        if (!file.exists()) {
            boolean s = file.createNewFile();
        }
            FileWriter fw = new FileWriter(file, canAppend);
            //Design Decesion that each input will be stored in a new line
            fw.write(input + "\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            //throw AvaErrorUnableToStoreException
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<String> readStorage(String fileName)/*throws AvaErrorUnableToReadStorageException*/ {
        ArrayList<String> res = new ArrayList<>();
        File file  = new File(STORAGE_DIR + fileName);
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

    public void deleteFile(String fileName) {
        File file  = new File(STORAGE_DIR + fileName);
        boolean success = file.delete();
        if (!success) {
            // Throw Error
        }


    }

}
