package Week2.src.main;
import Commands.Deadline;
import Commands.Event;
import Commands.Todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * List of tasks
 * List of tasks which is made of a custom type Task
 * @param <Task>
 */
public class TaskList<Task> {

    private int size = 0;

    private ArrayList<Task> list;

    /**
     * Basic TaskList constructor without parameter
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * TaskList constructor that loads data from the file of the path.
     * TaskList constructor.
     * It creates a new ArrayList to contain Task information in it.
     * It first figures out what type of task it is (todo, deadline, or event),
     * and divide the command and contents
     * @param path path of the file
     */
    @SuppressWarnings("unchecked")
    public TaskList(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        this.list = new ArrayList<Task>();
        while (sc.hasNext()) {
            char type = sc.next().toString().charAt(1);
            if(type == 'T') {
                this.list.add((Task) new Todo(sc.next()));
            } else if(type == 'D') {
                String[] parts = sc.next().split("by:");
                this.list.add((Task) new Deadline(parts[0].substring(0, parts[0].length()-2), parts[1].substring(0, parts[1].length()-2)));
            } else if(type == 'E') {
                String[] froms = sc.next().split("from:");
                String[] tos = froms[1].split("to:");
                this.list.add((Task) new Event(froms[0].substring(0, froms[0].length()-2),
                                                froms[1].substring(0, froms[1].length()-2),
                                                tos[1].substring(0, tos[1].length()-2)));
            }
        }
        sc.close();
    }

    /**
     * Size of the list
     * It returns the size of the list
     * @return size of the list
     */
    public int length() {
        return this.size;
    }

    /**
     * Gives the task at the given index
     * @param index
     * @return The task at the index
     */
    public Task get(int index) {
        return (Task) this.list.get(index);
    }

    /**
     * Removes the task at the given index
     * @param index
     * @param index index of the task that user wants to remove
     */
    public void removeT(int index) {
        //Task remtask = (Task) this.list.get(index);
        list.remove(index);
        size--;
    }

    /**
     * Add a new task that user has entered
     * @param t new task
     * It adds a new task
     * @param t New task that user wants to add on the list
     */
    public void add(Task t) {
        this.list.add(t);
        this.size++;
    }

    /**
     * Returns true if the task list is empty.
     * Otherwise, returns false.
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
}
