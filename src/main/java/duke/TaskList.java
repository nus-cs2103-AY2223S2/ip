package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of the list containing tasks
 */
public class TaskList {
    private ArrayList<Task> list;
    private int length;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");


    /**
     * Constructor for the TaskList class
     * @param tasks
     */
    public TaskList(ArrayList<String> tasks) throws DukeException {
        this.list = new ArrayList<>();
        for (String s: tasks) {
            length++;
            String[] array = s.split("\\|");

            switch (array[0]) {
            case "T": {
                this.addTask(new Todo(array[2], Boolean.parseBoolean(array[1])));
                break;
            }
            case "D": {
                LocalDateTime byDate = LocalDateTime.parse(array[3], storageFormatter);
                this.addTask(new Deadline(array[2], byDate, Boolean.parseBoolean(array[1])));
                break;
            }
            case "E": {
                LocalDateTime fromDate = LocalDateTime.parse(array[3], storageFormatter);
                LocalDateTime toDate = LocalDateTime.parse(array[4], storageFormatter);
                this.addTask(new Event(array[2], fromDate, toDate, Boolean.parseBoolean(array[1])));
                break;
            }
            case "P": {
                LocalDateTime fromDate = LocalDateTime.parse(array[3], storageFormatter);
                LocalDateTime toDate = LocalDateTime.parse(array[4], storageFormatter);
                this.addTask(new PeriodTask(array[2], fromDate, toDate, Boolean.parseBoolean(array[1])));
                break;
            }
            default:
                throw new DukeException("Error Task Type!!");
            }
        }

    }

    public TaskList() {
        list = new ArrayList<>();
    }


    /**
     * return size of the array
     */
    public int getNumTask() {
        return list.size();
    }


    /**
     * Adds a task to the list
     * @param t task to be added to the list
     */
    public String addTask(Task t) {
        list.add(t);
        length += 1;
        return ("Got it. I've added this task: \n" + t + "\nNow you have " + getNumTask() + " tasks in the list.");
    }



    /**
     * Loads a task to the list (silent addTask)
     * @param t task to be added to the list
     */
    public void loadTask(Task t) {
        list.add(t);
        length += 1;
    }


    /**
     * Deletes a task from the list
     * @param index index of the task to be deleted
     */
    public String deleteTask(int index) {
        Task t = list.get(index);
        list.remove(index);
        length -= 1;
        return ("Noted. I've removed this task: \n" + t + "\nNow you have " + getNumTask() + " tasks in the list.");
    }


    /**
     * Finds all the task with keyword
     */
     public String find(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list: \n");
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            if (list.get(i).getTaskDesc().contains(keyword)) {
                sb.append( index + ". " + list.get(i) + "\n");
            }
        }
        if (sb.length() == 0) {
            return "There are no matching tasks.";
        }
        return (sb.toString());
    }


    /**
     * Prints out all the tasks in the list
     * @return all the tasks in the list
     */
    public String printList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");

        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            sb.append(index + ". " + list.get(i) + "\n");
        }
        return (sb.toString());
    }

    /**
     * returns the ArrayList stored
     * @return ArrayList
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Set a specific task at an index to be done
     * @param i the index of the task
     * @return String indicating that the task has been set to done
     */
    public String setDone(int i) {
        this.list.get(i).setDone();
        return ("I've marked this task as done: \n" + list.get(i));
    }

    /**
     * Set a specific task at an index to be done
     * @param i the index of the task
     * @return String indicating that the task has been set to done
     */
    public String setUndone(int i) {
        this.list.get(i).setUndone();
        return ("I've unmarked this task for you: \n" + list.get(i));
    }

}
