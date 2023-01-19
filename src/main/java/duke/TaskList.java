package duke;

import java.util.ArrayList;

class TaskList<Task> {
    
    private final ArrayList<Task> tasks;
    private final static int INCREMENT = 1;
   
    TaskList(TaskList<Task> newTasks) {
        this.tasks = new ArrayList<Task>(newTasks.tasks);
    }
    
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    private TaskList(ArrayList<Task> oldTasks) {
        this.tasks = new ArrayList<Task>(oldTasks);
    }
    
    int numberOfTasks() {
        return this.tasks.size();
    }

    void listAllTasks() {
        for (int i=0; i < this.numberOfTasks(); i++) {
            System.out.println(i + INCREMENT + "." + this.get(i));    
        }
    }

    Task get(int index) {
        return this.tasks.get(index);
    }

    TaskList<Task> add(Task newTask) {
        TaskList<Task> newTasks = new TaskList<Task>(this.tasks);
        newTasks.tasks.add(newTask);
        System.out.println("SUI I have added this task. Remember, rest is as important as working hard!: " + newTask); 
        return newTasks;
    }

    TaskList<Task> set(int index, Task task) {
        TaskList<Task> newTasks = new TaskList<Task>(this.tasks);
        newTasks.tasks.set(index, task);
        return newTasks;
    }

    TaskList<Task> removeTask(int index) {
        TaskList<Task> newTasks = new TaskList<Task>(this.tasks);
        newTasks.tasks.remove(index);
        return newTasks;
    }

    @Override 
    public String toString() {
        return this.tasks.toString();
    }

}

