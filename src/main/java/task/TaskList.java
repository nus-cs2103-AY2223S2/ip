package task;

public class TaskList {
    protected Task[] tasks;


    public TaskList() {
        this.tasks = new Task[100];
    }

    public boolean isAllCompleted() {
        for (int i = 0; i < Task.getCount(); i++) {
            if (tasks[i].isMarked()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public int getNumTasks() {
        return Task.getCount();
    }

    public Task getTask(int i) {
        return tasks[i];
    }

    public boolean doesTaskExist(int taskNum) {
        return taskNum > 0 && taskNum <= getNumTasks();
    }

    public void addTodo(String desc) {
        int i = Task.getCount();
        tasks[i] = new Todo(desc);
        System.out.println("    " + tasks[i]);
        printNumTasks();
    }

    public void addDeadline(String date, String desc) {
        int i = Task.getCount();
        tasks[i] = new Deadline(date, desc);
        System.out.println("    " + tasks[i]);
        printNumTasks();
    }

    public void addEvent(String start, String end, String desc) {
        int i = Task.getCount();
        tasks[i] = new Event(start, end, desc);
        System.out.println("    " + tasks[i]);
        printNumTasks();
    }

    public void printNumTasks() {
        if (getNumTasks() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            String str = String.format("Now you have %d tasks in the list.", getNumTasks());
            System.out.println(str);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getNumTasks(); i++) {
            if (i == getNumTasks()-1) {
                str += (i+1) + ". " + tasks[i];
            } else {
                str += (i+1) + ". " + tasks[i] + '\n';
            }
        }
        return str;
    }

}
