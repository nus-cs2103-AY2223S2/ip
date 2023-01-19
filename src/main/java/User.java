import java.util.ArrayList;

public class User {
    private int taskCount = 0;
    private ArrayList<Tasks> tasks = new ArrayList<>();
    public void addTask(Tasks task) {
        this.tasks.add(task);
        this.taskCount += 1;
    }
    public void listTasks() {
        int i = 1;
        System.out.println("Here are the tasks in your list: \n");
        for (Tasks tasks : tasks) {
            System.out.println( i + "." + tasks);
            i++;
        }
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void markTask(int index, boolean done){
        this.tasks.get(index).markTask(done);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
        this.taskCount -= 1;
        System.out.println("Now you have " + this.taskCount + " tasks in the list.");
    }

    public Tasks getTask(int index) {
        return this.tasks.get(index);
    }

    public String getTaskIcon(int index) {
        return this.tasks.get(index).getStatusIcon();
    }

    public String getTaskContent(int index) {
        return this.tasks.get(index).seeTaskContent();
    }
}
