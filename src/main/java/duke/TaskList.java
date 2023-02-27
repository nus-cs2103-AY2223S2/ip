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
    public String addTask(Task task) {
        this.list.add(task);
        numberUndone++;
        numberOfTasks++;
        return "Got it! I have added the following task: \n    " + task.toString();

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
    public String markDone(int taskNumber) {

        if (numberOfTasks == 0) {
            return "There are no tasks in your list!";
        }

        if (taskNumber > this.numberOfTasks || taskNumber <= 0) {
            return "Please enter a task number between 1 and the total number of tasks!";
        }
        int indexAdjustedTaskNumber = taskNumber - 1;

        Task toBeMarked = this.list.get(indexAdjustedTaskNumber);
        if (toBeMarked.isDone) {
            return "This task has already been marked as done!";
        } else {
            toBeMarked.setMarked();
        }

        numberUndone--;
        numberDone++;
        return "Great! I have marked this task as done! \n" + list.get(indexAdjustedTaskNumber).toString();


    }


    /**
     * Method to mark a task with the supplied index number as incomplete
     * @param taskNumber The index number of the Task object in the TaskList object
     */
    public String markUndone(int taskNumber) {


        if (numberOfTasks == 0) {
            return "There are no tasks in your task list!";
        }

        if (taskNumber > this.numberOfTasks || taskNumber <= 0) {
            return "Please enter a task number between 1 and the total number of tasks!";
        }

        int indexAdjustedTaskNumber = taskNumber - 1;
        Task toBeUnmarked = this.list.get(indexAdjustedTaskNumber);
        if (!toBeUnmarked.isDone) {
           return "This task is already marked as undone!";
        } else {
            toBeUnmarked.setUnmarked();
        }
        numberUndone++;
        numberDone--;
        return "Noted. I have marked this task as undone! \n" + list.get(indexAdjustedTaskNumber).toString();
    }

    /**
     * Method that prints all the tasks in the TaskList object
     */
    public String printItems () {
        String output = "These are the tasks you have left to complete: \n";
        int numOfTasks = this.list.size();

        for (int i = 0; i < numOfTasks; i++) {
            output +=  i + 1 + "." + list.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Method to print the total number of tasks, as well as the number of tasks that are completed, and the number of
     * tasks that are yet to be completed
     */
    public String getTaskDetails() {
        String pluralCheck = (numberOfTasks == 1 ? " task" : " tasks");
        String output = "\nYou now have " + numberOfTasks +  pluralCheck + " in the list";
        output += "\n Number of tasks completed: " + numberDone;
        output += "\n Number of tasks yet to be completed: " + numberUndone;
        return output;
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
    public String deleteTask(int taskNumber) {

        if (numberOfTasks == 0) {
            return "You do not have any tasks at the moment!";
        } else if (taskNumber > numberOfTasks || taskNumber < 0) {
            return "Please enter a task number between 1 and the total number of tasks!";
        } else {
            String output = "Noted! I have deleted this task: \n" + this.list.get(taskNumber - 1).toString();
            boolean isDeletedDone = this.list.get(taskNumber - 1).isDone;
            this.list.remove(taskNumber - 1);
            this.numberOfTasks--;
            if (isDeletedDone) {
                numberDone--;
            } else {
                numberUndone--;
            }
            return output;
        }

    }

    public String findTask(String keyword) {
        String output = "Here are the tasks matching the given keyword\n";
        int index = 1;
        for (Task task : list) {
            if (task.description.toUpperCase().contains(keyword.toUpperCase())) {
                output += index + " " + task.toString() + "\n";
                index++;
            }
        }
        if (index == 1) {
            return "There seem to be no tasks matching the keyword";
        }
        return output;
    }



  }








