package duke;

import java.util.ArrayList;
import java.io.IOException;

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

        if (numberOfTasks == 0) {
            System.out.println("There are no tasks in your list!");
            return;
        }

        if (taskNumber > this.numberOfTasks || taskNumber <= 0) {
            System.out.println("Please enter a task number between 1 and the total number of tasks!");
            return;
        }
        int indexAdjustedTaskNumber = taskNumber - 1;

        Task toBeMarked = this.list.get(indexAdjustedTaskNumber);
        if (toBeMarked.isDone) {
            System.out.println("This task has already been marked as done!");
            return;
        } else {
            toBeMarked.mark();
        }
        System.out.println("Great! I have marked this task as done!");
        System.out.println(list.get(indexAdjustedTaskNumber));
        numberUndone--;
        numberDone++;

    }



    public void markUndone(int taskNumber) {


        if (numberOfTasks == 0) {
            System.out.println("There are no tasks in your task list!");
        }

        if (taskNumber > this.numberOfTasks || taskNumber <= 0) {
            System.out.println("Please enter a task number between 1 and the total number of tasks!");
            return;
        }

        int indexAdjustedTaskNumber = taskNumber - 1;
        Task toBeUnmarked = this.list.get(indexAdjustedTaskNumber);
        if (!toBeUnmarked.isDone) {
            System.out.println("This task is already marked as undone!");
            return;
        } else {
            toBeUnmarked.unmark();
        }
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
        String pluralCheck = (numberOfTasks == 1 ? " task" : " tasks");
        System.out.println("You now have " + numberOfTasks +  pluralCheck + " in the list");
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
        } else if (taskNumber > numberOfTasks || taskNumber < 0) {
            System.out.println("Please enter a task number between 1 and the total number of tasks!");
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








