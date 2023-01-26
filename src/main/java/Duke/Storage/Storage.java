package Duke.Storage;
import Duke.DateTime.DateTime;
import Duke.Parser.Parser;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.TaskList.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

/**
 * Task to manage and store tasks created by user in data.txt
 */
public class Storage {
    private static final String dataPath = "src/main/java/Duke/Storage/data.txt";

    public Storage() {
        try {
            File data = new File(Storage.dataPath);
            data.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println("Storage constructor Error in creating new file:" + e);
        }
    }

    /**
     * Add task in data.txt to TaskList
     *
     * @param tl TaskList to populate
     */
    public void populate(TaskList tl) {
        //populate the arraylist of taskmanager with values in file
        File file = new File(Storage.dataPath);
        try {
            Scanner sc = new Scanner(file);
            Parser parser = new Parser();
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                parser.parseAndAddStorageTask(str, tl);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Storage populate() data.txt is not found");
        }
    }

    /**
     * Add the string to data.txt
     *
     * @param str String to be added to data.txt
     */
    public void add(String str) {
        try {
            Path filePath = Paths.get(Storage.dataPath);
            Files.write(filePath,  (str + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (java.io.IOException e) {
            System.out.println("storage add() Error:" + e);
        }
    }

    /**
     * Overwrite line in data.txt to string provided
     *
     * @param str New string value to override to
     * @param line Line number of modify
     */
    public void modify(String str, int line) {
        try {
            Path filePath = Paths.get(Storage.dataPath);
            List<String> lines = Files.readAllLines(filePath);
            lines.set(line - 1, str);
            Files.write(filePath, lines);
        } catch (java.io.IOException e) {
            System.out.println("storage modify() Error:" + e);
        }
    }

    /**
     * Delete line in data.txt
     *
     * @param line Line number to delete
     */
    public void delete(int line) {
        try {
            Path filePath = Paths.get(Storage.dataPath);
            List<String> lines = Files.readAllLines(filePath);
            lines.remove(line - 1);
            Files.write(filePath, lines);
        } catch (java.io.IOException e) {
            System.out.println("storage delete() Error:" + e);
        }
    }

    /**
     * Given Task, convert them to the correct format in String to be stored
     * in data.txt.
     * Format: A,B,C,D,E
     * A: Type of task (E for event, D for deadline, T for todotask)
     * B: Status of task (1 for done, 0 for undone)
     * C: Taskname
     * D: DateTime of Deadline/From if Task is deadline/event
     * E: Datetime of To if Task is event
     *
     * @param task Task to be converted to string to be stored
     * @return String that can be stored in data.txt
     */
    public String getStorageTaskString(Task task) {
        String res = "";
        String completed = task.getStatus() ? "1" : "0";
        if (task instanceof Event) {
            res += "E,";
            res += completed + ",";
            res += task.getTaskName() + ",";
            res += DateTime.getDateTimeString(((Event) task).getFrom()) + ",";
            res += DateTime.getDateTimeString(((Event) task).getTo());
        } else if (task instanceof Deadline) {
            res += "D,";
            res += completed + ",";
            res += task.getTaskName() + ",";
            res += DateTime.getDateTimeString(((Deadline) task).getDeadline());
        } else {
            res += "T,";
            res += completed + ",";
            res += task.getTaskName();
        }
        return res;
    }
}
