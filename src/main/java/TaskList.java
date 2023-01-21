import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private final static String ADDTASKMSG = "Got it. Added this task, I have:";

    /**
     * Constructor for Task List.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Adds task to the task list String array.
     * @param task name of the task
     */
    public void addTask(Task task) {
        System.out.println(String.format("%s\n %s", ADDTASKMSG, task.toString()));
        addTaskSilent(task);
        System.out.println(String.format("%d tasks in the list, you have now.", getSize()));
    }

    /**
     * Adds task to task list without any messages
     * @param task task object
     */
    public void addTaskSilent(Task task) {
        this.taskList.add(task);
    }

    /**
     * Outputs the size of non-null Task Objects in Task List.
     * @return number of tasks in the task list
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Deletes a task in task list by their index counting from 1
     * @param indexString
     */
    public void deleteTask(String indexString) {
        int idx;
        try {
            idx = Integer.parseInt(indexString);
            if (idx > this.getSize()) {
                throw new IndexOutOfBoundsException();
            }
            System.out.println(String.format("Noted. Removed this task I have:\n %s",
                    this.taskList.get(idx - 1)));
            this.taskList.remove(idx - 1);
            System.out.println(String.format("%d tasks in the list, you now have", getSize()));
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("In this index, no such task I found", null);
        } catch (Exception e) {
            throw new InvalidIndexException(String.format("Used to index Task List, %s cannot be", indexString), null);
        }
    }

    /**
     * Marks a task by its index in the array of Tasks.
     * @param index index of interest to mark the task as done
     */
    public void markTask(String index) {
        int idx;
        try {
            idx = Integer.parseInt(index) - 1;
            this.taskList.get(idx).mark();
        } catch (Exception e) {
            throw new InvalidIndexException(String.format("Used to index Task List, %s cannot be", index), null);
        }
    }

    /**
     * Unmarks a task by its index in the array of Tasks.
     * @param index index of interest to unmark the task as undone.
     */
    public void unmarkTask(String index) {
        int idx;
        try {
            idx = Integer.parseInt(index) - 1;
            this.taskList.get(idx).unmark();
        } catch (Exception e) {
            throw new InvalidIndexException(String.format("Used to index Task List, %s cannot be", index), null);
        }
    }

    /**
     * Prints the names of tasks in the list in the top-down list format with numbered indexes
     * starting from 1 added in chronological order whereas returns empty list string if task list is empty.
     */
    public void listItems() {
        if (this.getSize() == 0) {
            System.out.println("Empty, this list is !");
        } else {
            String out = "";
            for (int i = 0; i < this.getSize() - 1; i++) {
                out += String.format("%d.%s\n", i + 1, taskList.get(i).toString());
            }
            System.out.println(out + String.format("%d.%s", this.getSize(), taskList.get(this.getSize() - 1).toString()));
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
