package storage;

import dukeException.StorageException.CorruptedFileException;
import dukeException.StorageException.CreateFileException;
import dukeException.StorageException.LoadFileException;
import dukeException.StorageException.SaveFileException;
import struct.Triple;
import task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
            boolean folderCreationSuccessful = folder.mkdir();
            File file = new File(this.filePath);
            boolean isFileCreated = file.createNewFile();
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
