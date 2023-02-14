package duke;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that handles loading and saving tasks to and from a txt file.
 */
public class Storage {

    private String filename;

    public Storage(String filename) {
        this.filename = filename;
        try {
            File f = new File(filename);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("IOException error: " + e);
        }
    }

    /**
     * Stores and saves the given list into a txt file in a formatted style
     *
     * @param taskArr The array list to save
     */
    public void storeList(ArrayList<Task> taskArr) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
            for (Task task : taskArr) {
                // Format descriptions of todo, deadline, event
                String descrip = "";
                switch (task.getIcon()) {
                case "T":
                    descrip = task.getDescription();
                    break;
                case "D":
                    descrip = task.getDescription() + " | " + ((Deadline)task).getByDate();
                    break;
                case "E":
                    descrip = task.getDescription() + " | " + ((Event)task).getFromDate()
                            + " | " + ((Event)task).getToDate();
                    break;
                case "F":
                    descrip = task.getDescription() + " | " + ((FixedTask)task).getHours();
                    break;
                default:
                    descrip = "error";
                }
                // Format status of marked or unmarked, 1 or 0
                int status = task.getStatusIcon() == "X" ? 1 : 0;
                writer.write(task.getIcon() + " | " + status + " | " + descrip);
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found error");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    /**
     * Loads the task list from the txt file and stores it in an ArrayList
     *
     * @return The ArrayList containing all saved tasks
     */
    public ArrayList<Task> loadList() {
        ArrayList<Task> taskArr = new ArrayList<>();
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] dataArr = line.split(" \\| ");
                boolean isMarked = dataArr[1] == "1" ? true : false;
                String descrip = dataArr[2];
                switch (dataArr[0]) {
                case "T":
                    Task t = new Task(descrip);
                    t.setDone(isMarked);
                    taskArr.add(t);
                    break;
                case "D":
                    String by = dataArr[3];
                    Deadline d = new Deadline(descrip, by);
                    d.setDone(isMarked);
                    taskArr.add(d);
                    break;
                case "E":
                    String from = dataArr[3];
                    String to = dataArr[4];
                    Event e = new Event(descrip, from, to);
                    e.setDone(isMarked);
                    taskArr.add(e);
                    break;
                case "F":
                    String hours = dataArr[3];
                    FixedTask fixedTask = new FixedTask(descrip, hours);
                    fixedTask.setDone(isMarked);
                    taskArr.add(fixedTask);
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found error");
        }
        return taskArr;
    }

}
