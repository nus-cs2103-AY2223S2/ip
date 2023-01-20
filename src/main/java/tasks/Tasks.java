package tasks;

public class Tasks {
    private int numTasks = 0;
    private Task[] tasksArray = new Task[100];

    public Task[] getTasks() {
        return this.tasksArray;
    }

    public void printAll() {
        for (int i = 0; i < numTasks; i++) {
            String printedString = String.format("%d. %s ", i + 1, tasksArray[i].toString());
            System.out.println(printedString);
        }
    }

    public void addTask(Task task) {
        this.tasksArray[numTasks] = task;
        numTasks++;
    }

    public String markTask(int taskIndex) {
        this.tasksArray[taskIndex - 1].mark();
        return this.tasksArray[taskIndex - 1].toString();
    }

    public String unmarkTask(int taskIndex) {
        this.tasksArray[taskIndex - 1].unmark();
        return this.tasksArray[taskIndex - 1].toString();
    }
}
