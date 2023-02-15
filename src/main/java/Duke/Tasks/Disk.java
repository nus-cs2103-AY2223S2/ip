package duke.Tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Disk {

    private final String path;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * The constructor for Duke.Tasks.Disk method
     * @param path
     */
    public Disk(String path) {
        this.path = path;
    }

    /**
     * Read data from txt file
     * @return ArrayList
     * @throws IOException
     */
    public ArrayList<Task> read() throws IOException {
        ArrayList<Task> taskTable = new ArrayList<>();
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] currentTask = line.split(" \\| ");
                boolean done = currentTask[1].equals("1");
                if (currentTask[0].equals("T")) {
                    taskTable.add(new Todo(currentTask[2], done));
                } else if(currentTask[0].equals("D")) {
                    taskTable.add(new Deadline(currentTask[2], LocalDateTime.parse(currentTask[3], format), done));
                } else if(currentTask[0].equals("E")) {
                    taskTable.add(new Event(currentTask[2], LocalDateTime.parse(currentTask[3], format),
                            LocalDateTime.parse(currentTask[4], format) , done));
                } else {}
            }
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("/data folder not found, creating for you now");
            } else if (new File("data/duke.txt").createNewFile()){
                System.out.println("/data/duke.txt file not found, creating for you now");
            }
        }
        return taskTable;
    }

    /**
     * Write data to txt file
     * @return ArrayList
     * @throws IOException
     */
    public void write(ArrayList<Task> taskTable) {
        try {
            FileWriter fw = new FileWriter(path, false);
            for (Task task : taskTable) {
                fw.write(task.reformat() + "\n");
            }
            fw.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
