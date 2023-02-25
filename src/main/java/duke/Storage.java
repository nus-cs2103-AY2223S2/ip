package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static File f;
    private File directory = new File("./data");

    /**
     * constructs a Storage object that stores data into a file.
     * @param filePath file path leading to data directory.
     */
    public Storage(String filePath) {
        f = new File(filePath);
        try {
            directory.mkdir();
            f.createNewFile();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * loads the tasks from the file into the CLI.
     *
     * @return ArrayList of tasks that was stored in the data directory.
     * @throws DukeException if task description is not found.
     * @throws FileNotFoundException if file is not found.
     */
    public static ArrayList<Task> load() throws DukeException, FileNotFoundException {
        Scanner sc = new Scanner(f);
        ArrayList<Task> list = new ArrayList<>();
        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(" \\| ");
            String type = input[0];
            String mark = input[1];
            Task x = null;
            switch (type) {
                case "T":
                    x = new ToDo(input[2]);
                    break;
                case "D":
                    x = new Deadline(input[2], input[3]);
                    break;
                case "E":
                    x = new Event(input[2], input[3], input[4]);
                    break;
            }
            if (mark.equals("X")) {
                assert x != null;
                x.markAsDone();
            }
            list.add(x);
        }
        return list;
    }

    /**
     * writes the data from taskList to save file.
     * @param filePath file path leading to data directory.
     * @param list taskList which data will be written to the save file.
     * @throws IOException if there is an error accessing the file.
     */
    public static void write(String filePath, TaskList list) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        FileWriter writer = new FileWriter(filePath, false);
        writer.write(list.toStorageData());
        writer.close();
    }
}
