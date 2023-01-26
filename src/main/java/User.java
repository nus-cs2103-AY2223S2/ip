import java.util.ArrayList;
import java.io.*;

public class User {
    private int taskCount = 0;
    private ArrayList<Tasks> tasks = new ArrayList<>();

    public User() {
        try {
            this.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("No existing tasks, creating new task list...");
        }

        this.taskCount = this.tasks.size();
    }

    public void addTask(Tasks task) {
        this.tasks.add(task);
        this.taskCount += 1;
        this.saveTasks();
    }

    //adopted from CHATGPT
    public void saveTasks() {
        try {
            FileOutputStream file = new FileOutputStream("tasks.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(this.tasks);
            output.close();
        } catch (IOException e) {
            System.out.println("file error");
        }
    }
    //adapted from CHATGPT
    public void loadTasks() throws FileNotFoundException {
        try {
            FileInputStream file = new FileInputStream("tasks.ser");
            ObjectInputStream output = new ObjectInputStream(file);
            this.tasks = (ArrayList<Tasks>)output.readObject();
            output.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new FileNotFoundException();
        }
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
        this.saveTasks();
    }

    public String deleteTask(int index) {
        String returnString = this.tasks.get(index).toString();
        this.tasks.remove(index);
        this.taskCount -= 1;
        System.out.println("Now you have " + this.taskCount + " tasks in the list.");
        this.saveTasks();
        return returnString;
    }



    public String getTaskIcon(int index) {
        return this.tasks.get(index).getStatusIcon();
    }

    public String getTaskContent(int index) {
        return this.tasks.get(index).seeTaskContent();
    }
}
