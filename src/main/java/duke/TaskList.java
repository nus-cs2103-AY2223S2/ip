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
     * Function to return string representation of the list of tasks currently stored in the list to the user.
     * @return String representation of the list of tasks currently stored.
     */
    public String toRead() {
        String toPrint = "Here are the tasks in your list:\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            Integer currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            String toUse = currIndex.toString() + "." + currTask.toString() + "\n";
            toPrint += toUse;
        }
        return toPrint;
    }

    /**
     * Deletes the task at the indicated index.
     * @param index The index which the task is stored at.
     * @return String representation of the list of tasks after deleting indicated task.
     * @throws DukeExceptions
     */
    public String deleteTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            int sizeBeforeDelete = listOfTasks.size();
            Task gettingTask = listOfTasks.remove(indexToUse);
            assert sizeBeforeDelete - 1 == listOfTasks.size() : "It appears the"
                    + "task has not been successfully deleted.";
            String toOutput = "Noted. I've removed this task:\n  " + gettingTask.toString()
                    + "\nNow you have " + listOfTasks.size() + " tasks in the list";
            return toOutput;
        } catch (DukeExceptions DE) {
            return DE.toString();
        }
    }

    /**
     * Finds a list of tasks containing the given keyword.
     * @param keyword String representation of the keyword.
     * @return String representation of the list of tasks containing given keyword.
     */
    public String findTasks(String keyword) {
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
        return starter;
    }

    /**
     * Adds a task to the list of tasks.
     * @param toAdd The task which will be added to the list.
     * @return String representation of the list of tasks after adding specified task.
     */
    public String addTask(Task toAdd) {
        int sizeBeforeAdd = listOfTasks.size();
        listOfTasks.add(toAdd);
        assert sizeBeforeAdd + 1 == listOfTasks.size() : "It appears that"
                + "the task has not been added successfully.";
        String toPrint = "";
        if (listOfTasks.size() == 1) {
            toPrint = "Got it. I've added this task:\n  " + toAdd.toString()
                    + "\nNow you have " + listOfTasks.size() + " task in the list.";
        } else {
            toPrint = "Got it. I've added this task:\n  " + toAdd.toString()
                    + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
        }
        return toPrint;
    }

    /**
     * Marks the task at the indicated index as done.
     * @param index The index at which the task should be marked as done.
     * @return String representation of the task which has been marked as done.
     * @throws DukeExceptions if the indicated index is invalid (<= 0 or larger than the size of the list)
     */
    public String markTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            Task currTask = listOfTasks.get(indexToUse);
            currTask.setDone();
            assert currTask.isDone() : "It appears that the task was not"
                    + "successfully marked as done.";
            String toOutput = "Nice! I've marked this task as done:\n  " + currTask.toString();
            return toOutput;
        } catch (DukeExceptions DE) {
            return DE.toString();
        }
    }

    /**
     * Marks the task at the indicated index as not done.
     * @param index The index at which the task should be marked as not done.
     * @return String representation of the task which has been marked as not done.
     * @throws DukeExceptions if the indicated index is invalid (<= 0 or larger than the list size)
     */
    public String unmarkTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            Task currTask = listOfTasks.get(indexToUse);
            currTask.setUndone();
            assert !currTask.isDone() : "It appears that the task was not"
                    + "successfully marked as not done.";
            String toOutput = "Ok, I've marked this task as not done yet:\n  " + currTask.toString();
            return toOutput;
        } catch (DukeExceptions DE) {
            return DE.toString();
        }
    }

    /**
     * Checks the due date of the task if it is applicable.
     * @param index The index at which to find out the due date of.
     * @return String representation of the task's due date if applicable.
     */
    public String checkDueDate(int index) {
        int indexToUse = index - 1;
        if (listOfTasks.get(indexToUse) instanceof Deadlines) {
            Deadlines taskOfInterest = (Deadlines) listOfTasks.get(indexToUse);
            return taskOfInterest.taskDate();
        } else {
            String toReturn = "This task does not have a due date!";
            return toReturn;
        }
    }

}
