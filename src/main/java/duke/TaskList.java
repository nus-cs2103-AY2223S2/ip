package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Class for TaskList object, which encapsulates the list of tasks input by the user.
 *
 * @author Eric Leow Yu Quan
 */
public class TaskList {

    private ArrayList<Task> lstOfTasks;

    /**
     * Constructor for a TaskList instance.
     *
     * @param file the file where the previous list of items is stored.
     */
    public TaskList(File file) {
        this.lstOfTasks = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(file);
            int noOfTasks = Integer.parseInt(fileScanner.nextLine());
            for (int i = 0; i < noOfTasks; i++) {
                String curr = fileScanner.nextLine();
                parseTask(curr);
            }
        } catch (FileNotFoundException err) {
            System.out.println(err);
        }
    }

    public TaskList() {
        this.lstOfTasks = new ArrayList<>();
    }

    /**
     * Parses the previously saved tasks that were keyed in, in the previous iteration.
     *
     * @param currentTask the saved task in the File in the filePath.
     */
    public void parseTask(String currentTask) {
        Task task = null;
        if (currentTask.charAt(1) == 'T') {
            task = new Todo(currentTask.substring(7, currentTask.length() - 2));
        } else if (currentTask.charAt(1) == 'D') {
            String[] split = currentTask.split("by: ");
            String description = split[0].substring(7, split[0].length() - 2);
            String date = split[1].substring(0, split[1].length() - 3);
            task = new Deadline(description, date);
        } else {
            String[] split = currentTask.split("from: ");
            String description = split[0].substring(7, split[0].length() - 2);
            String[] dateSplit = split[1].split(" to: ");
            String from = dateSplit[0];
            String to = dateSplit[1].substring(0, dateSplit[1].length() - 3);
            task = new Event(description, from, to);
        }
        if (currentTask.charAt(4) == 'X') {
            task.makeCompleted();
        }
        char lastChar = currentTask.charAt(currentTask.length() - 1);
        if (lastChar == 'M') {
            task.increasePriority();
        } else if (lastChar == 'H') {
            task.increasePriority();
            task.increasePriority();
        }
        assert task != null : "No Task created!";
        lstOfTasks.add(task);

    }

    /**
     * Prints the contents of the list.
     * If the list is empty, print a custom message.
     * Otherwise, print out the contents of the list in the order in which they were added.
     */
    public String printContents() {
        String response = "Attempting to print out your tasks...\n";
        if (lstOfTasks.size() == 0) {
            response += ("Nothing here yet. Add your 1st item!\n");
        } else {
            assert lstOfTasks.size() > 0 : "List is empty!";
            for (int i = 0; i < lstOfTasks.size(); i++) {
                response += (String.valueOf(i + 1) + ".");
                response += (lstOfTasks.get(i) + "\n");
            }
        }
        return response;
    }

    public String sortTasks() {
        String response = "Sorting tasks according to highest priority level to lowest...\n";
        ArrayList<Task> sortedTasks = new ArrayList<>(this.lstOfTasks);
        Collections.sort(sortedTasks);

        for (int i = 0; i < sortedTasks.size(); i++) {
            response += sortedTasks.get(i) + "\n";
        }
        return response;
    }

    /**
     * Returns a string representation of a TaskList instance.
     * If there are no tasks, return "Empty List".
     * Otherwise, return the string representation of all tasks inside in order.
     *
     * @return the desired string representation of a TaskList instance.
     */
    @Override
    public String toString() {
        if (lstOfTasks.size() == 0) {
            return "Empty List";
        } else {
            String answer = "";
            for (int i = 0; i < lstOfTasks.size(); i++) {
                answer += (lstOfTasks.get(i).toString());
                answer += "\n";
            }
            return answer;
        }
    }

    /**
     * Adds the desired new Task into the TaskList.
     *
     * @param newTask the desired new Task that the user wishes to add in.
     */
    public String addTask(Task newTask) {
        String response = "Adding new task in progress...\n";
        assert lstOfTasks != null : "No list present!";
        response += ("Got it. I have added: ");
        response += (newTask + "\n");
        lstOfTasks.add(newTask);
        response += ("Now you have " + String.valueOf(lstOfTasks.size()));
        if (lstOfTasks.size() == 1) {
            response += (" task");
        } else {
            response += (" tasks");
        }
        response += (" in the list\n");
        return response;
    }

    /**
     * Creates a dummy Task and adds to the list.
     * This is a method stub to test the TaskList class.
     * A stub Task with a simple "Test" description is added into the list of tasks.
     */
    public void addTask() {
        assert lstOfTasks != null : "No List created!";
        lstOfTasks.add(new Task("Test"));
    }

    /**
     * Creates a dummy Task and adds to the list.
     * This is a method stub to test the TaskList class.
     * A stub Task with a simple "Dummy test" description is added into the list of tasks.
     */
    public void addDummyTask() {
        assert lstOfTasks != null : "No List created!";
        lstOfTasks.add(new Task("Dummy test"));
    }

    /**
     * Marks a task as completed.
     *
     * @param number the task number to be marked as completed.
     */
    public String markTask(int number) {
        assert lstOfTasks != null : "No List present!";
        String response = "Marking task in progress...\n";
        try {
            if (number > lstOfTasks.size()) {
                throw new DukeException("No such item!");
            } else {
                assert lstOfTasks.size() >= number : "List too small!";
                lstOfTasks.get(number - 1).makeCompleted();
                response += ("Ok, I've marked this Task as completed: \n");
                response += (lstOfTasks.get(number - 1) + "\n");
            }
        } catch (DukeException err) {
            response += (err + "\n");
        }
        return response;
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param number the task number to be marked as incomplete.
     */
    public String unmarkTask(int number) {
        assert lstOfTasks != null : "No List present!";
        String response = "Unmarking task in progress...\n";
        try {
            if (number > lstOfTasks.size()) {
                throw new DukeException("No such item!");
            } else {
                assert lstOfTasks.size() >= number : "List too small!";
                lstOfTasks.get(number - 1).makeIncomplete();
                response += ("Ok, I've unmarked this Task as incomplete: \n");
                response += (lstOfTasks.get(number - 1) + "\n");
            }
        } catch (DukeException err) {
            response += (err + "\n");
        }
        return response;
    }

    public String increasePriorityOfTask(int number) {
        assert lstOfTasks != null : "No List present!";
        String response = "Increasing priority of task in progress...\n";
        try {
            if (number > lstOfTasks.size()) {
                throw new DukeException("No such item!");
            } else {
                assert lstOfTasks.size() >= number : "List too small!";
                response += lstOfTasks.get(number - 1).increasePriority();
                response += (lstOfTasks.get(number - 1) + "\n");
            }
        } catch (DukeException err) {
            response += (err + "\n");
        }
        return response;
    }

    public String decreasePriorityOfTask(int number) {
        assert lstOfTasks != null : "No List present!";
        String response = "Decreasing priority of task in progress...\n";
        try {
            if (number > lstOfTasks.size()) {
                throw new DukeException("No such item!");
            } else {
                assert lstOfTasks.size() >= number : "List too small!";
                response += lstOfTasks.get(number - 1).decreasePriority();
                response += (lstOfTasks.get(number - 1) + "\n");
            }
        } catch (DukeException err) {
            response += (err + "\n");
        }
        return response;
    }
    /**
     * Deletes a task.
     *
     * @param number the task number to be deleted.
     */
    public String deleteTask(int number) {
        assert lstOfTasks != null : "No List created!";
        String response = "Deleting task in progress...\n";
        try {
            if (number > lstOfTasks.size()) {
                throw new DukeException("No such item!");
            } else {
                assert lstOfTasks.size() >= number : "List too small!";
                response += ("Ok, I've removed this Task: \n");
                response += (lstOfTasks.get(number - 1) + "\n");
                lstOfTasks.remove(number - 1);
                String remaining = (lstOfTasks.size() == 1) ? " task" : " tasks";
                response += ("Now you have ");
                response += (String.valueOf(lstOfTasks.size()) + remaining + " left!\n");
            }
        } catch (DukeException err) {
            response += (err + "\n");
        }
        return response;
    }

    /**
     * Returns the list of tasks that are currently present.
     *
     * @return the list of tasks currently present.
     */
    public ArrayList<Task> getTasks() {
        assert lstOfTasks != null : "No List created!";
        return lstOfTasks;
    }

    /**
     * Returns the number of tasks in the list at the moment.
     *
     * @return the number of tasks currently in the list.
     */
    public int getSize() {
        assert lstOfTasks != null : "No List created!";
        return lstOfTasks.size();
    }

    public String find(String keyword) {
        assert lstOfTasks != null : "No List created!";
        String response = "Finding in progress...\n";
        int found = 0;
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            if (lstOfTasks.get(i).contains(keyword)) {
                found++;
                foundTasks.add(lstOfTasks.get(i));
            }
        }
        if (found == 0) {
            response += ("Didn't manage to find any tasks with this keyword!\n");
        } else {
            String descriptor = (found == 1) ? " task " : " tasks ";
            response += ("Found " + String.valueOf(found) + descriptor + "with this keyword\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                response += (String.valueOf(i + 1) + ". ");
                response += (foundTasks.get(i) + "\n");
            }
        }
        return response;
    }
}
