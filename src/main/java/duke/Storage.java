package duke;

import java.io.*;
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
     * The file path of the save file.
     */
    private String filePath;
    /**
     * Creates a new instance of Storage with the specified file path.
     * @param s the file path of the save file
     */
    public Storage(String s) {
        this.filePath = s;
    }

    /**
     * Loads a list of tasks from the save file.
     * @return an ArrayList of Task objects loaded from the save file
     */
    public ArrayList<Task> load() {
        assert this.filePath.length() > 0 : "this file path should not be empty";
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();
        File dir = new File(System.getProperty("user.dir") + "/data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            Scanner saveFile = new Scanner(file);
            System.out.println("    Saved data found, welcome back!");
            while (saveFile.hasNextLine()) {
                loadFile(saveFile.nextLine(), tasks);
            }
        } catch (IOException | DukeException ex) {
            System.out.println("    No saved data not found, new file will be created");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }
    /**
     * Loads a single line from the save file as a Task object and adds it to the specified ArrayList.
     * @param s a single line from the save file
     * @param list the ArrayList to which the Task object is added
     * @throws IOException if there is an error reading from the file
     * @throws DukeException if there is an error in the format of the file
     */
    public static void loadFile(String s, ArrayList<Task> list) throws IOException, DukeException {
        String[] currLine = s.split(" \\| ");
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
