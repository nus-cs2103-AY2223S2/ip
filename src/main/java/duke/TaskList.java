package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Stores all the tasks keyed in by the user during
 * the session. It has a generic type of Task, which means the type of data
 * stored in the list is Task. It is an immutable list and will always
 * return a new list if modified.
 *
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @since 11
 */
class TaskList<T> {

    private final ArrayList<Task> tasks;
    //List starts numbering from 1
    private final static int INCREMENT = 1;
    /**
     * Instantiate new list of tasks from an ArrayList
     *
     * @param newTasks Type TaskList<T>
     */
    TaskList(TaskList<T> newTasks) {
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
     * @return TaskList<T>
     *
     */
    TaskList<T> listAllTasks() {
        AtomicLong counter = new AtomicLong();
        tasks.stream().forEach(task ->
                System.out.println(counter.incrementAndGet() + "." + task));
        /*
        for (int i=0; i < numberOfTasks(); i++) {
            System.out.println(i + INCREMENT + "." + get(i));
        }
        */
        return new TaskList<T>(tasks);
    }

    <T extends Task> TaskList<T> listFindTasks(String find, TaskList<T> list) {
        AtomicLong counter = new AtomicLong();
        tasks.stream().filter(task ->
                task.getDescription().contains(find.substring(1))).forEach(task ->
                        System.out.println(counter.incrementAndGet() + "." + task));
        /*
        for (int i=0; i < numberOfTasks(); i++) {
            Task currTask = (Task)get(i);
            if (currTask.getDescription().contains(find.substring(1))) {
                System.out.println(i + INCREMENT + "." + currTask);
            }
        }
        */
        return new TaskList<T>(tasks);
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
     * @return TaskList<T>
     */
    TaskList<T> add(Task newTask) {
        TaskList<T> newTasks = new TaskList<T>(tasks);
        newTasks.tasks.add(newTask);
        System.out.println("SUI I have added this task!: " + newTask);
        return newTasks;
    }

    TaskList<T> addMultipleTasks(Task... newTask) {
        TaskList<T> newTasks = new TaskList<T>(tasks);
        Arrays.stream(newTask).forEach(task -> {
            newTasks.tasks.add(task);
            System.out.println("SUI I have added this task!: " + task);
        });
        /*
        for (Task task: newTask) {
            newTasks.tasks.add(task);
            System.out.println("SUI I have added this task!: " + task);
        }
        */
        return newTasks;
    }

    /**
     * Modify a task in the list, such as to mark it as done or undone. Due
     * to the immutability nature of TaskList, a new TaskList will be
     * returned with the modified marked / unmarked status of a particular
     * task.
     *
     * @return TaskList<T>
     */
    TaskList<T> set(int index, Task task) {

        TaskList<T> newTasks = new TaskList<T>(tasks);
        newTasks.tasks.set(index, task);
        return newTasks;
    }

    /**
     * Delete a particular task present in the list. However, due to the
     * immutability nature of TaskList, a new TaskList will be returned with
     * the corresponding task deleted.
     *
     * @return TaskList<T>
     */
    TaskList<T> removeTask(int index) {
        TaskList<T> newTasks = new TaskList<T>(tasks);
        newTasks.tasks.remove(index);
        return newTasks;
    }

    TaskList<T> removeMultipleTasks(int... index) {
        TaskList<T> newTasks = new TaskList<T>(tasks);
        Arrays.stream(index).forEach(newTasks.tasks::remove);
        /*
        for (int i : index) {
            newTasks.tasks.remove(i);
        }
        */
        return newTasks;
    }

    @Override
    public String toString() {
        return tasks.toString();
    }

}
