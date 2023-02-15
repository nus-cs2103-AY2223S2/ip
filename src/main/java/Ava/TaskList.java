package Ava;

import Ava.exceptions.AvaException;
import Ava.exceptions.NonExistentTask;
import Ava.tasks.Deadline;
import Ava.tasks.Event;
import Ava.tasks.Task;
import Ava.tasks.Todo;

import java.util.ArrayList;

/**
 * Wrapper for List Containing Task Objects
 */
public class TaskList {
    private  ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;
    private String FILE_NAME = "tasks.txt";

    private Storage storage = new Storage();

    /**
     * Add Task to the list and increments taskCount
     * @param t Task Object
     */
    public void addTask(Task t) {
        tasks.add(t);
        taskCount++;
    }

    /**
     * deletes task from List
     * @param index delete task index
     * @return deleted Task
     * @throws NonExistentTask indicate task does not exist
     */
    public Task deleteTask(int index) throws NonExistentTask {
        index -= 1;
        if (index >= this.taskCount) {
            throw new NonExistentTask("");
        }
        Task temp = tasks.get(index);
        tasks.remove(index);
        taskCount--;
        return temp;
    }

    /**
     * mark task from List
     * @param index int indicate index of task to be marked
     * @return marked task
     * @throws NonExistentTask indicate task does not exist
     */
    public Task mark(int index) throws NonExistentTask {
        index -= 1;
        if (!this.validUnMarkedTask(index)) {
            throw new NonExistentTask("");
        }
        tasks.get(index).mark();
        return tasks.get(index);
    }

    /**
     * unmark task from List
     * @param index int indicate index of task to be unmarked
     * @return unmarked task
     * @throws NonExistentTask indicate task does not exist
     */
    public Task unmark(int index) throws NonExistentTask {
        index = index - 1;
        if (!this.validMarkedTask(index)) {
            throw new NonExistentTask("");
        }
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    /**
     * Retreives Tasks from the list and formats according to UI specifications.
     * @param formatSpace indentation reuired by UI
     * @return formatted tasks from taskList
     */
    public String formatTasks(String formatSpace) {
        String  res = "";
        for (int i = 0; i < taskCount; i++){
                res +=  formatSpace + (i+1) + ". " + tasks.get(i).getRepresentation() + "\n";
        }
        return res;
    }

    /**
     * Update current TaskList to storage
     * @param storage a storage Object
     * @throws AvaException indicate problem in updating storage from TaskList
     */
    public void updateStorage(Storage storage) throws AvaException {
        storage.deleteFile();
        for(Task t: tasks){
            storage.writeToStorage(t.getStorageFormat());
        }
    }

    /**
     * retrieve current Storage to TaskList
     * @param storage a storage Object
     * @throws AvaException indicate problem in reading storage
     */
    public void retreiveStorage(Storage storage) throws AvaException {
        ArrayList<String> taskStrings = storage.readStorage();
        for (String task : taskStrings){
            String[] parsed = task.split(",");
            Task newTask = null;
            if (parsed[0].contains(Todo.TASK_SIGN)) {
                newTask = new Todo(parsed[2]);
            } else if (parsed[0].contains(Deadline.TASK_SIGN) ) {
                newTask = new Deadline(parsed[2] , parsed[3]);
            } else if (parsed[0].contains(Event.TASK_SIGN) ) {
                newTask = new Event(parsed[2] , parsed[3], parsed[4]);
            } else {
                //Uknown ouput
            }
            if (Boolean.valueOf(parsed[1])) {
                newTask.mark();
            }
            tasks.add(newTask);
            taskCount++;
        }

    }

    /**
     * Find keyword inside the List
     * @param keyword
     * @param formatSpace indentation required by UI
     * @return representations of task containing keyword
     */
    public String find(String keyword, String formatSpace){
        String  res = "";
        for (int i = 0; i < taskCount; i++){
            if (this.tasks.get(i).taskContains(keyword)) {
                res += formatSpace + (i + 1) + ". " + tasks.get(i).getRepresentation() + "\n";
            }
        }

        return res;
    }

    public boolean validUnMarkedTask(int index) {
        return index < this.taskCount;
    }

    public boolean validMarkedTask(int index) {
        return index < this.taskCount && this.tasks.get(index).isMarked();
    }


}
