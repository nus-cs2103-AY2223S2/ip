package duke.task;

import duke.SortAlphabetical;

import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String add(Task newTask) {
        this.taskList.add(newTask);
        String taskAdded = "Got it. I've added this task:";
        String taskString = newTask.toString();
        String totalTask = "Now you have " + Integer.toString(this.taskList.size()) + " tasks in the list";
        return taskAdded + "\n" + taskString + "\n" + totalTask;
    }

    /**
     * Takes in the selection made by the user as "taskNumber" and
     * <p>
     * removes that task from the list.
     * <p>
     * Argument must be the task number and not index of the task in the list.
     * @param taskNumber
     */
    public String remove(int taskNumber) {
        try {
            int taskIndex = taskNumber - 1;
            Task taskToRemove = this.taskList.get(taskIndex);
            String taskRemoved = "Noted. I've removed this task:";
            String taskString = taskToRemove.toString();
            this.taskList.remove(taskIndex);
            String totalTask = "Now you have " + Integer.toString(this.taskList.size()) + " tasks in the list";
            return taskRemoved + "\n" + taskString + "\n" + totalTask;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Incorrect selection made~  >:(");
            return "Incorrect selection made~  >:(";
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
    public String mark(int taskNumber) {
        int taskIndex = taskNumber - 1;
        this.taskList.get(taskIndex).mark();
        String markMessage = "OK, I've marked this task as done:";
        String taskString = this.taskList.get(taskIndex).toString();
        return markMessage + "\n" + taskString;
    }

    /**
     * Takes in the selection made by the user as "taskNumber" and
     * <p>
     * marks that task from the list with an " ".
     * @param taskNumber number label and not the index of the task in the list
     */
    public String unmark(int taskNumber) {
        int taskIndex = taskNumber - 1;
        this.taskList.get(taskIndex).unmark();
        String markMessage = "OK, I've marked this task as not done yet:";
        String taskString = this.taskList.get(taskIndex).toString();
        return markMessage + "\n" + taskString;
    }

    /**
     * Iterates through the list and prints every task description with a number label next to it.
     */
    public String list() {
        String listMessage = "Here are the tasks in your list:";
        String listOfTask = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            int numbering = i + 1;
            listOfTask += Integer.toString(numbering) + "." + taskList.get(i).toString() + "\n";
        }
        return listMessage + "\n" + listOfTask;
    }

    public String find(String keyword) {
        ArrayList<Task> searchResult = new ArrayList<Task>();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword) == true) {
                searchResult.add(taskList.get(i));
            }
        }

        if (!searchResult.isEmpty()) {
            String findMessage = "According to [" + keyword + "], these are what I've found:";
            String listOfResult = "";
            for (int j = 0; j < searchResult.size(); j++) {
                int numbering = j + 1;
                listOfResult += Integer.toString(numbering) + "." + searchResult.get(j).toString() + "\n";
            }
            return findMessage + "\n" + listOfResult;
        } else {
            return "None of the task(s) matched your keyword :o";
        }
    }

    public String sort() {
        Collections.sort(this.taskList, new SortAlphabetical());
        return list();
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