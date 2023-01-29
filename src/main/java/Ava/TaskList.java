package Ava;
import Ava.Exceptions.AvaException;
import Ava.Exceptions.CommandNotFoundException;
import Ava.Exceptions.NonExistentTask;
import Ava.Tasks.Deadline;
import Ava.Tasks.Event;
import Ava.Tasks.Task;
import Ava.Tasks.Todo;


import java.util.ArrayList;


public class TaskList {
    private  ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;
    private String FILE_NAME = "tasks.txt";

    private Storage storage = new Storage();


    public void addTask(Task t) {
        tasks.add(t);
        taskCount++;
    }

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


    public Task mark(int index) throws NonExistentTask{
        index -= 1;
        if (index >= this.taskCount) {
            throw new NonExistentTask("");
        }
        tasks.get(index).mark();
        return tasks.get(index);
    }

    public Task unmark(int index) throws NonExistentTask{
        index = index - 1;
        if (index >= this.taskCount) {
            throw new NonExistentTask("");
        }
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    /**
     * Retreives Tasks from the list and formats according to UI specifications.
     * @param formatSpace
     * @return formatted tasks from taskList
     */
    public String formatTasks(String formatSpace) {
        String  res = "";
        for (int i = 0; i < taskCount; i++){
                res +=  formatSpace + (i+1) + ". " + tasks.get(i).getRepresentation() + "\n";
        }
        return res;
    }

    public void updateStorage(Storage storage) throws AvaException {
        storage.deleteFile();
        for(Task t: tasks){
            storage.writeToStorage(t.getStorageFormat());
        }
    }

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


}
