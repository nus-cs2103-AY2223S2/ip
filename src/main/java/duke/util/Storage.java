package duke.util;

import duke.Command;
import duke.Duke;
import duke.DukeException;
import duke.taskers.Deadline;
import duke.taskers.Event;
import duke.taskers.Task;
import duke.taskers.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.Scanner;

import java.util.ArrayList;

public class Storage {

    private String filePath;
    private String dirPath;
    private File dukeFile;

    /**
     * Storage constructor.
     *
     * @param filePath String representation of the path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        int lastIndexOfSlash = this.filePath.lastIndexOf(File.separator);
        this.dirPath = this.filePath.substring(0, lastIndexOfSlash);
        this.dukeFile = new File(this.filePath);
    }

    /**
     * The function that formats the task to store into the duke file.
     *
     * @param t The task to be appended to the file.
     * @return The string representation to be appended to the file.
     */
    public static String taskStringFormatter(Task t) {
        return t.formatStringForFile();
    }


    /**
     * Writes the tasks to the duke file.
     *
     * @param item The task that is inserted into the duke file.
     */
    public void writeToFile(Task item) {
        try {
            String fileInputString = taskStringFormatter(item);
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(fileInputString + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads all the items from the duke file.
     */
    public ArrayList<Task> loadFromFile() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(this.dirPath));
            Scanner sc = new Scanner(this.dukeFile);

            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] valueArr = str.split("/");
                // throw exceptions here later if you want

                String type = valueArr[0].toUpperCase().trim();
                Command commandType = Parser.parseCommand(type);
                Task thisTask = null;
                boolean doneOrNot = valueArr[1].equals("1");
                if (commandType.equals(Command.TODO)) {
                    thisTask = new Todo(valueArr[2], doneOrNot);
                } else if (commandType.equals(Command.DEADLINE)) {
                    LocalDateTime end = Duke.createLocalDateTime(valueArr[3]);
                    thisTask = new Deadline(valueArr[2], doneOrNot, end);
                } else {
                    LocalDateTime start = Duke.createLocalDateTime(valueArr[3]);
                    LocalDateTime end = Duke.createLocalDateTime(valueArr[4]);
                    thisTask = new Event(valueArr[2], doneOrNot, start, end);
                }
                loadedTasks.add(thisTask);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return loadedTasks;
    }



    /**
     * The function that deletes the entire duke file and re-inserts it with the current one.
     * Used for delete, mark and unmark when values are changed.
     */
    public void deleteFileAndRedo(ArrayList<Task> listOfThings) {
        if (this.dukeFile.delete()) {
            for (Task item : listOfThings) {
                writeToFile(item);
            }
        } else {
            System.out.println("File deletion failed");
        }
    }


}
