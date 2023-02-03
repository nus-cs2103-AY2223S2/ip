package duke;

import java.util.ArrayList;

import duke.tasktypes.Deadlines;
import duke.tasktypes.Task;

/**
 * Class containing the list of tasks for Duke chatbot.
 */
public class TaskList {
    protected ArrayList<Task> listOfTasks;

    /**
     * Constructor to initialize a TaskList instance.
     * @param listOfTasks An arraylist containing a list of tasks from the Duke chatbot.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Function to print out the list of tasks currently stored in the list to the user.
     */
    public void toRead() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Integer currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            String toUse = currIndex.toString() + "." + currTask.toString();
            System.out.println(toUse);
        }
    }

    /**
     * Deletes the task at the indicated index.
     * @param index The index which the task is stored at.
     * @throws DukeExceptions
     */
    public void deleteTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            Task gettingTask = listOfTasks.remove(indexToUse);
            String toOutput = "Noted. I've removed this task:\n  " + gettingTask.toString()
                    + "\nNow you have " + listOfTasks.size() + " tasks in the list";
            System.out.println(toOutput);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Finds a list of tasks containing the given keyword.
     * @param keyword String representation of the keyword.
     */
    public void findTasks(String keyword) {
        String starter = "Here are the matching tasks in your list:";
        Integer firstIndex = 1;
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currTask = listOfTasks.get(i);
            if (currTask.getName().contains(keyword)) {
                String printThis = "\n" + firstIndex.toString() + "." + currTask.toString();
                firstIndex++;
                starter += printThis;
            }
        }
        System.out.println(starter);
    }

    /**
     * Adds a task to the list of tasks.
     * @param toAdd The task which will be added to the list.
     */
    public void addTask(Task toAdd) {
        listOfTasks.add(toAdd);
        String toPrint = "";
        if (listOfTasks.size() == 1) {
            toPrint = "Got it. I've added this task:\n  " + toAdd.toString()
                    + "\nNow you have " + listOfTasks.size() + " task in the list.";
        } else {
            toPrint = "Got it. I've added this task:\n  " + toAdd.toString()
                    + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
        }
        System.out.println(toPrint);
    }

    /**
     * Marks the task at the indicated index as done.
     * @param index The index at which the task should be marked as done.
     * @throws DukeExceptions if the indicated index is invalid (<= 0 or larger than the size of the list)
     */
    public void markTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            Task currTask = listOfTasks.get(indexToUse);
            currTask.setDone();
            String toOutput = "Nice! I've marked this task as done:\n  " + currTask.toString();
            System.out.println(toOutput);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Marks the task at the indicated index as not done.
     * @param index The index at which the task should be marked as not done.
     * @throws DukeExceptions if the indicated index is invalid (<= 0 or larger than the list size)
     */
    public void unmarkTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            Task currTask = listOfTasks.get(indexToUse);
            currTask.setUndone();
            String toOutput = "Ok, I've marked this task as not done yet:\n  " + currTask.toString();
            System.out.println(toOutput);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Checks the due date of the task if it is applicable.
     * @param index The index at which to find out the due date of.
     */
    public void checkDueDate(int index) {
        int indexToUse = index - 1;
        if (listOfTasks.get(indexToUse) instanceof Deadlines) {
            Deadlines taskOfInterest = (Deadlines) listOfTasks.get(indexToUse);
            System.out.println(taskOfInterest.taskDate());
        } else {
            System.out.println("This task does not have a due date!");
        }
    }

}
