import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;


    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void markDone(int taskNumber) {
        int indexAdjustedTaskNumber = taskNumber - 1;
        this.list.get(indexAdjustedTaskNumber).mark();
        System.out.println("Great! I have marked this task as done!");
        System.out.println(list.get(indexAdjustedTaskNumber));

    }

    public void markUndone(int taskNumber) {
        int indexAdjustedTaskNumber = taskNumber - 1;
        this.list.get(indexAdjustedTaskNumber).unmark();
        System.out.println("Noted. I have marked this task as undone!");
        System.out.println(list.get(indexAdjustedTaskNumber));
    }

    public void printItems () {
        int numOfRequests = this.list.size();
        System.out.println("These are the tasks you have left to complete: ");
        for (int i = 0; i < numOfRequests; i++) {
            System.out.println(i + 1 + "." + list.get(i).toString());
        }
    }


}
