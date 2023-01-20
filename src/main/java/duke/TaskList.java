package duke;

import java.util.ArrayList;
/**
 * TaskList is the class to store all the tasks keyed in by the user during
 * the session. It has a generic type of Task, which means the type of data
 * stored in the list is Task. It is an immutable list and will always
 * return a new list if modified.
 *
 * @author Muhammad Reyaaz 
 * @version %I% %G%
 * @since 11
 */
class TaskList<Task> {
    
    private final ArrayList<Task> tasks;
    //List starts numbering from 1
    private final static int INCREMENT = 1;
    /**
     * Instantiate new list of tasks from an ArrayList
     *
     * @param newTasks Type TaskList<Task>
     */
    TaskList(TaskList<Task> newTasks) {
        tasks = new ArrayList<Task>(newTasks.tasks);
    }
    /**
     * Instantiate an empty ArrayList of tasks
     */
    protected TaskList() {
        tasks = new ArrayList<Task>();
    }
    /**
     * Only allow the TaskList class to instantiate the list of task from an
     * ArrayList of previous tasks
     */
    private TaskList(ArrayList<Task> oldTasks) {
        tasks = new ArrayList<Task>(oldTasks);
    }
    /**
     * Get the total number of tasks in the list.
     * 
     * @return sizeOfTasks
     */
    int numberOfTasks() {
        return tasks.size();
    }
    /**
     * Display to the user in a pretty format all the tasks in the list,
     * starting from 1.
     *
     * @return TaskList<Task>
     *
     */
    TaskList<Task> listAllTasks() {
        for (int i=0; i < numberOfTasks(); i++) {
            System.out.println(i + INCREMENT + "." + get(i));    
        }
        return new TaskList<Task>(tasks);
    }
    /**
     * Get a task in a specific position 
     * @return positionOfTask
     */
    Task get(int index) {
        return tasks.get(index);
    }
    /**
     * Insert a new Task. However, due to the TaskList being immutable, a
     * new TaskList is returned with the added task.
     *
     * @param newTask Task to add
     * @return TaskList<Task>
     */
    TaskList<Task> add(Task newTask) {
        TaskList<Task> newTasks = new TaskList<Task>(tasks);
        newTasks.tasks.add(newTask);
        System.out.println("SUI I have added this task. Remember, rest is as important as working hard!: " + newTask); 
        return newTasks;
    }
    /**
     * Modify a task in the list, such as to mark it as done or undone. Due
     * to the immutability nature of TaskList, a new TaskList will be
     * returned with the modified marked / unmarked status of a particular
     * task.
     *
     * @return TaskList<Task>
     */
    TaskList<Task> set(int index, Task task) {
        TaskList<Task> newTasks = new TaskList<Task>(tasks);
        newTasks.tasks.set(index, task);
        return newTasks;
    }
    /**
     * Delete a particular task present in the list. However, due to the
     * immutability nature of TaskList, a new TaskList will be returned with
     * the corresponding task deleted.
     *
     * @return TaskList<Task> 
     */
    TaskList<Task> removeTask(int index) {
        TaskList<Task> newTasks = new TaskList<Task>(tasks);
        newTasks.tasks.remove(index);
        return newTasks;
    }

    @Override 
    public String toString() {
        return tasks.toString();
    }

}

