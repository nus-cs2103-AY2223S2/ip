import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class Tasklist {
    private ArrayList<Task> tasklst;

    public Tasklist() {
        this.tasklst = new ArrayList<>();
    }

    public Task getTask(int i) {
        return this.tasklst.get(i);
    }

    public Task removeTask(int i) {
        return this.tasklst.remove(i);
    }

    public void addTask(Task t) {
        this.tasklst.add(t);
    }

    public int getSize() {
        return this.tasklst.size();
    }

    public String getTasks() {
        String res = "";
        int counter = 1;
        for (Task tmp : this.tasklst) {
            res += counter++ + ". " + tmp.toString() + "\n";
        }
        return res;
    }

    public void updateTasklist() {
        String DIRECTORY = "./data";
        String FILENAME = "duke.txt";
        try {
            File directory = new File(DIRECTORY);
            if (!directory.exists()){
                directory.mkdir();
            }
            String res = this.getTasks();
            FileWriter myWriter = new FileWriter(DIRECTORY + "/" + FILENAME);
            myWriter.write(res);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
