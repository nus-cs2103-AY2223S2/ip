package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

import duke.datetime.DateTime;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Task to manage and store tasks created by user in data.txt
 */
public class Storage {
    private static final String DATAPATH = "data.txt";

    /**
     * Constructor class for Storage
     * Creates data.txt if not present
     */
    public Storage() {
        try {
            File data = new File(Storage.DATAPATH);
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
        File file = new File(Storage.DATAPATH);
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
            Path filePath = Paths.get(Storage.DATAPATH);
            Files.write(filePath, (str + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
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
            Path filePath = Paths.get(Storage.DATAPATH);
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
            Path filePath = Paths.get(Storage.DATAPATH);
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
        String completed = task.getIsCompleted() ? "1" : "0";
        if (task instanceof Event) {
            res += "E,";
            res += completed + ",";
            res += task.getTaskName() + ",";
            res += DateTime.getDateTimeString(((Event) task).getStartTime()) + ",";
            res += DateTime.getDateTimeString(((Event) task).getEndTime());
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
