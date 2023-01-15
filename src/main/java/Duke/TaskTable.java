package Duke;

import java.util.ArrayList;
import Duke.Tasks.Task;

/**
 * TaskTable keeps track of all the tasks
 */
public class TaskTable {
    private static ArrayList<Task> table;

    /**
     * Constructor of TaskTable
     */
    public TaskTable() {
        this.table = new ArrayList<>();
    }

    /**
     * Constructor of TaskTable
     * @param table
     */
    public TaskTable(ArrayList<Task> table) {
        this.table = table;
    }

    /**
     * The method gets the size of the table
     * @return int
     */
    public int size() {
        return this.table.size();
    }

    /**
     * The method gets the table
     * @return ArrayList
     */
    public ArrayList<Task> getTable() {
        return this.table;
    }

    /**
     * The method adds new task to the table
     * @param Task
     */
    public void add(Task Task) {
        this.table.add(Task);
    }

    /**
     * The method deletes certain task from the table
     * @param num
     * @return Task
     */
    public Task delete(int num) {
        return this.table.remove(num);
    }

    /**
     * The method gets a certain task from the table
     * @param num
     * @return Task
     */
    public Task get(int num) {
        return this.table.get(num);
    }

    /**
     * The method marks a certain task as done
     * @param num
     */
    public void mark(int num) {
        this.table.get(num).mark();
    }

    /**
     * The method marks a certain task as undone
     * @param num
     */
    public void unmark(int num) {
        this.table.get(num).unmark();
    }





}
