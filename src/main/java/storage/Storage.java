package storage;

import tasklist.TaskList;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

/**
 * Class which deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    String path;
    File file;
    PrintWriter output;
    Scanner sc;

    /**
     * Constructor.
     *
     * @param path File Path.
     * @throws IOException if PrintWriter is unable to find a file from the given path.
     */
    public Storage(String path) throws IOException {
        this.path = path;
        this.file = new File(path);
        this.sc = new Scanner(file);
        boolean test = this.sc.hasNextLine(); // for some reason this
        this.output = new PrintWriter(path);
    }

    /**
     * Returns the list of tasks from the data file.
     * From the data file, iterate through each line and convert it back to a Task.
     * Add the task into the list of tasks.
     * If a file cannot be found from the file path, create a new file. A new, empty list of tasks will be created.
     *
     * @return the list of tasks from the data file.
     * @throws IOException If a I/O error occurs
     */
    public TaskList load() throws IOException {
        if (!file.exists()) {
            System.out.println("cannot find file");
            file.createNewFile();
        }
        TaskList list = new TaskList();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] descriptionAndMarkedStatus = this.getDescriptionAndMarkedStatus(line);
            String description = descriptionAndMarkedStatus[0];
            String markedStatus = descriptionAndMarkedStatus[1];
            boolean marked = markedStatus.equals("X") ? true : false;
            char letter = line.charAt(3);
            Task task;
            if (letter == 'T') {
                task = new ToDo(description, marked);
            } else if (letter == 'D') {
                String[] words = description.split("\\|by: ");
                task = new Deadline(words[0], words[1], marked);
            } else {
                String[] words = description.split("\\|from: ");
                String[] fromTo = words[1].split(" to: ");
                task = new Event(words[0], fromTo[0], fromTo[1], marked);
            }
            list.addTask(task);
        }
        return list;
    }

    /**
     * Returns description of task and its marked status in the String form.
     * Manipulates the description from the data file into
     * description of the task and its marked status.
     *
     * @param description Description from the data file.
     * @return A String array, with the zero index as the description of the' task
     * and the first index as the marked status in String form.
     */
    public String[] getDescriptionAndMarkedStatus(String description) {
        String[] words = description.split("] ");
        String newDescription = words[1];
        String firstWord = words[0];
        String marked = firstWord.charAt(firstWord.length()-1) + "";
        return new String[]{newDescription, marked};
    }

    /**
     * Saves the list of tasks into the data file.
     * Iterates through the tasks in the list and write it to the data file.
     *
     * @param list List of tasks.
     */
    public void save(TaskList list) {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.getTask(i);
            this.output.println(i+1 + "." + task.toString());
        }
        this.output.close();
    }
}
