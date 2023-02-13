package seedu.duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The ToDoList class manages and stores Tasks.
 */
public class ToDoList {

    private List<Task> todolist;
    private int count = 0;

    public ToDoList() {
        todolist = new ArrayList<>();
    }

    /**
     * Lists all tasks.
     */
    public String list() {
        String res = "\tTasks for Tony Stark:\n";
        res += "\t--------------------------\n";
        for (int i = 0; i < count; i++) {
            String temp = "\t" + (i + 1) + todolist.get(i).printTask() + "\n";
            res += temp;
        }
        res += "\t--------------------------\n";
        return res;
    }

    /**
     * Prints the current task.
     *
     * @param index index of task to print task
     * @return the task at specified index
     */
    public String getTask(int index) {
        return todolist.get(index).printTask();
    }

    /**
     * Constructor for Todo.
     *
     * @param task task to be instantiated
     */
    public void add(String task) {
        Task t = new Todo(count, task);
        todolist.add(count, t);
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\t--------------------------");
    }

    /**
     * Constructor for Deadline.
     *
     * @param task task to be instantiated
     * @param time deadline of task
     */
    public void add(String task, LocalDate time) {
        Task t = new Deadline(count, task, time);
        todolist.add(count, t);
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\tDue: " + time);
        System.out.println("\t--------------------------");
    }

    /**
     * Constructor for Event.
     *
     * @param task task to be instantiated
     * @param startTime starting time of event
     * @param endTime ending time of event
     */
    public void add(String task, LocalDate startTime, LocalDate endTime) {
        Task t = new Event(count, task, startTime, endTime);
        todolist.add(count, t);
        count++;
        System.out.println("\t--------------------------");
        System.out.println("\tI have added task: " + t.getTask());
        System.out.println("\tStarts: " + startTime);
        System.out.println("\tEnds: " + endTime);
        System.out.println("\t--------------------------");
    }

    /**
     * Deletes the task at specified index.
     *
     * @param index index of task
     */
    public void delete(int index) {
        System.out.println("\t--------------------------");
        System.out.println("\tDeleted: " + todolist.get(index - 1).printTask());
        System.out.println("\t--------------------------");
        todolist.remove(index - 1);
        count--;
    }

    /**
     * Marks the task at specified index as done.
     *
     * @param index index of task
     */
    public void markDone(int index) {
        todolist.get(index - 1).markDone(index);
    }

    /**
     * Marks the task at specified index as not done.
     *
     * @param index index of task
     */
    public void unmark(int index) {
        todolist.get(index - 1).unmark(index);
    }

    /**
     * Returns the last index of the current arrayList.
     *
     * @return last index of arrayList
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns boolean of whether todolist has specified tasks.
     *
     * @param task task description
     * @return boolean result
     */
    public boolean hasTask(String task) {
        for (int i = 0; i < count; i++) {
            if (todolist.get(i).hasTask(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the specified task.
     *
     * @param task task description
     */
    public String findTaskIfExist(String task) {
        for (int i = 0; i < count; i++) {
            if (todolist.get(i).hasTask(task)) {
                return todolist.get(i).printTask();
            }
        }
        return ("Task does not exist");
    }

    /**
     * Finds available timeslots
     *
     * @param timeLength task description
     */
    public String findTimeslot(String timeLength) {
        return "";
    }

    /**
     * Finds time (in days) until next deadline.
     *
     * @return Confirmation of found time length, or error message
     */
    public String findDaysUntilNextDeadline() {
        LocalDate currEarliestDate = null;

        for (int i = 0; i < count; i++) {
            Task currTask = todolist.get(i);
            if (currTask instanceof Deadline) {
                boolean isFutureDate = ((Deadline) currTask).getDeadline()
                        .isAfter(Duke.getCurrDate());
                if (!isFutureDate) {
                    continue;
                } else if (currEarliestDate == null) {
                    currEarliestDate = ((Deadline) currTask).getDeadline();
                } else if (currEarliestDate.isAfter(((Deadline) currTask).getDeadline())) {
                    currEarliestDate = ((Deadline) currTask).getDeadline();
                }
            }
        }
        if (currEarliestDate == null) {
            return "No deadlines in todolist.";
        }
        long daysDiff = Duke.getCurrDate().datesUntil(currEarliestDate).count();
        return (daysDiff + " days until next deadline.");
    }
}
