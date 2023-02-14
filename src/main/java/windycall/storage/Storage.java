package windycall.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import windycall.exception.WindyCallException;
import windycall.parser.Parser;
import windycall.task.Deadline;
import windycall.task.Event;
import windycall.task.Task;
import windycall.task.Todo;
import windycall.ui.Ui;


/**
 * Deals with loading tasks from the file and handle tasks change
 * by overwriting contents in tasks back to file
 */
public class Storage {

    public Storage() {

    }

    /**
     * Loads data inside data file to current tasks.
     * Handles non-existence of files or related directory
     *
     * @param tasks an ArrayList that will be loaded into
     */
    public void handleLoad(List<Task> tasks) {
        //check if "./data" directory exist
        File file = new File("./data");
        File taskFile = new File("./data/WindyCall.txt");;
        // if data file exists
        if (!file.isDirectory()) {
            file.mkdir();
        }
        try {
            if (taskFile.createNewFile()) {
                Ui.space();
                System.out.println("File created: " + taskFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred. Can not load your tasks");
            e.printStackTrace();
        }

        // load data in task_file into this.tasks
        try {
            Scanner myReader = new Scanner(taskFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.charAt(0) == 'T') {
                    if (data.charAt(9) == '|') {
                        String description = data.substring(11);
                        Task task = new Todo(description, data.charAt(4) == 'X');
                        tasks.add(task);
                    } else {
                        int idx = data.indexOf("|", 7);
                        String tag = data.substring(8, idx - 1);
                        String description = data.substring(idx + 2);
                        Task task = new Todo(description, data.charAt(4) == 'X', tag);
                        tasks.add(task);
                    }
                } else if (data.charAt(0) == 'D') {
                    if (data.charAt(9) == '|') {
                        int idx = data.indexOf("|", 10);
                        String description = data.substring(11, idx - 1);
                        String deadlineStr = data.substring(idx + 2);
                        LocalDate deadline = null;
                        try {
                            deadline = Parser.processDate(deadlineStr);
                        } catch (WindyCallException e) {
                            // since load from file
                            // for sure the format is correct
                        }
                        Task task = new Deadline(description, data.charAt(4) == 'X', deadline);
                        tasks.add(task);
                    } else {
                        int idx1 = data.indexOf("|", 7);
                        int idx2 = data.indexOf("|", idx1 + 1);
                        String description = data.substring(idx1 + 2, idx2 - 1);
                        String deadlineStr = data.substring(idx2 + 2);
                        LocalDate deadline = null;
                        try {
                            deadline = Parser.processDate(deadlineStr);
                        } catch (WindyCallException e) {
                            // since load from file
                            // for sure the format is correct
                        }
                        String tag = data.substring(8, idx1 - 1);
                        Task task = new Deadline(description, data.charAt(4) == 'X', deadline, tag);
                        tasks.add(task);
                    }
                } else {
                    if (data.charAt(9) == '|') {
                        int idx1 = data.indexOf("|", 10);
                        int idx2 = data.indexOf("|", idx1 + 1);
                        String description = data.substring(11, idx1 - 1);
                        String fromStr = data.substring(idx1 + 2, idx2 - 1);
                        String toStr = data.substring(idx2 + 2);
                        LocalDate from = null;
                        LocalDate to = null;
                        try {
                            from = Parser.processDate(fromStr);
                            to = Parser.processDate(toStr);
                        } catch (WindyCallException e) {
                            // since load from file
                            // for sure the format is correct
                        }
                        Task task = new Event(description, data.charAt(4) == 'X', from, to);
                        tasks.add(task);
                    } else {
                        int idx1 = data.indexOf("|", 7);
                        int idx2 = data.indexOf("|", idx1 + 1);
                        int idx3 = data.indexOf("|", idx2 + 1);
                        String description = data.substring(idx1 + 2, idx2 - 1);
                        String fromStr = data.substring(idx2 + 2, idx3 - 1);
                        String toStr = data.substring(idx3 + 2);
                        LocalDate from = null;
                        LocalDate to = null;
                        try {
                            from = Parser.processDate(fromStr);
                            to = Parser.processDate(toStr);
                        } catch (WindyCallException e) {
                            // since load from file
                            // for sure the format is correct
                        }
                        String tag = data.substring(8, idx1 - 1);
                        Task task = new Event(description, data.charAt(4) == 'X', from, to, tag);
                        tasks.add(task);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Handles tasks change by overwriting contents in tasks
     * back to data file so that change is saved each time tasks get modified
     *
     * @param tasks tasks that has been changed to do users' operation on tasks
     */
    public void handleTaskChange(List<Task> tasks) {
        // overwrite tasks back to data file to record the change
        try {
            FileWriter myWriter = new FileWriter("./data/WindyCall.txt");
            for (Task task : tasks) {
                myWriter.write(task.getFileFormat());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
