package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage object that handles the loading and unloading
 * of tasks from/to the hard disk.
 */
public class Storage {
    /** The file used to write/load data */
    private File file;
    /** The ArrayList used to store the loaded data*/
    private ArrayList<Task> outputList = new ArrayList<>();

    /**
     * Constructor for the storage class.
     * Also checks and initialises the folder and file
     * if they are not present.
     *
     * @param filepath the path to the file.
     */
    public Storage(String filepath) {
        // File object representing the data folder.
        File folder = new File("data");
        System.out.println("Initialising data dependencies:");
        System.out.println("    Checking for data directory...");
        try {
            if (folder.exists()) {
                System.out.println("    Data directory exists.");
            } else {
                System.out.println("    Data directory does not exist. Creating directory.");
                if (folder.mkdir()) {
                    System.out.println("    Data directory created.");
                } else {
                    throw new DukeException("    Data directory cannot be created.");
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        // File object representing the list file.
        this.file = new File(filepath);
        System.out.println("    Checking for saved file...");
        if (file.exists()) {
            System.out.println("    Saved list exists.");
        } else {
            System.out.println("    Saved list does not exist. Creating list file.");
            try {
                if (file.createNewFile()) {
                    System.out.println("    List file created.");
                } else {
                    throw new DukeException("    List file cannot be created.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Loads the data from hard disk to the ArrayList.
     */
    public ArrayList<Task> load() {
        System.out.println("    Loading data from file...");

        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String task = input.nextLine();
                String[] items = task.split("/", 5);

                if (items[0].equals("T")) {
                    outputList.add(new Todo(items[2]));
                } else if (items[0].equals("D")) {
                    outputList.add(new Deadline(items[2], items[3]));
                } else if (items[0].equals("E")) {
                    outputList.add(new Event(items[2], items[3], items[4]));
                } else {
                    throw new DukeException("Unknown task type detected.");
                }

                if (items[1].equals("1")) {
                    outputList.get(outputList.size() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("    Loading complete.");

        return outputList;
    }

    /**
     * Writes the data provided into the hard disk.
     */
    public void writeData() {
        try {
            FileWriter writer = new FileWriter("data/list.txt");
            for (int i = 0; i < outputList.size(); i++) {
                writer.write(outputList.get(i).printData() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Write to list file failed. ("
                    + e.getMessage() + ")");
        }
    }
}
