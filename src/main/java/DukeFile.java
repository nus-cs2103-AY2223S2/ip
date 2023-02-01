import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DukeFile {
    

    private static void checkFile() {
        File file = new File("duke.txt");
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }

    public static void saveData(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter("duke.txt");
            for (Task t : taskList) {
                fileWriter.write(t.formatForFile());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File("duke.txt");
        if (file.isFile()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] data = sc.nextLine().split(" \\| ");
                Task task = null;
                switch (data[0]) {
                    case "T":
                        task = new Todo(data[2]);
                        break;
                    case "D":
                        task = new Deadline(data[2], data[3]);
                        break;
                    case "E":
                        task = new Event(data[2], data[3], data[4]);
                        break;
                }

                if (data[1].equals("1")) {
                    task.mark();
                }

                taskList.add(task);
                Task.incrementTaskCount();
            }
        }

        return taskList;
     }
}
