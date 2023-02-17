package duke.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Save tasks to local hard drive and loading it when program starts.
 *
 * @author jengoc415
 */
public class Storage {
    private File savedFile;

    /**
     * Constructor for storage instance
     *
     * @param savedFile File to read/write to
     */
    public Storage(File savedFile) {
        this.savedFile = savedFile;
    }

    /**
     * Load tasks saved in local hard disk.
     * If no previously saved file, create a new blank file.
     *
     * @return An ArrayList of tasks for Duke to do CRUD operations on.
     * @throws IOException Due to possible faulty inputs
     */
    public ArrayList<Task> populateTasks() throws IOException {
        Scanner sc;
        ArrayList<Task> tasks = new ArrayList<>();
        String command;
        String instr;
        String extendedInstr;
        String[] instrSplit;
        Task task;
        boolean completed;

        try {
            sc = new Scanner(savedFile);
            while (sc.hasNextLine()) {
                try {
                    extendedInstr = sc.nextLine();
                    // first character in instruction shows if task is completed
                    completed = Integer.parseInt(extendedInstr.substring(0, 1)) == 1 ? true : false;
                    instr = extendedInstr.substring(2);
                    instrSplit = instr.split(" ");
                    command = instrSplit[0];

                    if (command.equals("todo")) {
                        task = new Todo(instr);
                    } else if (command.equals("deadline")) {
                        task = new Deadline(instr);
                    } else {
                        task = new Event(instr);
                    }
                    tasks.add(task);

                    if (completed) {
                        task.setDone();
                    }
                } catch (DateTimeParseException | DukeException dtpe) {
                    throw new DukeException("Date Time error");
                }
            }
            sc.close();
        } catch (FileNotFoundException | DukeException fnfe) {
            savedFile.createNewFile();
        }
        return tasks;
    }

    /**
     * Save tasks into a file in local hard drive.
     *
     * @param encodedTasks Tasks with extended instructions
     *                     to include completion status.
     */
    public void saveTasks(String encodedTasks) {
        try {
            FileWriter fw = new FileWriter(savedFile);
            fw.write(encodedTasks);
            fw.flush();
            fw.close();
            for (int i = 0; i < 60; i++) {
                Thread.sleep(15);
                System.out.print(".");
            }
            System.out.print("\n");
            Thread.sleep(250);
            System.out.println("Your tasks have been successfully saved! :-)");
        } catch (IOException e) {
            System.out.println("Sorry, unable to save your tasks right now.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
