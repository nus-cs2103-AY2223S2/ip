package duke;
import java.io.Serializable;
import java.util.ArrayList;
import duke.tasks.Task;

public class TaskList implements Serializable{
    private ArrayList<Task> tasks;

    /**
     * Creating a new ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public void add(Task task){
        this.tasks.add(task);
    } 

    public int size(){
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public String list() {
        StringBuilder listOfTasks = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    }


}
