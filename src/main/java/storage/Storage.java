package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import logic.commands.DeadlineCommand;
import logic.commands.DoAfterCommand;
import logic.commands.EventCommand;
import logic.response.Response;
import model.TaskList;
import model.tasks.Deadline;
import model.tasks.DoAfter;
import model.tasks.Event;
import model.tasks.Task;
import model.tasks.Todo;

/**
 * Class that manages the CRUD of the tasks
 */
public class Storage {

    private final String fileName;
    private final String filePath;
    private final String completeFilePath;

    /**
     * Constructor for Storage.
     *
     * @param storageName    Name of storage file. Should be a .txt file.
     * @param storageDirName Name of directory storing the storage file.
     */
    public Storage(String filePath, String fileName) {
		this.fileName = fileName;
		this.filePath = filePath;
        this.completeFilePath = String.format("%s%s%s", this.filePath, File.separator, this.fileName);
    }


    /**
     * Attempts to create a directory and/or storage file if it does not exist.
     *
     * @throws IOException Thrown when an error creating the storage file occurs.
     */
    public String initializeStorage() throws IOException {
        Path completeFilePath = Paths.get(this.completeFilePath);
        String res = "";

        if (!Files.exists(completeFilePath)) {
            Files.createDirectories(completeFilePath);
            res += Response.createFileMessage(this.fileName);

        }
        return res;
    }

    /**
     * Reads data from the database and inserts it into an ArarayList to be returned
     * @return ArrayList containing string of tasks
     * @throws IOException
     */
    public void load(TaskList taskList) throws IOException {
        File f = new File(this.completeFilePath);
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
            	String[] taskString = s.nextLine().split("\\|");
                switch (taskString[0]) {
                case "T": {
                    taskList.add(new Todo(taskString[2], Boolean.parseBoolean(taskString[1])));
                    break; 
                }
                case "D": {
                    taskList.add(new Deadline(taskString[2],
                    	DeadlineCommand.parseDeadlineStorage(taskString[3]),
            			Boolean.parseBoolean(taskString[1])));
                    break;
                }
                case "E": {
                    taskList.add(new Event(taskString[2],
            			EventCommand.parseEventStorage(taskString[3]),
            			EventCommand.parseEventStorage(taskString[4]),
            			Boolean.parseBoolean(taskString[1])));
                    break;
                }
                case "A" : {
                    taskList.add(new DoAfter(taskString[2],
                    	DoAfterCommand.parseDoAfterStorage(taskString[3]),
            			Boolean.parseBoolean(taskString[1])));
                    break;
                }
                default:
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves data in the ArrayList to the databasse
     * @param list
     * @throws IOException
     */
    public void save(TaskList list) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.completeFilePath);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String taskString = task.savedAs();
                fw.write(taskString + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
