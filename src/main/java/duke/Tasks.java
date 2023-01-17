package duke;

import java.util.ArrayList;

class Tasks<Task> {
    
    private final ArrayList<Task> tasks;
    private final static int INCREMENT = 1;
   
    Tasks(Tasks<Task> newTasks) {
        this.tasks = new ArrayList<Task>(newTasks.tasks);
    }
    
    Tasks() {
        this.tasks = new ArrayList<Task>();
    }

    private Tasks(ArrayList<Task> oldTasks) {
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

    Tasks<Task> add(Task newTask) {
        Tasks<Task> newTasks = new Tasks<Task>(this.tasks);
        newTasks.tasks.add(newTask);
        System.out.println("SUI I have added this task. Remember, rest is as important as working hard!: " + newTask); 
        return newTasks;
    }

    Tasks<Task> set(int index, Task task) {
        Tasks<Task> newTasks = new Tasks<Task>(this.tasks);
        newTasks.tasks.set(index, task);
        return newTasks;
    }

    Tasks<Task> removeTask(int index) {
        Tasks<Task> newTasks = new Tasks<Task>(this.tasks);
        newTasks.tasks.remove(index);
        return newTasks;
    }

    @Override 
    public String toString() {
        return this.tasks.toString();
    }

}

