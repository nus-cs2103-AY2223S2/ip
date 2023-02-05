package task;

import exception.WillyException;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks = new ArrayList<Task>();
    public Storage storage;

    public TaskList(){
        this.storage = new Storage();
        List<Task> tmp = storage.loadData();
        this.tasks = tmp;
    }

    public int getTaskCount(){
        return tasks.size();
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public void addTodo(String details) throws WillyException {
        ToDos entry = new ToDos(details);
        tasks.add(entry);
        storage.save(this);
        System.out.println("added todo item"); //chaneg this later
    }
}
