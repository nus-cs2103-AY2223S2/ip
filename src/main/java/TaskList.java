import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();
    private int numberOfTasks = 0;
    private int numberUndone = 0;
    private int numberDone = 0;



    public TaskList() {

    }

    public int getNumberOfTasks() {
        return this.list.size();
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it! I have added the following task: \n    " + task.toString());
        numberUndone++;
        numberOfTasks++;
    }

    public void addTaskWhenLoading(Task task) {
        this.list.add(task);
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
        int numOfTasks = this.list.size();
        System.out.println("These are the tasks you have left to complete: ");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println(i + 1 + "." + list.get(i).toString());
        }
    }

    public void getTaskDetails() {
        System.out.println("You now have " + numberOfTasks + " tasks in the list");
        System.out.println("Number of tasks completed: " + numberDone);
        System.out.println("Number of tasks yet to be completed: " + numberUndone);

    }

    public Task getTaskAtIndex (int index) throws IOException {
       return this.list.get(index);
    }

    public Task getLatestTask() {
        return this.list.get(this.list.size() - 1);
    }



    public void deleteTask(int taskNumber) {
        if (numberOfTasks == 0) {
            System.out.println("You do not have any tasks at the moment!");
        } else {
            System.out.println("Noted! I have deleted this task: \n" + this.list.get(taskNumber - 1));
            boolean isDeletedDone = this.list.get(taskNumber - 1).isDone;
            this.list.remove(taskNumber - 1);
            this.numberOfTasks--;
            if (isDeletedDone) {
                numberDone--;
            } else {
                numberUndone--;
            }
        }

    }

  }








