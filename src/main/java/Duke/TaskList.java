package Duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.PeriodTask;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstraction of an arrayList that stores all the tasks.
 */
public class TaskList {
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static String printList() {
        String storeTask = "";
        for(int i = 0; i < list.size(); i++) {
            storeTask = storeTask + (i+1) + ". " + list.get(i).toString() + "\n";
      }
       return storeTask;
    }

    /**
     * Gives length of arrayList.
     * @return size of the arrayList.
     */
    public static int getLength() {
        return list.size();
    }

    /**
     * marks a task as done
     * @param num takes the ith number task starting from 1
     * @return string mentioning the task that has been editted.
     */
    public static String mark(int num) { //Takes ith number task as argument
        list.get(num - 1).toggleTrue();
        return "Nice! I've marked this task as done:\n" + list.get(num - 1).toString();
    }

    /**
     * marks a task as undone
     * @param num takes the ith number task starting from 1
     * @return string mentioning the task that has been editted.
     */
    public static String unmark(int num) {
        list.get(num - 1).toggleFalse();
        return "OK, I've marked this task as not done yet:\n" + list.get(num - 1).toString();
    }

    /**
     * Deletes a task in the ArrayList.
     * @param num ith number task starting from 1.
     * @return string mentioning the task that has been removed.
     */
    public static String delete(int num) {
        String temp = "Noted. I've removed this task:\n" + "  " + list.get(num-1).toString() + "\n" +
                "Now you have " + (list.size() - 1) + " tasks in the list.";
        list.remove(num-1);
        return temp;

    }

    /**
     * finds tasks with matching keywords.
     * @param keyword keyword to find matching task.
     * @return string listing down all the tasks.
     */
    public static String find(String keyword) {
        int index = 0;
        String tempstr = "";
        for (Task t : list) {
            String[] sample = t.toString().split(" ");
            List<String> temp = new ArrayList<>(Arrays.asList(sample));

            if (temp.contains(keyword)) {
                index++;
                tempstr = tempstr + (index + "." + t.toString());
            }
        }
        if (index == 0) {
            tempstr = ("Could not find the given word");
        }
        return tempstr;
    }

    /**
     * adds the required task to be added
     * @param str the command mentionin the type of task to be added.
     * @return string affirming that the task has been added.
     * @throws InvalidCommandException
     * @throws NoDescriptionException
     */
    public static String add_to_list(String str) throws InvalidCommandException, NoDescriptionException {

        if ((str.split(" ", 2).length == 1)) {
            throw new NoDescriptionException();
        }
        else if ((str.split(" ", 2)[0]).equals("todo")) {

            list.add(new Todo(str));
        }
        else if ((str.split(" ", 2)[0]).equals("deadline")) {
            list.add(new Deadline(str));
        }
        else if ((str.split(" ", 2)[0]).equals("event")) {
            list.add(new Event(str));
        }
        else if ((str.split(" ", 2)[0]).equals("periodTask")) {
            list.add(new PeriodTask(str));
        }
        else {
            throw new InvalidCommandException();
        }
        int size = list.size();
        return "Got it. I've added this task:\n"
        + "  " + list.get(size-1).toString() + "\n" + "Now you have " + size + " tasks in the list";
    }


}

