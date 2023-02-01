package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + newTask.toString());
        System.out.println("    Now you have " + this.taskList.size() + " tasks in the list");
    }

    /**
     * Takes in the selection made by the user as "taskNumber" and
     * <p>
     * removes that task from the list.
     * <p>
     * Argument must be the task number and not index of the task in the list.
     * @param taskNumber
     */
    public void remove(int taskNumber) {
        try {
            int taskIndex = taskNumber - 1;
            Task taskToRemove = this.taskList.get(taskIndex);
            System.out.println("    Noted. I've removed this task:");
            System.out.println("     " + taskToRemove.toString());
            this.taskList.remove(taskIndex);
            System.out.println("    Now you have " + this.taskList.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Incorrect selection made~  >:(");
        }
    }

    /**
     * Takes in the selection made by the user as "taskNumber" and
     * <p>
     * marks that task from the list with an "X".
     * <p>
     * Argument must be the task number and not index of the task in the list.
     * @param taskNumber
     */
    public void mark(int taskNumber) {
        int taskIndex = taskNumber - 1;
        this.taskList.get(taskIndex).mark();
        System.out.println("    OK, I've marked this task as done:");
        System.out.println("     " + this.taskList.get(taskIndex).toString());
    }

    /**
     * Takes in the selection made by the user as "taskNumber" and
     * <p>
     * marks that task from the list with an " ".
     * @param taskNumber number label and not the index of the task in the list
     */
    public void unmark(int taskNumber) {
        int taskIndex = taskNumber - 1;
        this.taskList.get(taskIndex).unmark();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("     " + this.taskList.get(taskIndex).toString());
    }

    /**
     * Iterates through the list and prints every task description with a number label next to it.
     */
    public void list() {
        for (int i = 0; i < this.taskList.size(); i++) {
            int numbering = i + 1;
            System.out.println("    " + numbering + "." + taskList.get(i).toString());
        }
    }

    public void find(String keyword) {
        ArrayList<Task> searchResult = new ArrayList<Task>();
        for (int i = 0; i < this.taskList.size(); i++) {
            if(taskList.get(i).getDescription().contains(keyword) == true) {
                searchResult.add(taskList.get(i));
            }
        }
        for (int j = 0; j < searchResult.size(); j++) {
            int numbering = j + 1;
            System.out.println("    " + numbering + "." + searchResult.get(j).toString());
        }
    }

    /**
     * Returns the Task using the index specified
     * @param taskIndex index of the task in the list
     */
    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Returns the list of task as an ArrayList
     * @return ArrayList of Task
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }
}