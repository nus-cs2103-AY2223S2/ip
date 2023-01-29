import java.util.ArrayList;
import java.io.IOException;

import java.util.Date;
public class toDoList {

    ArrayList<Task> allTasks;

    toDoList(){
        allTasks =  new ArrayList<Task>();
    }

    public int numberOfTask() {
        return allTasks.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". "+ allTasks.get(i).toString());
        }
    }

    public void addItem(String type, String item) {
        Task newTask = new Task(item, type);
        allTasks.add(newTask);
        System.out.println(" " + newTask.toString());
    }


    public void addItemDeadline(String type, String item, Date time, String timeString) {
        Task newTask = new Deadline(item, type, time, timeString);
        allTasks.add(newTask);
        System.out.println(" " + newTask.toString());
    }

    public void addItemEvent(String type, String item, Date time1, Date time2, String start, String end) {
        Task newTask = new Event(item, type, time1, time2, start, end);
        allTasks.add(newTask);
        System.out.println(" " + newTask.toString());
    }

    public void changingStatus(int x, int index) {
        if(x == 0) { //marked
            allTasks.get(index-1).mark();

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + allTasks.get(index-1).toString());

        } else { //unmarked
            allTasks.get(index-1).unmark();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + allTasks.get(index-1).toString());
        }
    }

    public void deleteTask(int index) {
        System.out.println(" " + allTasks.get(index-1).toString());
        allTasks.remove(index-1);
    }

    public ArrayList<Task> getTasks() {
        return allTasks;
    }


}
