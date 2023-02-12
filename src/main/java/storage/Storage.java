package storage;

import tasklist.TaskList;

import java.io.FileNotFoundException;
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

    /**
     * Constructor.
     *
     * @param path File Path.
     */
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * Returns the list of tasks from the data file.
     * From the data file, iterate through each line and convert it back to a Task.
     * Add the task into the list of tasks.
     * If a file cannot be found from the file path, create a new file. A new, empty list of tasks will be created.
     *
     * @return the list of tasks from the data file.
     */
    public TaskList load() {
        if (!file.exists()) {
            System.out.println("cannot find file");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("error occurred");
            }
        }
        TaskList list = new TaskList();
        try {
            Scanner sc = new Scanner(file);
            boolean test = sc.hasNextLine(); // for some reason this
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
        } catch (IOException e) {
            System.out.println("error");
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
        try {
            PrintWriter output = new PrintWriter(file);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.getTask(i);
                output.println(i + 1 + "." + task.toString());
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("error occurred");
        }
    }
}
