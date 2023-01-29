package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class of Storage that saves and loads the given tasks by the user.
 */
public class Storage {
    private ArrayList<Task> tasklst;

    private String filepath;

    public Storage() {
        this.tasklst = new ArrayList<>();
    }

    public Storage(ArrayList<Task> tasklst) {
        this.tasklst = tasklst;
    }

    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasklst = new ArrayList<>();
    }

    /**
     * This method returns the task based on the id.
     *
     * @param i - index of the id.
     * @return Task - Returns the task of given id.
     */
    public Task getTask(int i) {
        return this.tasklst.get(i);
    }

    /**
     * This method removes and returns the task based on the selected id.
     *
     * @param i - index of the id.
     * @return Task - Returns the task of the given id being removed.
     */
    public Task removeTask(int i) {
        return this.tasklst.remove(i);
    }

    /**
     * This method adds a new task to the current tasklist.
     *
     * @param t - The given task.
     */
    public void addTask(Task t) {
        this.tasklst.add(t);
    }

    /**
     * This method returns the size of tasks in the list.
     *
     * @return int - Returns the size of the tasklist.
     */
    public int getSize() {
        return this.tasklst.size();
    }

    /**
     * This method returns the tasklist in a string format.
     *
     * @return String - Returns the output of the list of tasks.
     */
    public String getTasksString() {
        String res = "";
        int counter = 1;
        for (Task tmp : this.tasklst) {
            res += counter++ + ". " + tmp.toString() + "\n";
        }
        return res;
    }

    /**
     * This method returns the ArrayList of tasklist.
     *
     * @return ArrayList<Task> - Returns the ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasklst;
    }

    /**
     * This method loads the data of the stored tasks from a textfile.
     *
     * @return ArrayList<Task> - Returns the Task arraylist that is loaded from the textfile.
     * @throws DukeException - Error of the filed not being found.
     */
    public ArrayList<Task> load() throws DukeException {
        //@@author pzaiming-reused
        //Reused from https://github.com/RyanQiu1
        // with minor modifications
        try {
            File file = new File(this.filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArr = line.split("\\] ");
                String[] lineType = lineArr[0].split("\\]");
                Task t = new Task(lineType[0].substring(4), lineType[1].substring(1), lineArr[1]);
                this.addTask(t);
            }
            scanner.close();
            return this.tasklst;
        } catch (FileNotFoundException e) {
            throw new DukeException("The loading of file \"duke.txt\" is not found!");
        }
    }

    /**
     * This method will update the textfile based on the current commands inputed by the user.
     */
    public void updateStorage() {
        // create the directory if it is not found
        String DIRECTORY = "./data";
        try {
            File directory = new File(DIRECTORY);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String res = this.getTasksString();
            FileWriter myWriter = new FileWriter(this.filepath);
            myWriter.write(res);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred updating the hard disk.");
            e.printStackTrace();
        }
    }
}
