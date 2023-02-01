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

    public void mark(int taskNumber) {
        int taskIndex = taskNumber - 1;
        this.taskList.get(taskIndex).mark();
        System.out.println("    OK, I've marked this task as done:");
        System.out.println("     " + this.taskList.get(taskIndex).toString());
    }

    public void unmark(int taskNumber) {
        int taskIndex = taskNumber - 1;
        this.taskList.get(taskIndex).unmark();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("     " + this.taskList.get(taskIndex).toString());
    }

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

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

}
