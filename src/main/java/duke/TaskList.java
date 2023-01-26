package duke;

import duke.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> lst;
    
    TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public List<Task> getTasks() {
        return lst;
    }
    
    public Task get(int index) {
        return this.lst.get(index);
    }
    
    public void add(Task task) {
        this.lst.add(task);
    }
    
    public void remove(int index) {
        this.lst.remove(index);
    }
    
    public boolean isEmpty() {
        return this.lst.isEmpty();
    }
    
    public int size() {
        return this.lst.size();
    }
    
}
