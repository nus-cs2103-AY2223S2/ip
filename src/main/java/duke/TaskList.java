package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> lstOfItems;

    /**
     * Constructor for a TaskList instance
     * @param file the file where the previous list of items is stored
     */
    public TaskList(File file) {
        this.lstOfItems = new ArrayList<>();
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
        this.lstOfItems = new ArrayList<>();
    }

    /**
     * Parses the previously saved tasks that were keyed in, in the previous iteration.
     * @param currentTask the saved task in the File in the filePath.
     */
    public void parseTask(String currentTask) {
        Task task = null;
        if (currentTask.charAt(1) == 'T') {
            task = new Todo(currentTask.substring(7));
        } else if (currentTask.charAt(1) == 'D') {
            String[] split = currentTask.split("by: ");
            String description = split[0].substring(7, split[0].length() - 2);
            String date = split[1].substring(0, split[1].length() - 1);
            task = new Deadline(description, date);
        } else {
            String[] split = currentTask.split("from: ");
            String description = split[0].substring(7, split[0].length() - 2);
            String[] dateSplit = split[1].split(" to: ");
            String from = dateSplit[0];
            String to = dateSplit[1].substring(0, dateSplit[1].length() - 1);
            task = new Event(description, from, to);
        }
        if (currentTask.charAt(4) == 'X') {
            task.makeCompleted();
        }
        lstOfItems.add(task);
    }

    /**
     * Prints the contents of the list.
     * If the list is empty, print a custom message.
     * Otherwise, print out the contents of the list in the order in which they were added.
     */
    public String printContents() {
        String response = "Attempting to print out your tasks...\n";
        if (lstOfItems.size() == 0 ) {
            response += ("Nothing here yet. Add your 1st item!\n");
        } else {
            for (int i = 0; i < lstOfItems.size(); i++) {
                response += (String.valueOf(i + 1) + ".");
                response += (lstOfItems.get(i) + "\n");
            }
        }
        return response;
    }

    /**
     * Returns a string representation of a TaskList instance.
     * If there are no tasks, return "Empty List".
     * Otherwise, return the string representation of all tasks inside in order.
     * @return the desired string representation of a TaskList instance.
     */
    @Override
    public String toString() {
        if (lstOfItems.size() == 0) {
            return "Empty List";
        } else {
            String answer = "";
            for (int i = 0; i < lstOfItems.size(); i++) {
                answer += (lstOfItems.get(i).toString());
                answer += "\n";
            }
            return answer;
        }
    }

    /**
     * Adds the desired new Task into the TaskList.
     * @param newTask the desired new Task that the user wishes to add in.
     */
    public String addTask(Task newTask) {
        String response = "Adding new task in progress...\n";
        response += ("Got it. I have added: ");
        response += (newTask + "\n");
        lstOfItems.add(newTask);
        response += ("Now you have " + String.valueOf(lstOfItems.size()));
        if (lstOfItems.size() == 1) {
            response += (" task");
        } else {
            response += (" tasks");
        }
        response += (" in the list\n");
        return response;
    }

    /**
     * A method stub to test the TaskList class.
     * A stub Task with a simple "Test" description is added into the list of tasks.
     */
    public void addTask() {
        lstOfItems.add(new Task("Test"));
    }

    /**
     * Marks a task as completed.
     * @param number the task number to be marked as completed.
     */
    public String markTask(int number) {
        String response = "Marking task in progress...\n";
        try {
            if (number > lstOfItems.size()) {
                throw new DukeException("No such item!");
            } else {
                lstOfItems.get(number - 1).makeCompleted();
                response += ("Ok, I've marked this Task as completed: \n");
                response += (lstOfItems.get(number - 1) + "\n");
            }
        } catch (DukeException err) {
            response += (err + "\n");
        }
        return response;
    }

    /**
     * Deletes a task..
     * @param number the task number to be deleted.
     */
    public String deleteTask(int number) {
        String response = "Deleting task in progress...\n";
        try {
            if (number > lstOfItems.size()) {
                throw new DukeException("No such item!");
            } else {
                response += ("Ok, I've removed this Task: \n");
                response += (lstOfItems.get(number - 1) + "\n");
                lstOfItems.remove(number - 1);
                String remaining = (lstOfItems.size() == 1) ? " task" : " tasks";
                response += ("Now you have ");
                response += (String.valueOf(lstOfItems.size()) + remaining + " left!\n");
            }
        } catch (DukeException err) {
            response += (err + "\n");
        }
        return response;
    }

    /**
     * Returns the list of tasks that are currently present.
     * @return the list of tasks currently present.
     */
    public ArrayList<Task> getTasks() {
        return lstOfItems;
    }

    /**
     * Returns the number of tasks in the list at the moment.
     * @return the number of tasks currently in the list.
     */
    public int getSize() {
        return lstOfItems.size();
    }

    public String find(String keyword) {
        String response = "Finding " + keyword + " in progress...\n";
        int found = 0;
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            if (lstOfItems.get(i).contains(keyword)) {
                found++;
                foundTasks.add(lstOfItems.get(i));
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
