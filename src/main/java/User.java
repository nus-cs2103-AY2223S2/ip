import java.util.ArrayList;

public class User {
    private ArrayList<Tasks> tasks = new ArrayList<Tasks>();
    public void addTask(Tasks task) {
        this.tasks.add(task);
    }
    public void listTasks() {
        int i = 1;
        System.out.println("Here are the tasks in your list: \n");
        for (Tasks tasks : tasks) {
            System.out.println( i
                    + ".[" + tasks.getStatusIcon() + "] " + tasks.seeTaskContent());
            i++;
        }
    }

    public void markTask(int index, boolean done){
        this.tasks.get(index).markTask(done);
    }

    public String getTaskIcon(int index) {
        return this.tasks.get(index).getStatusIcon();
    }

    public String getTaskContent(int index) {
        return this.tasks.get(index).seeTaskContent();
    }
}
