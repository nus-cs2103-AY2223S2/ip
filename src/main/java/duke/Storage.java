package duke;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import util.DukeException;

/**
 * The Storage class is responsible for reading and writing tasks from/to a file.
 * It contains methods for creating a save file if one does not exist,
 * loading a list of tasks from a file, converting a string to a boolean value,
 * saving a list of tasks to a file, and clearing the contents of a file.
 */
public class Storage {
    /**
     * Creates a directory for storing task data and a new file for storing the tasks if it does not exist.
     */
    public static void initSaveFile() {
        File dir = new File("src/main/data/");
        dir.mkdirs();
        // create a file object for the current location
        File file = new File(dir, "duke.txt");

        try {
            // create a new file with name specified
            // by the file object
            boolean value = file.createNewFile();
            if (value) {
                FileWriter output = new FileWriter("duke.txt");
                output.write("---------------LIST OF TASKS--------------------");
                output.append('\n');
                System.out.println("New Save File is created.");
            } else {
                System.out.println("Save file already exists.");
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
    }
    /**
     * Loads tasks from a file and adds them to a task list.
     * @param list a list of tasks
     * @throws IOException if there is an error reading from the file
     * @throws DukeException if there is an error in the format of the file
     */
    public static void loadFile(TaskList list) throws IOException, DukeException {
        File file = new File(System.getProperty("user.dir") + "/data/duke.txt");
        File dir = new File(System.getProperty("user.dir") + "/data");

        // if directory has not been created, make directory
        if (!dir.exists()) {
            dir.mkdir();
        }

        file.createNewFile();
        //takes file's content as input, scans through files and adds items to the list
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String[] currLine = sc.nextLine().split(" \\| ");
            if (currLine[0].equalsIgnoreCase("T")) {
                list.add(new Todo(strToBool(currLine[1]), currLine[2]));
            } else if (currLine[0].equalsIgnoreCase("D")) {
                list.add(new Deadline(strToBool(currLine[1]), currLine[2], currLine[3]));
            } else if (currLine[0].equalsIgnoreCase("E")) {
                list.add(new Event(strToBool(currLine[1]), currLine[2], currLine[3], currLine[4]));
            } else {
                throw new DukeException("Read Error");
            }
        }
    }
    /**
     * Converts a string representation of a boolean value to a boolean value.
     * @param str a string representation of a boolean value
     * @return the corresponding boolean value
     */
    public static boolean strToBool(String str) {
        if (str.equalsIgnoreCase("1")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Saves a list of tasks to a file.
     * @param list a list of tasks
     * @throws IOException if there is an error writing to the file
     */
    public static void saveToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/duke.txt");
        ArrayList<Task> tasks = TaskList.getList();
        for (Task t: tasks) {
            fw.write(t.saveFormat() + "\n");
        }
        fw.close();
    }
    /**
     * Clears the contents of a file.
     * @param filename the name of the file to be cleared
     * @throws IOException if there is an error clearing the file
     */
    public static void clearFile(String filename) throws IOException {
        FileOutputStream writer = new FileOutputStream(filename);
        writer.write(("").getBytes());
        writer.close();
    }
}
