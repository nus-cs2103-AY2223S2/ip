package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Class that encapsulates 2 methods to load file or write to file
 */
public class Storage {

    public static void saveToFile(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/duke.Duke.txt");
            for (Task t: list) {
                fw.write(t.parse() +  System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public static ArrayList<Task> loadFile() throws IOException {
        String home = System.getProperty("user.dir");
        File f = new File(home +"/data/duke.Duke.txt");
        File directory = new File(home + "/data");
        if (! directory.exists()) {
            directory.mkdir();
        }
        f.createNewFile();

        ArrayList<Task> list = new ArrayList<>();

        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] curr = s.nextLine().split(" \\| ");
            if (curr[0].equals("T")) {
                list.add(new Todo(curr[2], Boolean.valueOf(curr[1])));
            }
            else if (curr[0].equals("D")) {
                list.add(new Deadline(curr[2], curr[3], Boolean.valueOf(curr[1])));
            }
            else if (curr[0].equals("E")) {
                list.add(new Event(curr[2], curr[3], curr[4], Boolean.valueOf(curr[1])));
            }
        }

        return list;
    }
}
