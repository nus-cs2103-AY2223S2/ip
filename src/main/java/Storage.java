import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    private ArrayList<Task> tasklst;

    private String filepath;

    public Storage() {
        this.tasklst = new ArrayList<>();
    }

    public Storage(ArrayList<Task> tasklst) {
        this.tasklst = tasklst;
    }


    public Storage(String filepath) {
        this.filepath = filepath;
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

    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(this.filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArr = line.split("\\] ");
                String[] lineType = lineArr[0].split("\\]");
                this.addTask(new Task(lineType[0].substring(1),
                        lineType[1].substring(1), lineArr[1]));
            }
            scanner.close();
            return this.tasklst;
        } catch (FileNotFoundException e) {
            throw new DukeException("File is not found!");
        }
    }

    public String getTasks() {
        String res = "";
        int counter = 1;
        for (Task tmp : this.tasklst) {
            res += counter++ + ". " + tmp.toString() + "\n";
        }
        return res;
    }

    public void updateStorage() {
        // create the directory if it is not found
        String DIRECTORY = "./data";
        try {
            File directory = new File(DIRECTORY);
            if (!directory.exists()){
                directory.mkdir();
            }
            String res = this.getTasks();
            FileWriter myWriter = new FileWriter(this.filepath);
            myWriter.write(res);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred updating the hard disk.");
            e.printStackTrace();
        }
    }
}
