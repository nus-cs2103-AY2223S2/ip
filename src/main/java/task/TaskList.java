package task;

import duke.Ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * This class handles the TaskList operations.
 * @author Bryan Ong
 */
public class TaskList {

    private static File dataFile;
    private static boolean isNew;
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(File file, boolean isItNew) throws FileNotFoundException{
        dataFile = file;
        isNew = isItNew;
        if (!isNew) {
            init();
        }
    }

    /**
     * This method handles the addition of Task
     * to the Tasklist.
     * @param t Task to be added.
     */
    public static void addToList(Task t) {
        taskList.add(t);
    }

    /**
     * This method handles the retrieval of task
     * by its serial number.
     * @param num Serial number.
     * @return Task The task of this serial number.
     */
    public static Task get(int num) {
        return taskList.get(num);
    }

    /**
     * This method handles the initialization of the file.
     * @throws FileNotFoundException
     */
    private void init() throws FileNotFoundException {
        Scanner sc = new Scanner(dataFile);
        while (sc.hasNext()) {
            String in = sc.nextLine();
            String[] split = in.split(" \\| ");
            boolean isDone = split[1].equals("1");
            switch (split[0].toUpperCase()) {
                case "T" :
                    Todo t = new Todo(split[2], isDone);
                    taskList.add(t);
                    break;
                case "E" :
                    Event e = new Event(split[2], split[3], split[4], isDone);
                    taskList.add(e);
                    break;
                case "D" :
                    Deadline d = new Deadline(split[2], split[3], isDone);
                    taskList.add(d);
                    break;
                default:
                    break;

            }
        }
    }

    /**
     * This method handles the printing of list command.
     */
    public static void printList() {
        ListIterator<Task> li = taskList.listIterator();
        if (!li.hasNext()) {
            Ui.printListEmpty();
        } else {
            int count = 1;
            Ui.printListPrompt();
            while (li.hasNext()) {
                Task curr = li.next();
                System.out.print(count + ". ");
                System.out.print(curr);
                count++;
            }
            Ui.printDivider();
        }
    }

    public static void remove(int num) {
        taskList.remove(num);
    }
    public static void writeToFile() {
        if (!taskList.isEmpty()) {
            try (BufferedWriter writer = Files.newBufferedWriter(dataFile.toPath())) {
                for (Task curr : taskList) {
                    writer.write(curr.write(dataFile));
                }
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (!isNew) {
                try (BufferedWriter writer = Files.newBufferedWriter(dataFile.toPath())) {
                    writer.write("");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
