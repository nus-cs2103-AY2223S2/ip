package duke;

import java.util.ArrayList;


/**
 * TaskList class is an extension of the java Array List class as it provides functionalities
 * that are unique for duke.
 */
public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    /**
     * Function that prints all the tasks currently in the TaskList.
     *
     */

    public String printList() {
        String output = "";
        for (int i = 0; i < this.size(); i++) {
            int num = i + 1;
            output = output + num + ". " + this.get(i) + "\n";
        }
        return output;
    }

    public String findList(String input) {
        int size = this.size();
        int index = 1;
        String output = "";
        for (int i = 0; i < size; i++) {
            Task t = this.get(i);
            if (t.getTask().contains(input)) {
                output = output + index + ". " + t + "\n";
                index++;
            }
        }
        return output;
    }

    /**
     * Marks a task in the Task List at index as done.
     *
     * @param index Index of task user wish to mark.
     * @return Task that has been marked done.
     */

    public Task markTask(int index) {

        Task t = this.get(index - 1);
        t.mark();
        return t;
    }

    /**
     * Un-marks a task in the Task List at index as undone.
     *
     * @param index Index of task user wish to un-mark.
     * @return Task that has been marked undone.
     */

    public Task unmarkTask(int index) {

        Task t = this.get(index - 1);
        t.unmark();
        return t;
    }


    /**
     * Deletes a task in the Task List at index.
     *
     * @param index Index of task user wish to delete from the TaskList.
     */

    public Task delete(int index) {
        return this.remove(index - 1);
    }

    public Task markPriority(int index, int p) {
        Task t = this.get(index - 1);
        t.markPriority(p);
        return t;
    }
}
