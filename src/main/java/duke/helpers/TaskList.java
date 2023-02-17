package duke.helpers;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class: Structure that stores each task.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * TaskList constructor: Structure that stores each task.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds task into the TaskList structure.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Reads in a char (length 1 string), and outputs whether it is checked or not.
     *
     * @param mark Reads char, could be X or white space.
     */
    public boolean checkMark(String mark) {
        if (mark.equals("X")) {
            return true;
        } else return false;
    }

    /**
     * Reads in a index, which is the index to be removed in the structure.
     *
     * @param index Indicates which index of the structure to delete.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Adds a task object from the load storage into this TaskList structure.
     *
     * @param line Feeds in the line from load storage, interpretes it into a task.
     */
    public void addLine(String line) {
        if (line.substring(1,2).equals("T")) {
            String todo = line.substring(8);
            Todo newTodo = new Todo(todo, this.checkMark(line.substring(5,6)));
            this.addTask(newTodo);
        }
        if (line.substring(1,2).equals("D")) {
            String deadline = line.substring(8);
            Deadline newDead = new Deadline(deadline, this.checkMark(line.substring(5,6)));
            this.addTask(newDead);
        }
        if (line.substring(1,2).equals("E")) {
            String event = line.substring(8);
            Event newEvent = new Event(event, this.checkMark(line.substring(5,6)));
            this.addTask(newEvent);
        }
    }

    /**
     * Provides the string for Ui to print to the screen, whenever Task added to TaskList.
     *
     * @param task Gives the Task object that will be outputted.
     */
    public String addReport(Task task) {
        String returnStr = "gotcha.\nyou added: " + task.toString().substring(2) + "\n"
                + this.numberOfTasks();
        return returnStr;
    }

    /**
     * Provides the string for Ui to print to the screen, whenever Task removed to TaskList.
     *
     * @param task Gives the Task object that will be outputted.
     */
    public String deleteReport(Task task) {
        String returnStr = "gotcha.\nyou you have deleted: " + task.toString().substring(2) + "\n"
                + this.numberOfTasks();
        return returnStr;
    }

    /**
     * Switches the mark attribute of Task object within the TaskList structure by passing index.
     *
     * @param marked Redundant input
     * @param index Passes in index of Task element for TaskList to change the mark boolean.
     */
    public String mark(String marked, int index) {
        return this.tasks.get(index).setMark();
    }

    /**
     * Provides the size of the TaskList structure.
     */
    public String numberOfTasks() {
        return "You have " + this.tasks.size() + " tasks in this list!";
    }

    /**
     * Returns a String that details the contents of the TaskList's Task objects.
     */
    public String listThings() {
        if (this.tasks.size() == 0) {
            return "Oh... You currently have 0 tasks.... @.@";
        }
        String returnstr = "Alright, here are the things: \n";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i == this.tasks.size() - 1) {
                returnstr += Integer.toString(i+1) + this.tasks.get(i).toString();
            } else {
                returnstr += Integer.toString(i+1) + this.tasks.get(i).toString() + "\n";
            }
        } return returnstr;
    }

}