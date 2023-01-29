package duke;

import java.util.ArrayList;

public class Tasklist {
    ArrayList<Task> listOfThings = new ArrayList<Task>();

    public void add(Task task) {
        listOfThings.add(task);
    }

    public void mark(int i) {
        Task thisTask = listOfThings.get(i - 1);
        thisTask.markDone();
        listOfThings.set(i - 1, thisTask);
        Ui.showToUser("Nice! I've marked this task as done:");
        Ui.showToUser(thisTask.toString());
    }

    public void unmark(int i) {
        Task thisTask = listOfThings.get(i - 1);
        thisTask.markUndone();
        listOfThings.set(i - 1, thisTask);
        Ui.showToUser("Nice! I've marked this task as undone:");
        Ui.showToUser(thisTask.toString());
    }

    public void delete(int i) {
        System.out.println("Noted. I've removed this task!");
        System.out.println(listOfThings.get(i - 1));
        listOfThings.remove(i - 1);
        System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
    }

    public int size() {
        return listOfThings.size();
    }

    public Task get(int i) {
        return listOfThings.get(i);
    }

    public void set(int i, Task task) {
        listOfThings.set(i, task);
    }

    public void printList() {
        for (int i = 0; i < listOfThings.size(); i++) {
            System.out.println(i + 1 + ". " + listOfThings.get(i));
        }
    }
} 
