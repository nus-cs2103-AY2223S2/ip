package duke;

import java.util.ArrayList;
import java.io.IOException;

/**
 * A TaskList represents a list that stores all the user's current tasks
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();
    private int numberOfTasks = 0;
    private int numberUndone = 0;
    private int numberDone = 0;


    /**
     * A constructor for a Task List
     */
    public TaskList() {

    }

    /**
     * A Method to get the number of Tasks currently in the TaskList object
     * @return An integer that is the number of tasks currently in the TaskList
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    /**
     * Method to add a task to the TaskList object
     * @param task The Task object to be added to the TastList object
     */
    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it! I have added the following task: \n    " + task.toString());
        numberUndone++;
        numberOfTasks++;
    }

    /**
     * Method to add a task when reading from the storage file. This does not print a message that a task has been
     * added
     * @param task The Task object to be added to the TaskList object when reading from the storage file
     */
    public void addTaskWhenLoading(Task task) {
        this.list.add(task);
        numberUndone++;
        numberOfTasks++;
    }

    /**
     * Method to mark a task with the supplied index number as completed
     * @param taskNumber The index number of the Task object in the TaskList object
     */
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


    /**
     * Method to mark a task with the supplied index number as incomplete
     * @param taskNumber The index number of the Task object in the TaskList object
     */
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

    /**
     * Method that prints all the tasks in the TaskList object
     */
    public void printItems () {
        int numOfTasks = this.list.size();
        System.out.println("These are the tasks you have left to complete: ");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println(i + 1 + "." + list.get(i).toString());
        }
    }

    /**
     * Method to print the total number of tasks, as well as the number of tasks that are completed, and the number of
     * tasks that are yet to be completed
     */
    public void getTaskDetails() {
        String pluralCheck = (numberOfTasks == 1 ? " task" : " tasks");
        System.out.println("You now have " + numberOfTasks +  pluralCheck + " in the list");
        System.out.println("Number of tasks completed: " + numberDone);
        System.out.println("Number of tasks yet to be completed: " + numberUndone);

    }


    /**
     * Method to access a Task at a particular index in the TaskList object
     * @param index The index of the Task in the TaskList object
     * @return The Task object that was requested
     * @throws IOException
     */
    public Task getTaskAtIndex (int index) throws IOException {
       return this.list.get(index);
    }


    /**
     * Method to access the Task that was added last to the TaskList
     * @return The latest Task object in the TaskList object
     */
    public Task getLatestTask() {
        return this.list.get(this.list.size() - 1);
    }


    /**
     * Method to delete a Task from the TastList
     * @param taskNumber An integer that represents the index of the Task in the TaskList
     */
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








