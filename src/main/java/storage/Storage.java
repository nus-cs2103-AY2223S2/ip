package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dukeexception.storageexception.CorruptedFileException;
import dukeexception.storageexception.CreateFileException;
import dukeexception.storageexception.LoadFileException;
import dukeexception.storageexception.SaveFileException;
import struct.Triple;
import task.TaskList;

/**
<<<<<<< HEAD
 * Storage module for handling file saves and loads.
=======
 * Storage module for handling saves and loads.
>>>>>>> master
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a folder and a file, if not already created.
     */
    public void create() {
        try {
            File folder = new File("./data");
            boolean folderCreationIsSuccessful = folder.mkdir();
            File file = new File(this.filePath);
            boolean fileIsCreated = file.createNewFile();
        } catch (IOException e) {
            throw new CreateFileException();
        }
    }

    /**
     * Saves the data within the task list into a file.
     * @param taskList The task list to be saved.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(taskList.tasksStorageString("\n"));
            writer.close();
            System.out.println("OK saved for you.");
        } catch (IOException e) {
            throw new SaveFileException();
        }
    }

    /**
     * Loads the data from the text file.
     * @return A list of taskType-markedStatus-content triples is returned.
     */
    public ArrayList<Triple<Character, Boolean, String>> load() {
        try {
            File file = new File(this.filePath);
            Scanner scanner = new Scanner(file);
            ArrayList<Triple<Character, Boolean, String>> triples = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char taskType = line.charAt(0);
                boolean isMarked = line.charAt(2) == '1';
                String command = line.substring(4);
                triples.add(new Triple<>(taskType, isMarked, command));
            }
            return triples;
        } catch (FileNotFoundException e) {
            throw new LoadFileException();
        } catch (StringIndexOutOfBoundsException e) {
            File file = new File(this.filePath);
            boolean isDeleted = file.delete();
            this.create();
            throw new CorruptedFileException();
        }
    }
}
