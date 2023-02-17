package alfred.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.IntStream;

import alfred.exceptions.AlfredException;


/**
 * Represents a task list that contains all the task given the user.
 */

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list that is filled with tasks from a data file.
     * @param tasks Contains an array of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the task list.
     * @param task A task given by the user.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Creates a String that contains all the information of the tasks inside the task list.
     * @return The string that contains all the tasks in a format.
     */
    public String getList() {
        int itemIndex = 1;
        StringBuilder listOfItems = new StringBuilder();

        IntStream.range(0, tasks.size())
                .forEach(index -> listOfItems.append(String.format("    %d. %s\n", index + 1, tasks.get(index))));

        String numTasks = tasks.size() <= 1 ? "task" : "tasks";
        listOfItems.append(String.format("You have %d %s in the list\n", tasks.size(), numTasks));
        return listOfItems.toString();
    }

    /**
     * Creates a String that contains all the information of the tasks inside the task list
     * that has attribute of the date.
     * @param date The date that we wish to find all the tasks that contains.
     * @return The string that contains all the tasks in a format.
     */
    public String getList(LocalDate date) {
        int itemIndex = 1;
        StringBuilder listOfItems = new StringBuilder();
        for (Task item : tasks) {
            if (item.containsDate(date)) {
                listOfItems.append(String.format("    %d. %s\n", itemIndex, item));
                itemIndex++;
            }
        }
        String numTasks = (itemIndex - 1) <= 1 ? "task" : "tasks";
        listOfItems.append(String.format("You have %d %s on %s in the list\n", itemIndex - 1, numTasks, date));
        return listOfItems.toString();
    }

    /**
     * Writes all the tasks in the task list into the data file given.
     * @param dataFile The data file that stores the data of this program
     * @throws AlfredException An exception thrown when there is an error writing the tasks
     *     into the data file.
     */
    public void writeToFile(File dataFile) throws AlfredException {
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (Task task : tasks) {
                fw.write(task.getFileFormat());
            }
            fw.close();
        } catch (IOException e) {
            throw new AlfredException(String.format("Something went wrong while"
                    + " saving the tasks, %s\n", e.getMessage()));
        }
    }

    /**
     * Returns a string that contains the list of tasks that contains the key word.
     * @param keyWords The keywords that we are looking for the in the task list.
     * @return The string that contains the list of tasks that contains the key word.
     */
    public String findTasks(String keyWords) {
        StringBuilder listOfTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        int itemIndex = 1;
        for (Task item : tasks) {
            if (item.containsKeyWords(keyWords)) {
                listOfTasks.append(String.format("    %d. %s\n", itemIndex, item));
                itemIndex++;
            }
        }
        String numTasks = (itemIndex - 1) <= 1 ? "task" : "tasks";
        listOfTasks.append(String.format("You have %d %s in the list with %s\n", itemIndex - 1, numTasks, keyWords));
        return listOfTasks.toString();
    }

    /**
     * Deletes the task from the task list.
     * @param taskIndex The index of the task in the list.
     */
    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Retrieves the task from the task list at the given index.
     * @param taskIndex The index of the task in the list.
     * @return The task that is retrieved from.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Retrieves the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty
     * @return True if the task list is empty, else false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
