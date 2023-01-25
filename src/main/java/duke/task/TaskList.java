package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> a) {
        this.list = a;
    }

    public void markTask(int taskno) {
        list.get(taskno).markAsDone();
        System.out.println(list.get(taskno));
        statement();
    }

    public void unmarkTask(int taskno) {
        list.get(taskno).markAsUndone();
        System.out.println(list.get(taskno));
        statement();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list: ");
        for (int x = 0; x < list.size(); x++) {
            System.out.println((x + 1) + "." + list.get(x));
        }
        System.out.println("");
        statement();
    }

    public void addToList(Task t) {
        list.add(t);
    }


    public void deleteTask(int taskno) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskno));
        list.remove(taskno);
        statement();
    }

    public int lengthOfList() {
        return list.size();
    }

    public void statement() {
        System.out.println("Now you have " + this.lengthOfList() + " tasks in your list.\n");
    }
}
