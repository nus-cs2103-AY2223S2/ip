import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {
    private final String STORAGE_PATH;

    public Storage(String filePath) {
        this.STORAGE_PATH = filePath;
    }

    public void saveToFile(ArrayList<Task> tasksList) {
        try {
            String currPath = System.getProperty("user.dir") + "/data";
            File dataDirectory = new File(currPath);
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }
            File currFile = new File(STORAGE_PATH);
            FileWriter fw = new FileWriter(currFile, false);
            for (Task task : tasksList) {
                fw.write(task.toString() + '\n');
            }
            System.out.print(tasksList);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> loadFromFile() throws FileNotFoundException {
        ArrayList<Task> tasksList = new ArrayList<>();
        String path = System.getProperty("user.dir") + STORAGE_PATH;
        File sourceFile = new File(path);
        if (!sourceFile.exists()) {
            return tasksList;
        } else {
            return readFile(sourceFile);
        }
    }

    public ArrayList<Task> readFile(File file) throws FileNotFoundException {
        ArrayList<Task> tasksList = new ArrayList<>();
        Scanner sc =new Scanner(file);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            String[] actionArray = nextLine.split("\\.");
            String[] actionNextArray = actionArray[1].split(":");
            String action = actionNextArray[0].strip();
            switch(action) {
                case "Todo": {
                    String[] taskArray = nextLine.split(":");
                    String task = taskArray[0].strip();
                    Task savedTask = new Todo(task);
                    if (nextLine.substring(1, 2).equals("X")) {
                        savedTask.check();
                    }
                    tasksList.add(savedTask);
                    break;
                }
                case "Deadline": {
                    String[] frontTaskArray = nextLine.split(":");
                    String[] backTaskArray = frontTaskArray[1].split("\\[");
                    String task = backTaskArray[0].strip();
                    String[] frontByArray = nextLine.split("\\[");
                    String[] backByArray = frontByArray[1].split("]");
                    String from = backByArray[0].strip();
                    Task savedTask = new Deadline(task, from);
                    if (nextLine.substring(1, 2).equals("X")) {
                        savedTask.check();
                    }
                    tasksList.add(savedTask);
                    break;
                }
                case "Event": {
                    String[] frontTaskArray = nextLine.split(":");
                    String[] backTaskArray = frontTaskArray[1].split("\\[");
                    String task = backTaskArray[0].strip();
                    String[] frontFromArray = nextLine.split("\\[");
                    String[] backFromArray = frontFromArray[1].split("\\]");
                    String from = backFromArray[0].strip();
                    String[] frontToArray = nextLine.split("-");
                    String[] backToArray = frontToArray[1].split("\\]");
                    String to = backToArray[0].strip();
                    Task savedTask = new Event(task, from, to);
                    if (nextLine.substring(1, 2).equals("X")) {
                        savedTask.check();
                    }
                    tasksList.add(savedTask);
                    break;
                }
            }
        }
        return tasksList;
    }
}
