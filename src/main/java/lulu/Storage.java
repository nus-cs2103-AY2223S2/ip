package lulu;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is used mainly during the start of the chatbot and the end of the chatbot when the ByeCommand is executed.
 * Representative of a storage, it helps to read and write to the file, specified by the String path.
 */
public class Storage {
    private File file;
    private String path;
    private File directoryFile;
    private static final String DIRECTORY = "./data";

    public Storage(String filePath) {
        this.file = new File(filePath);
        path = filePath;
        directoryFile = new File(DIRECTORY);
        directoryFile.mkdir();
    }

    /**
     * Returns true or false for whether a file at a specified location is present.
     * <p>
     * This method checks if a file at a specified directory is present. If it is not, a file will be created at the
     * specified location.
     *
     * @return true if file at specified location is present, false otherwise.
     */
    public boolean isSavePresent() {
        try {
            boolean isPresent = !(file.createNewFile());
            if (!isPresent) {
                System.out.println("Installing text file...");
            }
            return isPresent;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    /**
     * This method writes whatever is in the list to the file at the specified location
     *
     * @param list the array of string to be written
     */
    public void writeSave(ArrayList<String> list) {
        try {
            FileWriter myWriter = new FileWriter(path);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                myWriter.write(list.get(i));
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * This method reads the file at the specified location and loads it into a TaskList.
     *
     * @param tasks the TaskList which loads the data
     */
    public void readSave(TaskList tasks) {
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tasks.load(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}
