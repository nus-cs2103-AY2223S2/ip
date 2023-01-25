import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();
    private int numberOfTasks = 0;
    private int numberUndone = 0;
    private int numberDone = 0;



    public TaskList() {

    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it! I have added the following task: \n    " + task.toString());
        numberUndone++;
        numberOfTasks++;
    }

    public void markDone(int taskNumber) {
        int indexAdjustedTaskNumber = taskNumber - 1;
        this.list.get(indexAdjustedTaskNumber).mark();
        System.out.println("Great! I have marked this task as done!");
        System.out.println(list.get(indexAdjustedTaskNumber));
        numberUndone--;
        numberDone++;

    }

    public void markUndone(int taskNumber) {
        int indexAdjustedTaskNumber = taskNumber - 1;
        this.list.get(indexAdjustedTaskNumber).unmark();
        System.out.println("Noted. I have marked this task as undone!");
        System.out.println(list.get(indexAdjustedTaskNumber));
        numberUndone++;
        numberDone--;
    }

    public void printItems () {
        int numOfRequests = this.list.size();
        System.out.println("These are the tasks you have left to complete: ");
        for (int i = 0; i < numOfRequests; i++) {
            System.out.println(i + 1 + "." + list.get(i).toString());
        }
    }

    public void getTaskDetails() {
        System.out.println("You now have " + numberOfTasks + " tasks in the list");
        System.out.println("Number of tasks completed: " + numberDone);
        System.out.println("Number of tasks yet to be completed: " + numberUndone);

    }





}
