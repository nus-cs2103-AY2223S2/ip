package Ava;

import java.io.File;

import Ava.exceptions.CannotCreateDirectory;
import Ava.exceptions.CannotReadFromFile;
import Ava.exceptions.CannotWriteToFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage  {
    private final static String STORAGE_DIR = "storage/";
    // File Name is stored here , so all storage functionality remains in the storage class
    private final static String FILE_NAME = "tasks.txt";
    private final static String FILE_PATH = STORAGE_DIR + FILE_NAME;



    private void CheckandCreateDirectory() throws CannotCreateDirectory{
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            boolean success = directory.mkdir();
            if (!success){
                throw new CannotCreateDirectory(this.STORAGE_DIR);

            }
        }
    }

    public void writeToStorage(String input) throws CannotCreateDirectory, CannotWriteToFile {
        File file  = new File(Storage.FILE_PATH);
        try {
        if (!file.exists()) {
            this.CheckandCreateDirectory();
            boolean s = file.createNewFile();
        }
            FileWriter fw = new FileWriter(file, true);
            //Design Decesion that each input will be stored in a new line
            fw.write(input + "\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new CannotWriteToFile(Storage.FILE_PATH);
        }
    }
    public ArrayList<String> readStorage() throws CannotCreateDirectory, CannotReadFromFile  {
        ArrayList<String> res = new ArrayList<>();
        File file  = new File(Storage.FILE_PATH);
        try {
            if (!file.exists()) {
                // Creates a new file if file does not exists .
                this.CheckandCreateDirectory();
                file.createNewFile();
                return res;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                res.add(sc.nextLine());
            }
        } catch (IOException e) {
            throw new CannotReadFromFile(Storage.FILE_PATH);
        }
        return res;
    }

    public void deleteFile() throws CannotReadFromFile {
        File file  = new File(STORAGE_DIR + FILE_NAME);
        boolean success = file.delete();
        if (!success) {
            // Throw Error
            throw new CannotReadFromFile(FILE_PATH);
        }


    }

}
